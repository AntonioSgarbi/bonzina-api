<template>
  <div id="main">
  <h1>{{isFromEdit ? 'Editar Paciente' : 'Cadastrar Paciente'}}</h1>
    <b-form @submit="submitForm" @reset="cleanForm">

      <b-form-group
          id="input-group-1"
          label="Nome do Paciente:"
          label-for="input-1">
        <b-form-input
            id="input-1"
            v-model="form.name"
            placeholder="Nome completo"
            required
        ></b-form-input>
      </b-form-group>

      <b-form-group
          id="input-group-2"
          label="Endereço de Email:"
          label-for="input-2">
        <b-form-input
            id="input-2"
            v-model="form.email"
            type="email"
            placeholder="Email"
            required
        ></b-form-input>
      </b-form-group>

      <b-form-group
          id="input-group-3"
          label="Telefone:"
          label-for="input-3">
        <b-form-input
            id="input-3"
            v-model="form.phone"
            type="tel"
            placeholder="Telefone"
            required
        ></b-form-input>
      </b-form-group>

      <b-form-group
          id="input-group-4"
          label="Endereço:"
          label-for="input-4">
        <b-form-input
            id="input-4"
            v-model="form.address"
            type="tel"
            placeholder="Endereço"
            required
        ></b-form-input>
      </b-form-group>

      <b-form-group
          id="datepicker-group"
          label="Data de Nascimento:"
          label-for="datepicker">
        <b-form-datepicker
            id="datepicker"
            v-model="form.birthdate"
            locale="pt"
            placeholder="Selecione um data"
            required
        ></b-form-datepicker>
      </b-form-group>
      <div id="buttons">
        <b-button class="bbutton" type="submit" variant="primary">Submit</b-button>
        <b-button class="bbutton" type="reset">Reset</b-button>
        <b-button class="bbutton" @click="deletePerson" variant="outline-danger">Excluir</b-button>

      </div>
    </b-form>
  </div>

</template>

<script>
import store from "../vuex-store";
import PatientService from "../services/PatientService";
import {mapState} from "vuex";

export default {
  name: "PatientRegister",

  data() {
    return {
      form: {
        id: null,
        name: '',
        phone: '',
        email: '',
        address: '',
        birthdate: '',
      },
      patient: {
        name: "maicon",
        phone: "4891912121",
        email: "jacinto@leite.com",
        address: "rua qualquer",
        birthdate: "2007-02-07"
      }
    }
  },
  created() {
    if (store.state.isFromEdit) {
      this.form.id = store.state.patient.id
      this.form.name = store.state.patient.name
      this.form.phone = store.state.patient.phone
      this.form.email = store.state.patient.email
      this.form.address = store.state.patient.address
      this.form.birthdate = store.state.patient.birthdate
    }
  },
  computed: {
    ...mapState(['isFromEdit'])
  },

  methods: {
    submitForm(event) {
      event.preventDefault()
      if (store.state.isFromEdit) {
        if(confirm('Tem certeza que quer continar? \n Essa Ação não pode ser desfeita')){
          PatientService.update(this.form)
          this.cleanForm()
        } else alert('cancelado')
      } else PatientService.insert(this.form)
    },
    cleanForm() {
      store.state.isFromEdit = false
      store.state.patient = {}

      this.form.id = null
      this.form.name = ''
      this.form.email = ''
      this.form.phone = ''
      this.form.address = ''
      this.form.birthdate = null
    },
    deletePerson() {
      alert('Tem certeza disso? \n \n Essa ação não pode ser desfeita!')
      PatientService.delete(this.form.id)
      this.cleanForm()
    }
  }
}
</script>

<style scoped>
#main {
  padding: 2%;
}

#buttons {
  display: flex;
  justify-content: center;
}
.bbutton {
  margin: 2px;
}
</style>

export default PatientRegister