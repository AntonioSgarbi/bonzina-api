<template>
  <div>
    <h1 v-show="isListEmpty && !this.$store.state.isPersonTableLoading">
      lista vazia
    </h1>
    <div id="spinner">
      <b-spinner v-show="this.$store.state.isPersonTableLoading"
                 style="width: 3rem; height: 3rem;" variant="primary" label="Spinning">
      </b-spinner>
    </div>
    <b-table
        v-show="!isListEmpty && !this.$store.state.isPersonTableLoading"
        ref="selectableTable"
        bordered
        small
        hover
        responsive="sm"
        stacked="md"
        id="my-table"
        :items="items"
        :fields="fields"
        :per-page="this.$store.state.patientPage.numberOfElements"
    >
      <template #cell(show_details)="row">
        <b-button
            size="sm"
            variant="info"
            @click="row.toggleDetails"
            class="mr-2">
          {{ row.detailsShowing ? 'Cancelar' : 'Opções' }}
        </b-button>
      </template>

      <template #row-details="row">
        <b-card>
          <b-button
              v-b-modal.modal
              size="sm"
              variant="success"
              @click="toSchedule(row.item)"
              class="mr-2">
            {{ isSchedulingNow ? 'Inserir na consulta' : 'Criar Agendamento' }}
          </b-button>&nbsp;

          <router-link :to="routeToEditPage" custom v-slot="{ navigate }">
            <span @click="navigate" @keypress.enter="navigate" role="link">
              <b-button
                  variant="warning"
                  size="sm"
                  @click="toEdit(row.item)"
                  class="mr-2"
              >
                Editar Cadastro
              </b-button>
            </span>
          </router-link>

        </b-card>
      </template>
    </b-table>
    <div id="pagination" v-show="!isFromDoctor && !isListEmpty">
      <b-pagination
          pills
          @change="handlePageChange"
          v-model="pageComponentLocal"
          :enabled="!isFromDoctor"
          :total-rows="this.$store.state.patientPage.totalElements"
          :number-of-pages="this.$store.state.patientPage.totalPages"
          aria-controls="my-table"
          first-number
          last-number
          variant="dark">
      </b-pagination>
    </div>
  </div>
</template>

<script>

import { mapMutations, mapState } from "vuex";

export default {
  name: "PersonTable",

  emits: [
    'pageChanged'
  ],

  props: {
    fields: [],
    items: [],
    isListEmpty: {
      type: Boolean,
      default: false
    },
    isFromDoctor: {
      type: Boolean,
      default: false
    },
  },

  data() {
    return {
      routeToEditPage: '',
      pageComponentLocal: 1,
    }
  },
  computed: {
    ...mapState([
      'isSchedulingNow',
      'isPersonTableLoading',
      'isSchedulingEditing',
      'fromPerson'
    ]),
  },

  methods: {
    ...mapMutations([
      'setPatient',
      'setDoctor',
      'setIsFromEdit',
      'setIsDoctorFilled',
      'setIsPatientFilled',
      'setSchedulingDoctor',
      'setSchedulingPatient',
      'setIsPatientFilled',
      'setIsSchedulingNow',
    ]),

    toSchedule(item) {
      console.log(this.fromPerson)
      this.setIsSchedulingNow()
      if (this.fromPerson === 'fromPatient') {
        this.setIsPatientFilled()
        this.setSchedulingPatient(item)
        this.setPatient(item)
      }
      else { //fromDoctor
        this.setIsDoctorFilled()
        this.setSchedulingDoctor(item)
        console.log(item)
        this.setDoctor(item)
      }
    },
    toEdit(item) {
      this.setIsFromEdit()
      if (this.$store.state.fromPerson === 'fromPatient') {
        console.log('from patient ' + item)
        console.log(item)
        this.setPatient(item)
        this.routeToEditPage = 'registerpatient'
      } else { //fromDoctor
        console.log('from doctor ' + item)
        console.log(item)
        this.setDoctor(item)
        this.routeToEditPage = 'registerdoctor'
      }
    },

    handlePageChange(value) {
      this.pageComponentLocal = value
      this.$store.state.patientPageNumber = value - 1
      this.$emit('pageChanged')
    },
  }
}
</script>

<style scoped>

#spinner {
  display: flex;
  justify-content: center;
  padding: 0.5%;
}

#pagination {
  margin: 1% 0;
  padding-top: 1%;
  display: flex;
  justify-content: center;
}
</style>

export default PersonTable