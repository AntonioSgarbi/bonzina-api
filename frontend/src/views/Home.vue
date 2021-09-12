<template>
  <div id="background-animation">
    <div class="generalclass">
      <div v-show="isServerOn" id="primary" class="generalclass">
        <h1>
          Consultas agendadas
        </h1>
      </div>
      <div  v-show="!isServerOn">
        <h2>Servidor Carregando, aguarde...</h2>
        <b-spinner style="width: 3rem; height: 3rem;" variant="primary" label="Spinning"></b-spinner>
      </div>
      <div v-show="isServerOn" id="secondary" class="generalclass">
        <h1>
          <router-link :to="routeSchedule" custom v-slot="{ navigate }">
          <span @click="navigate" @keypress.enter="navigate" role="link">
            <b-badge v-b-tooltip.hover title="Selecione" id="badge" @click="today">hoje:{{ fromDay }}</b-badge>&nbsp;
          </span>
          </router-link>

          <router-link :to="routeSchedule" custom v-slot="{ navigate }">
          <span @click="navigate" @keypress.enter="navigate" role="link">
          <b-badge v-b-tooltip.hover title="Selecione" id="badge" @click='week'>essa semana:{{ fromWeek }}</b-badge>&nbsp;
          </span>
          </router-link>

          <router-link :to="routeSchedule" custom v-slot="{ navigate }">
          <span @click="navigate" @keypress.enter="navigate" role="link">
            <b-badge v-b-tooltip.hover title="Selecione" id="badge" @click='month'>esse mês:{{ fromMonth }}</b-badge>
          </span>
          </router-link>
        </h1>
      </div>

    </div>
  </div>
</template>

<script>
import ScheduleService from "../services/ScheduleService"
import {mapState} from "vuex"

export default {
  name: 'Home',
  emits: [
    'contentread'
  ],
  data() {
    return {
      routeSchedule: '/schedule'
    }
  },

  created() {
    ScheduleService.countByDate()
  },

  computed: {
    ...mapState({
      fromDay: state => state.counterSchedule.fromDay,
      fromWeek: state => state.counterSchedule.fromWeek,
      fromMonth: state => state.counterSchedule.fromMonth,
      isServerOn: state => state.isServeOn
    }),
  },

  methods: {
    today() {
      ScheduleService.findByDateToday(null)
    },
    week() {
      ScheduleService.findByDateWeek(null)
    },
    month() {
      ScheduleService.findByDateMonth(null)
    },

  }
}
</script>

<style scoped>

#background-animation {
  height: 88.5vh;
  background: linear-gradient(218deg, #5dc558, #5fb0f2, #f924c2, #03dba3, #c5e322);
  background-size: 1000% 1000%;

  -webkit-animation: HomeAnimation 49s ease infinite;
  -moz-animation: HomeAnimation 49s ease infinite;
  -o-animation: HomeAnimation 49s ease infinite;
  animation: HomeAnimation 49s ease infinite;
}

@-webkit-keyframes HomeAnimation {
  0%{background-position:0 99%}
  50%{background-position:100% 2%}
  100%{background-position:0 99%}
}
@-moz-keyframes HomeAnimation {
  0%{background-position:0 99%}
  50%{background-position:100% 2%}
  100%{background-position:0 99%}
}
@keyframes HomeAnimation {
  0%{background-position:0 99%}
  50%{background-position:100% 2%}
  100%{background-position:0 99%}
}

.generalclass {
  padding: 2% 7.5%;
  justify-content: center;
  text-align: center;
}

#primary {
  margin-bottom: 3%;
}

#secondary {
}

h1 {
  color: black;
}

#badge {
  cursor: pointer;
}
</style>
