<template>
  <div id="app">
    <NavBar/>
    <router-view/>
  </div>
</template>
<script>
  import PatientTable from "@/components/PatientTable";
  import NavBar from '@/components/NavBar.vue'
  export default {
    components: {
      NavBar,
      PatientTable
    },
  }
</script>

