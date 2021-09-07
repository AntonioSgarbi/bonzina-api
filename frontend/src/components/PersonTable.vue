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
        :current-page="this.$store.state.pageComponentStore"
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
              variant="info"
              @click="toSchedule(row.item)"
              class="mr-2">
            {{ isSchedulingNow ? 'Inserir na consulta' : 'Criar Agendamento' }}
          </b-button>&nbsp;

          <router-link :to="routeToEditPage" custom v-slot="{ navigate }">
            <span @click="navigate" @keypress.enter="navigate" role="link">
              <b-button
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
          :per-page="perPage"
          @change="handlePageChange"
          v-model="pageComponent"
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

import {mapGetters, mapState} from "vuex";

export default {
  name: "PersonTable",

  emits: [
    'pageChanged'
  ],

  props: {
    fields: [],
    perPage: 0,
    items: [],
    isPersonTableLoading: true,
    isListEmpty: false,
    isFromDoctor: true,
  },

  data() {
    return {
      routeToEditPage: '',
    }
  },
  computed: {
    ...mapState({isSchedulingNow:state => state.isSchedulingNow}),
    ...mapGetters({
      pageComponent: 'pageComponentStore'
    }),
    pageComponent: {
      get() {
        return this.$store.state.pageComponentStore
      },
      set(newPage) {
        return newPage
      }
    }
  },

  methods: {

    toSchedule(item) {
      if (this.$store.state.fromPerson === 'fromPatient') {
        this.$store.state.patient = item
        this.$store.state.patientFilled = true
      } else { //fromDoctor
        this.$store.state.doctor = item
        this.$store.state.doctorFilled = true
      }
    },
    toEdit(item) {
      this.$store.state.isFromEdit = true
      if (this.$store.state.fromPerson === 'fromPatient') {
        this.$store.state.patient = item
        this.routeToEditPage = 'registerpatient'
      } else { //fromDoctor
        this.$store.state.doctor = item
        this.routeToEditPage = 'registerdoctor'
      }
    },

    handlePageChange(value) {
      console.log(this.$store.state.patientPage.totalPages)
      this.pageComponent = value
      this.$store.state.patientPageNumber = value - 1
      console.log('value ' + value + ' patientPageNumber ' + this.$store.state.patientPageNumber)
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