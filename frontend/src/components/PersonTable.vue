<template>
  <div>
    <b-table
        hover
        :items="items"
        :fields="fields"
        ref="selectableTable"
        responsive="sm"
    >
      <template #cell(show_details)="row" >
        <b-button size="sm" @click="row.toggleDetails" class="mr-2" >
          {{ row.detailsShowing ? 'Cancelar' : 'Opções' }}
        </b-button>
      </template>

      <template #row-details="row">
        <b-card>
              <b-button v-b-modal.modal size="sm" @click="toSchedule(row.item)">Agendar Consulta</b-button>&nbsp;

          <router-link :to="editRoute" custom v-slot="{ navigate }">
            <span @click="navigate" @keypress.enter="navigate" role="link">
              <b-button size="sm" @click="toEdit(row.item)">Editar Cadastro</b-button>
            </span>
          </router-link>
        </b-card>
      </template>
    </b-table>
    <div id="pagination">
      <b-pagination
          v-model="currentPage"
          pills
          :total-rows="rows"
          aria-controls="my-table"
          first-number
          last-number
          variant="dark">
      </b-pagination>
    </div>
  </div>
</template>

<script>
import store from "../vuex";

export default {
  name: "PersonTable",
  props: {
    fields: [],
    items: [],
  },
  data() {
    return {
      editRoute: '',
      currentPage: 0,
      rows: 10
    }
  },
  methods: {
    toSchedule(item) {
      if(store.state.fromPerson === 'fromPatient'){
        store.state.patient = item
      } else { //fromDoctor
        store.state.doctor = item
      }
      console.log('item ' + item) //test
    },
    toEdit(item) {
      store.state.fromEdit = true
      if(store.state.fromPerson === 'fromPatient') {
        store.state.patient = item
        this.editRoute = 'registerpatient'
      }
      else { //fromDoctor
        store.state.doctor = item
        this.editRoute = 'registerdoctor'
      }
      console.log(item) //test
    }
  }
}
</script>

<style scoped>
  #pagination {
    margin: 1% 0%;
    padding-top: 1%;
    border: solid black;
    display: flex;
    justify-content: center;
  }
</style>

export default PersonTable