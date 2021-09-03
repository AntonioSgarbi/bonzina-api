<template>
  <div id="background-animation">
    <div class="generalclass">
      <div id="primary" class="generalclass">
        <h1>
          Consultas agendadas
        </h1>
      </div>

      <div id="secondary" class="generalclass">
        <h1>
          <router-link :to="routeSchedule" custom v-slot="{ navigate }">
          <span @click="navigate" @keypress.enter="navigate" role="link">
            <b-badge id="badge" @click="today">hoje:{{ fromDay }}</b-badge>&nbsp;
          </span>
          </router-link>

          <router-link :to="routeSchedule" custom v-slot="{ navigate }">
          <span @click="navigate" @keypress.enter="navigate" role="link">
          <b-badge id="badge" @click='week'>essa semana:{{ fromWeek }}</b-badge>&nbsp;
          </span>
          </router-link>

          <router-link :to="routeSchedule" custom v-slot="{ navigate }">
          <span @click="navigate" @keypress.enter="navigate" role="link">
            <b-badge id="badge" @click='month'>esse mês:{{ fromMonth }}</b-badge>
          </span>
          </router-link>
        </h1>
      </div>

    </div>
  </div>
</template>

<script>
import axios from 'axios';
import store from "../vuex";

export default {
  name: 'Home',
  emits: [
    'contentread'
  ],
  data() {
    return {
      fromDay: 0,
      fromWeek: 0,
      fromMonth: 0,
      routeSchedule: '/schedule'
    }
  },

  created() {
    axios.get("http://localhost:8081/scheduling/quantitybydate")
        .then(response => {
          this.fromDay = response.data.fromDay
          this.fromWeek = response.data.fromWeek
          this.fromMonth = response.data.fromMonth
        })
        .catch(e => {
          console.log('falha no created() Home \ncause: ' + e)
        })
  },

  methods: {
    today() {
      this.request('today')
    },
    week() {
      this.request('week')
    },
    month() {
      this.request('month')
    },
    request(param) {
      axios.get('http://localhost:8081/scheduling/bydate/' + param)
          .then(response => {
            store.state.schedulingPages = response.data
            console.log('reposta pronta')
          })
          .catch(e => {
            console.log('falha no week() Home \ncause: ' + e)
          })
          .finally(
              () => console.log('finally')
          )
    }
  }
}
</script>

<style scoped>

#background-animation {
  height: 100vh;
  background: linear-gradient(218deg, #5dc558, #5fb0f2, #f924c2, #03dba3, #c5e322);
  background-size: 1000% 1000%;

  -webkit-animation: HomeAnimation 49s ease infinite;
  -moz-animation: HomeAnimation 49s ease infinite;
  -o-animation: HomeAnimation 49s ease infinite;
  animation: HomeAnimation 49s ease infinite;
}

@-webkit-keyframes HomeAnimation {
  0%{background-position:0% 99%}
  50%{background-position:100% 2%}
  100%{background-position:0% 99%}
}
@-moz-keyframes HomeAnimation {
  0%{background-position:0% 99%}
  50%{background-position:100% 2%}
  100%{background-position:0% 99%}
}
@keyframes HomeAnimation {
  0%{background-position:0% 99%}
  50%{background-position:100% 2%}
  100%{background-position:0% 99%}
}

.generalclass {

  padding: 2% 7.5%;
  border: solid black;
  justify-content: center;
  text-align: center;
}

#primary {
  margin-bottom: 3%;
}

#secondary {
  border: solid blueviolet;
}

h1 {
  color: black;
}

#badge {
  cursor: pointer;
}
</style>
