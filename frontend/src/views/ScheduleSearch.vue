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
              calendar-width="100%"
              locale="pt"
              placeholder="Selecione uma data"
          ></b-form-datepicker>
          <b-button
              @click="searchByDate"
              class="inputbutton"
              variant="info"
              text-center
          >Pesquisar
          </b-button>
        </b-form>
      </div>

      <div id="name" class="geralInput">
        <b-form inline @submit="searchByName">
          <b-form-input
              class="inputfield"
              v-model="fieldName"
              placeholder="Buscar por paciente"
          ></b-form-input>
          <b-button
              @click="searchByName"
              class="inputbutton"
              variant="info"
              text-wrap>Pesquisar
          </b-button>
        </b-form>
      </div>
    </div>

    <div id="spinner">
      <b-spinner v-show="isScheduleTableLoading" style="width: 3rem; height: 3rem;" variant="primary"
                 label="Spinning"></b-spinner>
    </div>

    <div id="table">
      <h1 v-show="schedulingPage.empty && !isScheduleTableLoading">
        lista vazia
      </h1>
      <b-table
          v-show="!schedulingPage.empty && !isScheduleTableLoading"
          ref="tableschedule"
          bordered
          small
          hover
          responsive="sm"
          stacked="md"
          id="table"
          :items="schedulingPage.content"
          :fields="fields"
          :per-page="schedulingPage.numberOfElements"
      >
        <template #cell(show_details)="row">
          <b-button
              size="sm"
              variant="info"
              @click="row.toggleDetails"
              class="mr-2">
            {{ row.detailsShowing ? 'Cancelar' : 'Opções' }}
          </b-button>
        </template>

        <template #row-details="row">
          <b-card>
            <b-button
                v-b-modal.modal
                size="sm"
                variant="warning"
                @click="editSchedule(row.item)"
                class="mr-2"
            >
              Editar
            </b-button>&nbsp;

            <b-button
                size="sm"
                variant="danger"
                @click="deleteSchedule(row.item)"
                class="mr-2"
            >
              Apagar
            </b-button>

          </b-card>
        </template>
      </b-table>
    </div>
    <div id="pagination" v-show="!schedulingPage.empty">
      <b-pagination
          pills
          @change="handlePageChange"
          v-model="pageComponent"
          :total-rows="schedulingPage.totalElements"
          :number-of-pages="schedulingPage.totalPages"
          aria-controls="my-table"
          first-number
          last-number
          variant="dark">
      </b-pagination>
    </div>
  </div>
</template>

<script>
import ScheduleService from "../services/ScheduleService";
import { mapMutations, mapState } from "vuex";

export default {
  name: "Schedule",

  data() {
    return {
      fieldDate: '',
      fieldName: '',
      pageComponent: 1,
      page: 0,
      fields: [
        {
          key: 'show_details',
          label: ''
        },
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

  computed:
      mapState(['schedulingPage', 'isScheduleTableLoading']),

  methods: {
    ...mapMutations([
        'setScheduling',
        'setIsSchedulingEditing',
        'setIsDoctorFilled',
        'setIsPatientFilled'
    ]),

    searchByName(event) {
      event.preventDefault()
      ScheduleService.findByPatientName(this.fieldName)
    },

    searchByDate() {
      ScheduleService.findByDate(this.fieldDate)
    },

    handlePageChange(value) {
      this.pageComponent = value
      let objectParam = {page: this.pageComponent - 1}
      this.activeMethod(objectParam)
    },

    activeMethod(params) {
      if (this.$store.state.scheduleServiceMethod === 'findByDate') {
        ScheduleService.findByDate(this.fieldDate, params)
      } else if (this.$store.state.scheduleServiceMethod === 'findByPatientName') {
        ScheduleService.findByPatientName(this.fieldName, params)
      } else if (this.$store.state.scheduleServiceMethod === 'findByDateToday') {
        ScheduleService.findByDateToday(params)
      } else if (this.$store.state.scheduleServiceMethod === 'findByDateWeek') {
        ScheduleService.findByDateWeek(params)
      } else {
        ScheduleService.findByDateMonth(params)
      }
    },

    editSchedule(item) {
      this.setScheduling(item)
      this.setIsSchedulingEditing()
      this.setIsDoctorFilled()
      this.setIsPatientFilled()
    },

    deleteSchedule(item) {
      if(confirm('Tem certeza que quer continar? \n Essa Ação não pode ser desfeita')) {
        ScheduleService.delete(item.id)
      }
    },

  }
}
</script>

<style scoped>

#frame {
}

#buttondiv {
  display: grid;
  margin: 0.5% 1%;
  justify-content: end;

}

#newSchedule {
}

#searchItems {
  display: flex;
  width: 100%;
}

.geralInput {
  width: 50%;
  padding: 1%;
}

#datepicker {
}

#name {
}

.inputfield {
  width: 85%;
}

.inputbutton {
  width: 15%;
  white-space: normal;
}

#spinner {
  display: flex;
  justify-content: center;
  margin: 0.5%;
}

#table {
  margin-top: 0.5%;
  padding: 1%;
}

#pagination {
  margin: 1% 0;
  padding-top: 1%;
  display: flex;
  justify-content: center;
}

@media screen and (max-width: 720px) {
  #searchItems {
    flex-direction: column;
  }

  #buttondiv {
    justify-content: center;
    margin: 0.25%;
  }

  #newSchedule {
    width: 98vw;
  }

  .geralInput {
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