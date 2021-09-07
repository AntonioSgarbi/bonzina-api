<template>
  <div id="main">
    <h1>{{isFromEdit ? 'Editar Médico' : 'Cadastrar Médico'}}</h1>

    <b-form @submit="submitForm" @reset="cleanForm">

      <b-form-group
          id="input-group-1"
          label="Nome do Médico:"
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
          label="CRM:"
          label-for="input-4">
        <b-form-input
            id="input-4"
            v-model="form.register"
            placeholder="Cadastro do Conselho"
            required
        ></b-form-input>
      </b-form-group>

      <b-form-group
          id="input-group-6"
          label="Especialidade:"
          label-for="input-6">
        <b-form-input
            id="input-6"
            v-model="form.speciality"
            required
        ></b-form-input>
      </b-form-group>

      <b-form-group
          id="input-group-7"
          label="Consultório:"
          label-for="input-7">
        <b-form-select
            v-model="form.clinic"
            :options="optionsClinic"
            required
        ></b-form-select>
      </b-form-group>

      <b-form-group
          id="input-group-8"
          label="Período:"
          label-for="input-8">
        <b-form-select
            v-model="form.period"
            :options="optionsPeriod"
            required
        ></b-form-select>
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
import DoctorService from "../services/DoctorService";
import {mapState} from "vuex";

export default {
  name: "DoctorRegister",
  data() {
    return {
      optionsPeriod: [
        {value: null, text:'Selecione um Período'},
        {value: 0, text:'Matutino'},
        {value: 1, text:'Vespertino'},
      ],
      optionsClinic: [
        {value: null, text:'Selecione um Consultório'},
        {value: 0, text:'Consultório 1'},
        {value: 1, text:'Consultório 2'},
      ],
      form: {
        id: null,
        name: '',
        phone: '',
        email: '',
        register: '',
        speciality: '',
        period: null,
        clinic: null
      }
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
          DoctorService.update(this.form)
          this.cleanForm()
        } else alert('cancelado')
      } else DoctorService.insert(this.form)
    },
    cleanForm() {
      store.state.isFromEdit = false
      store.state.doctor = {}

      this.form.id = null
      this.form.name = ''
      this.form.email = ''
      this.form.phone = ''
      this.form.register = ''
      this.form.speciality = ''
      this.form.clinic = null,
      this.form.period = null
    },
    deletePerson() {
      alert('Tem certeza disso? \n \n Essa ação não pode ser desfeita!')
      DoctorService.delete(this.form.id)
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