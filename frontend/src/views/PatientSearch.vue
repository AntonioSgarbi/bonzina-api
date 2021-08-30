<template>
  <div>
    <b-input-group size="lg">
      <b-form-input v-model="fieldName" placeholder="Nome do Paciente"></b-form-input>
      <b-button @click="searchClicked" variant="info">Pesquisar</b-button>
    </b-input-group>

  <PersonTable :fields="fields" :items="patients" edit-route="/registerpatient" />
  </div>

</template>

<script>
import PersonTable from "@/components/PersonTable";
import axios from "axios";

export default {
  name: "PatientSearch",

  components: {
    PersonTable
  },

  data() {
    return {
      fieldName: '',
      fields: [
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
        'show_details'
      ],
      patients: [
        {
          id: 1,
          name: 'searchPatientantonio',
          birthdate: '1997-12-03',
          phone: '(48)99170-6401',
          email: 'antonio@email.com',
          address: 'minha ruaminha ruaminha ruaminha ruaminha ruaminha rua'
        },
        {
          id: 2,
          name: 'searchPatientantonio1',
          birthdate: '1997-12-04',
          phone: '(48)99170-6401',
          email: 'antonio@email.com',
          address: 'minha ruaminha ruaminha ruaminha ruaminha ruaminha rua'
        },
        {
          id: 3,
          name: 'searchPatientantonio2',
          birthdate: '1997-12-05',
          phone: '(48)99170-6401',
          email: 'antonio@email.com',
          address: 'minha ruaminha ruaminha ruaminha ruaminha ruaminha rua'
        },
        {
          id: 4,
          name: 'searchPatientantonio3',
          birthdate: '1997-12-06',
          phone: '(48)99170-6401',
          email: 'antonio@email.com',
          address: 'minha ruaminha ruaminha ruaminha ruaminha ruaminha rua'
        }
      ]
    }
  },

  methods: {
    async searchClicked() {
      this.showTable = !this.showTable
      await axios.get('http://localhost:8081/patient/' + this.fieldName)
          .then(function (response) {
            this.patients = response
          })
          .catch(window.alert('Api sem resposta'))
    }
  }
}
</script>

<style scoped>
div {
  padding: 1%;
}
</style>