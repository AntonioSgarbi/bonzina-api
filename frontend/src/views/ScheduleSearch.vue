<template>
  <div id="frame">
    <div id="buttondiv">
    <b-button v-b-modal.modal id="newSchedule" variant="success">Agendar Consulta</b-button>
    </div>
    <div id="searchItems">
      <div id="datepicker" class="geralInput">
        <b-form inline>
          <b-form-datepicker
              class="inputfield"
              v-model="fieldDate"
              menu-class="w-100"
              calendar-width="100%"
          ></b-form-datepicker>
          <b-button @click="searchByDate" class="inputbutton" variant="info" text-center>Pesquisar</b-button>
        </b-form>
        <p>Value: '{{ fieldDate }}'</p>
      </div>

      <div id="name" class="geralInput">
        <b-form inline>
          <b-form-input
              class="inputfield"
              v-model="fieldName"
              placeholder="Buscar por paciente">
          </b-form-input>
          <b-button @click="searchByName" class="inputbutton" variant="info" text-wrap>Pesquisar</b-button>
        </b-form>
        <div class="mt-2">Value: {{ fieldName }}</div>
      </div>
    </div>

    <div id="table">
      <b-table
          bordered
          small
          hover
          stacked="md"
          id="my-table"
          :items="schedules"
          :fields="fields"
          :per-page="perPage"
          :current-page="currentPage">
      </b-table>
    </div>
    <div id="pagination">
    <b-pagination
      v-model="currentPage"
      pills
      :total-rows="rows"
      aria-controls="my-table"
      first-number
      last-number
      variant="dark">
    </b-pagination>
    </div>
  </div>
</template>

<script>
import store from "../vuex";
import axios from "axios";

export default {
  name: "Schedule",

  data() {
    return {
      fieldDate: '',
      fieldName: '',
      schedules: [], //mudar pra page
      rows: 10,
      perPage: 20,
      currentPage: 1,
      fields: [
        {
          key: 'date',
          label: 'Data',
          sortable: true,
        },
        {
          key: 'patient.name',
          label: 'Paciente',
          sortable: true,
        },
        {
          key: 'doctor.name',
          label: 'Médico',
          sortable: true,
        }
      ],
    }
  },
  created() {
    this.schedules = store.state.schedules
  },
  methods: {
    async searchByName() {
      store.state.schedulings = await axios.get('link da api'+ this.fieldName);
    },
    async searchByDate() {
      store.state.schedulings = await axios.get('link-api/scheduling/' + this.fieldDate);
    }
  }
}
</script>

<style scoped>

  #frame {
    border: solid darkred;
  }

  #buttondiv {
    display: grid;
    margin: 0.25%;

    justify-content: end;

  }

  #newSchedule {

  }

  #searchItems {
    display: flex;
    width: 100%;
    border: solid black;
  }

  .geralInput {
    border: solid red;
    width: 50%;
    padding: 1%;
  }

  #datepicker {
    border: solid blue;
  }

  #name {
    border: solid green;
  }

  .inputfield {
    width: 85%;
  }

  .inputbutton {
    width: 15%;
    white-space: normal;
  }

  #table {
    border: solid darkslategray;
    margin-top: 0.5%;
    padding: 1%;
  }

  #pagination {
    margin: 1% 0%;
    padding-top: 1%;
    border: solid black;
    display: flex;
    justify-content: center;
  }

  @media screen and (max-width: 720px) {
    #searchItems{
      flex-direction: column;
    }
    #buttondiv {
      justify-content: center;
    }
    #newSchedule {
      width: 95vw;
    }
    .geralInput{
      width: 100%;
    }
    .inputfield {
      width: 74%;
    }
    .inputbutton {
      width: 26%;
    }
  }

</style>