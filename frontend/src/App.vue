<template>
  <div id="app">
    <NavBar/>
    <ScheduleRegister />
    <router-view/>
    <Footer />
  </div>
</template>

<script>
  import NavBar from "./components/NavBar.vue";
  import ScheduleRegister from "./components/ScheduleRegister";
  import Footer from "./components/Footer";

  export default {
    components: {
      NavBar,
      ScheduleRegister,
      Footer,
    },
  }
</script>

<style>
  Footer {
    bottom: 0;
  }
</style>

