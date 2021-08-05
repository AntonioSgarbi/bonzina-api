<template>
  <b-navbar mx-auto pills justified sticky=true toggleable="lg" type="dark" variant="dark">
    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-navbar-brand to="/">Home</b-navbar-brand>
    <b-collapse id="nav-collapse" is-nav>
      <b-navbar-nav>
        <b-nav-item to="/schedule">Agendar</b-nav-item>
        <b-nav-item-dropdown text="Cadastrar">
          <b-dropdown-item to="/registerpatient">Paciente</b-dropdown-item>
          <b-dropdown-item to="/registerdoctor">Médico</b-dropdown-item>
        </b-nav-item-dropdown>
        <b-nav-item-dropdown text="Pesquisar">
          <b-dropdown-item to="/searchpatient">Paciente</b-dropdown-item>
          <b-dropdown-item to="/searchdoctor">Médico</b-dropdown-item>
        </b-nav-item-dropdown>
        <b-nav-item to="/about">Sobre</b-nav-item>
      </b-navbar-nav>
    </b-collapse>
  </b-navbar>
</template>

<script>
export default {
  name: 'NavBar',
}
</script>

<style scoped>

</style>