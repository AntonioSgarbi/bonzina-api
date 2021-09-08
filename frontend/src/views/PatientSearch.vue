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
      :items="patientPage.content"
      :is-list-empty="patientPage.empty"
      :enabled="true"
      :is-from-doctor="false"
      edit-route="/registerpatient"
  />
  </div>
</template>

<script>
import PersonTable from "../components/PersonTable";
import PatientService from "../services/PatientService";
import {mapMutations, mapState} from "vuex";

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

  computed: {
    ...mapState([
      'patientPage',
      'patientPageNumber'
    ])
  },

  methods: {
    ...mapMutations(['setPageComponentStore']),

    findPatient() {
      this.pageComponentStore(1)
      PatientService.findByName(this.fieldName)
    },

    findPatientFromPagination() {
      let objectParam = {page: this.patientPageNumber}
      PatientService.findByName(this.fieldName, objectParam)
    },

  }
}
</script>

<style scoped>
div {
  padding: 1%;
}

</style>