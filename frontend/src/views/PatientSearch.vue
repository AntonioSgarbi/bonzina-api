<template>
  <div>
    <b-input-group size="lg">
      <b-form-input
          v-model="fieldName"
          @keypress.enter="findPatient"
          placeholder="Nome do Paciente">
      </b-form-input>
      <b-button
          @click="findPatient"
          variant="info">
        Pesquisar
      </b-button>
    </b-input-group>

  <PersonTable
      @pageChanged="findPatientFromPagination"
      :fields="fields"
      :per-page="patientPage.numberOfElements"
      :items="patientPage.content"
      :totalpages="patientPage.totalPages"
      :totalelements="patientPage.numberOfElements"
      :is-list-empty="patientPage.empty"
      :is-from-doctor="false"
      edit-route="/registerpatient"
  />
  </div>
</template>

<script>
import PersonTable from "../components/PersonTable";
import PatientService from "../services/PatientService";
import store from "../vuex-store";
import {mapState} from "vuex";

export default {
  name: "PatientSearch",

  components: {
    PersonTable
  },

  data() {
    return {
      isListEmpty: false,
      fieldName: '',
      fields: [
        {
          key: 'show_details',
          label: ''
        },

        {
          key: 'name',
          label: 'Nome',
          sortable: true,
        },
        {
          key: 'phone',
          label: 'Telefone',
          sortable: false
        },
        {
          key: 'email',
          label: 'Email',
          sortable: false
        },
        {
          key: 'address',
          label: 'Endereço',
          sortable: false
        },
        {
          key: 'birthdate',
          label: 'Data de Nascimento',
          sortable: false
        },
      ],
      pageComponent: 1
    }
  },
  mounted() {
    //this.stopLoading()
  },
  computed:
      mapState ([
        'patientPage'
        ]),

  methods: {
    findPatient() {
      this.$store.state.pageComponentStore = 1
      PatientService.findByName(this.fieldName)
    },
    findPatientFromPagination() {
      console.log('findPatient ' + this.$store.state.patientPageNumber)
      let objectParam = {page: this.$store.state.patientPageNumber}
      PatientService.findByName(this.fieldName, objectParam)
    },

    stopLoading() {
      setTimeout(() => this.isPersonTableLoading = false, 2000)
    }
  }
}
</script>

<style scoped>
div {
  padding: 1%;
}

</style>