<template>
  <b-navbar mx-auto pills justified sticky toggleable="lg" type="dark" variant="dark">
    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-navbar-brand to="/">Home</b-navbar-brand>
    <b-collapse id="nav-collapse" is-nav>
      <b-navbar-nav>
        <b-nav-item to="/schedule" @click="reset('fromSchedule')">Consultas</b-nav-item>
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
import ScheduleService from "../services/ScheduleService";
import DoctorService from "../services/DoctorService";
import PatientService from "../services/PatientService";
import { mapMutations } from "vuex"

export default {
  name: 'NavBar',
  methods: {
    ...mapMutations(['resetEditing']),

    reset(entityRoute) {
      this.resetEditing()
      this.$store.state.fromPerson = entityRoute
      if(entityRoute === 'fromSchedule') ScheduleService.findByDateToday()
      if(entityRoute === 'fromDoctor') DoctorService.findAll()
      if(entityRoute === 'fromPatient') PatientService.findByName('')
    }
  }
}
</script>

<style scoped>

</style>