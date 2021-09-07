<template>
  <div>
    <!--
    <b-input-group size="lg">
      <b-form-input v-model="fieldName" placeholder="Nome do Médico"></b-form-input>
      <b-button @click="searchClicked" variant="info">Pesquisar</b-button>
    </b-input-group>
    -->
    <h1 id="title">Lista de Médicos Cadastrados</h1>

    <PersonTable
        :fields="fields"
        :items="doctorPage"
        :isEmptyList="false"
        :isFromDoctor="true"
        edit-route="/registerdoctor"/>
  </div>
</template>

<script>
import PersonTable from "../components/PersonTable";
import DoctorService from "../services/DoctorService";
import store from "../vuex-store";


export default {
  name: "DoctorSearch",
  components: {
    PersonTable
  },
  data() {
    return {
      fields: [
        {
          key:'show_details',
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
        }
      ],
    }
  },
  mounted() {
    DoctorService.findAll();
  },
  computed: {
    doctorPage() {
      return store.state.doctorPage
    }
  },
  methods: {

  }
}

</script>

<style scoped>
div {
  padding: 1%;
}
#title {
  margin-top: 0.5%;
  margin-left: 0.75%;
}
</style>