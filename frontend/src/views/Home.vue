<template>
  <div id="mainbox" class="maindiv">
    <div id="primary" class="maindiv">
      <h1>
        Consultas agendadas
      </h1>
    </div>

    <div id="secondary" class="maindiv">
      <h1>
        <router-link :to="routeSchedule" custom v-slot="{ navigate }">
          <span @click="navigate" @keypress.enter="navigate" role="link">
            <b-badge id="badge" @click="today">hoje:{{ fromToday }}</b-badge>&nbsp;
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
</template>

<script>
import axios from 'axios';
import store from "@/vuex";

export default {
  name: 'Home',

  data() {
    return {
      fromToday: 0,
      fromWeek: 0,
      fromMonth: 0,
      routeSchedule: '/schedule'
    }
  },

  components: {},

  async created() {
    store.state.schedulings = this.simuleData()
    //corrigir esse metodo com a estrutura correta
    let response = await [1, 2, 3]//axios.get("http://localhost:8081/scheduling/quantity")
    //.catch(console.log('capturando falha no retorno do endpoint de quantidade da api'))
    this.fromToday = response[0]
    this.fromWeek = response[1]
    this.fromMonth = response[2]

  },

  methods: {
    async today() {
      /*
      await axios.get("http://localhost:8080/scheduling/today")
      .then(function(response) {
        store.state.scheduling = reponse
        })
      .catch(console.log('tratar erro'))
       */

      store.state.schedulings = this.simuleData()
    },
    week() {
      /*
      await axios.get("http://localhost:8080/scheduling/week")
      .then(function(response) {
        store.state.scheduling = reponse
        })
      .catch(console.log('tratar erro'))
       */
      window.alert('clicou no  semana')
    },
    month() {
      /*
      await axios.get("http://localhost:8080/scheduling/month")
      .then(function(response) {
        store.state.scheduling = reponse
        })
      .catch(console.log('tratar erro'))
       */
      window.alert('clicou no mẽs')
    },
    simuleData() {
      var content = [
        {
          date: '2021-08-20',
          doctor: {
            name: 'antonio home',
            phone: '(48)99170-6401',
            email: 'antonio@email.com',
            register: 1234567,
            speciality: 'cardiologista',
            clinic: 'ONE',
            period: 'MORNING'
          },
          patient: {
            name: 'antonio home',
            birthdate: null,
            address: null,
            phone: null,
            email: null
          }
        }
      ]
      return content
    }
  }
}
</script>

<style>
.maindiv {
  /*  display: flex; */
  width: 85%;
  margin: 2% 7.5%;
  border: solid black;
  justify-content: center;
  text-align: center;
}

#primary {

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
