<template>
  <div id="app">
    <NavBar/>
    <ScheduleRegister />
    <router-view/>
  </div>
</template>
<script>
  import NavBar from '@/components/NavBar.vue'
  import ScheduleRegister from "@/components/ScheduleRegister";

  export default {
    components: {
      NavBar,
      ScheduleRegister,
    },
  }
</script>

