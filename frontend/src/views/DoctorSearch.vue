<template>
  <div>
    <b-input-group size="lg">
      <b-form-input v-model="fieldName" placeholder="Nome do Médico"></b-form-input>
      <b-button @click="searchClicked" variant="info">Pesquisar</b-button>
    </b-input-group>

    <PersonTable :fields="fields" :items="doctors" edit-route="/registerdoctor"/>
  </div>
</template>

<script>
import PersonTable from "./components/PersonTable";


export default {
  name: "DoctorSearch",
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
          key: 'register',
          label: 'CRM',
          sortable: false
        },
        {
          key: 'speciality',
          label: 'Especialidade',
          sortable: false
        },
        {
          key: 'clinic',
          label: 'Consultório',
          sortable: false
        },
        {
          key: 'period',
          label: 'Período',
          sortable: false
        },
        'show_details'
      ],
      doctors: [
          //lista de objetos pra teste
        {
          name: 'antonio',
          phone: '(48)99170-6401',
          email: 'antonio@email.com',
          register: 1234567,
          speciality: 'cardiologista',
          clinic: 'ONE',
          period: 'MORNING'
        },
        {
          name: 'antonio',
          phone: '(48)99170-6401',
          email: 'antonio@email.com',
          register: 1234567,
          speciality: 'cardiologista',
          clinic: 'ONE',
          period: 'MORNING'
        },
        {
          name: 'antonio',
          phone: '(48)99170-6401',
          email: 'antonio@email.com',
          register: 1234567,
          speciality: 'cardiologista',
          clinic: 'ONE',
          period: 'MORNING'
        },
        {
          name: 'antonio',
          phone: '(48)99170-6401',
          email: 'antonio@email.com',
          register: 1234567,
          speciality: 'cardiologista',
          clinic: 'ONE',
          period: 'MORNING'
        },
        {
          name: 'antonio',
          phone: '(48)99170-6401',
          email: 'antonio@email.com',
          register: 1234567,
          speciality: 'cardiologista',
          clinic: 'ONE',
          period: 'MORNING'
        }
      ],
    }
  },
  methods: {
    searchClicked() {
      //regra da pesquisa por get
    }
  }
}

</script>

<style scoped>
div {
  padding: 1% 5%;
}
</style>