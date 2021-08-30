<template>
  <div id="main">
    <b-form @submit="onSubmit" @reset="onReset">

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
        <!--    description="We'll never share your email with anyone else." -->
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
        <!--    description="We'll never share your email with anyone else." -->
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
        <!--    description="We'll never share your email with anyone else." -->
        <b-form-input
            id="input-4"
            v-model="form.address"
            type="tel"
            placeholder="Endereço"
            required
        ></b-form-input>
      </b-form-group>

      <b-form-group
          id="input-group-5"
          label="Data de Nascimento:"
          label-for="input-5">
        <!--    description="We'll never share your email with anyone else." -->
        <b-form-input
            id="input-5"
            v-model="form.birthdate"
            type="date"
            required
        ></b-form-input>
      </b-form-group>
      <div id="buttons">
        <b-button type="submit" variant="primary">Submit</b-button>
        <b-button type="reset" variant="danger">Reset</b-button>
      </div>
    </b-form>
  </div>

</template>

<script>
import store from "@/vuex";

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
    }
  },
  created() {
    if (store.state.fromEdit) {
      this.form.id = store.state.patient.id
      this.form.name = store.state.patient.name
      this.form.phone = store.state.patient.phone
      this.form.email = store.state.patient.email
      this.form.address = store.state.patient.address
      this.form.birthdate = store.state.patient.birthdate
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault()
      if (store.state.fromEdit) {
        //post pra editar
      } else {
        //post pra cadastrar
      }
      alert(JSON.stringify(this.form))
    },
    onReset(event) {
      event.preventDefault()
      store.state.fromEdit = false
      store.state.patient = null

      this.form.id = 0
      this.form.name = ''
      this.form.email = ''
      this.form.phone = ''
      this.form.address = ''
      this.form.birthdate = null
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
</style>

export default PatientRegister