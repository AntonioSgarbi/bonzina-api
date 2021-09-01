<template>
  <b-navbar mx-auto pills justified sticky toggleable="lg" type="dark" variant="dark">
    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-navbar-brand to="/">Home</b-navbar-brand>
    <b-collapse id="nav-collapse" is-nav>
      <b-navbar-nav>
        <b-nav-item to="/schedule" @click="reset">Consultas</b-nav-item>
        <b-nav-item-dropdown text="Paciente">
          <b-dropdown-item to="/searchpatient" @click="reset('fromPatient')">Pesquisar</b-dropdown-item>
          <b-dropdown-item to="/registerpatient" @click="reset">Cadastrar</b-dropdown-item>
        </b-nav-item-dropdown>
        <b-nav-item-dropdown text="Médico">
          <b-dropdown-item to="/searchdoctor" @click="reset('fromDoctor')">Pesquisar</b-dropdown-item>
          <b-dropdown-item to="/registerdoctor" @click="reset">Cadastrar</b-dropdown-item>
        </b-nav-item-dropdown>
        <b-nav-item to="/about">Sobre</b-nav-item>
      </b-navbar-nav>
    </b-collapse>
  </b-navbar>
</template>

<script>
import store from "../vuex";

export default {
  name: 'NavBar',
  methods: {
    reset(person) {
      store.state.patient = { name: ''}
      store.state.doctor = { name: ''}
      store.state.fromPerson = person
      store.state.fromEdit = false
    }
  }
}
</script>

<style scoped>

</style>