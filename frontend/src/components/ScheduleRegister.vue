<template>
  <div>
    <b-modal
        id="modal"
        title="BootstrapVue"
        button-size="lg"
        @ok="submit"
        @cancel="cancel"
        @show="isSchedulingNow = true">

      <template #modal-header="{ }">
        <h3>Cadastrar Consulta</h3>
      </template>

      <template #default="{ hide }">
        <b-form>
          <div id="patient" class="div">
            <div class="name">
              <h2>Paciente: {{ patient.name }}</h2>
            </div>
            <div class="btnSwitch">
              <router-link to="/searchpatient">
                <b-button
                    variant="info"
                    @click="hide(), setFromPerson('fromPatient')">
                  {{ patientFilled ? 'Trocar' : 'Inserir' }}
                </b-button>
              </router-link>
            </div>
          </div>
          <div id="doctor" class="div">
            <div class="name">
              <h2>Médico: {{ doctor.name }}</h2>
            </div>
            <div class="btnSwitch">
              <router-link to="/searchdoctor">
                <b-button
                    variant="info"
                    @click="hide(), setFromPerson('fromDoctor')">
                  {{ doctorFilled ? 'Trocar' : 'Inserir' }}
                </b-button>
              </router-link>
            </div>
          </div>
          <div class="div">
            <h2 class="labelpicker">Data: </h2>
            <b-form-datepicker
                id="datepicker"
                size="sm"
                v-model="date"
                locale="pt"
                class="mb-2">
            </b-form-datepicker>
          </div>
          <div class="div">
            <h2 class="labelpicker">Horário: </h2>
            <b-form-timepicker
                id="timepicker"
                size="sm"
                locale="pt"
                class="mb-2"></b-form-timepicker>
          </div>
        </b-form>
      </template>

      <template #modal-footer="{ ok, cancel }">
      <div id="btnAction">
        <b-button size="lg" variant="outline-success" @click="ok()" >
          Agendar
        </b-button>
        <b-button size="lg" variant="outline-danger" @click="cancel">
          Cancelar
        </b-button>
        <b-button size="lg" variant="outline-secondary" @click="clean">
          Limpar
        </b-button>
      </div>
      </template>
    </b-modal>
  </div>
</template>

<script>


import ScheduleService from "../services/ScheduleService"
import {mapGetters, mapState} from "vuex"
import DoctorService from "../services/DoctorService";
import PatientService from "../services/PatientService";

export default {
  name: "ScheduleRegister",
  data() {
    return {
      date: '',
      scheduled: '',
    }
  },

  computed: {
    ...mapState([
      'patient',
      'doctor',
      'patientFilled',
      'doctorFilled',
    ]),
    ...mapGetters({
      isSchedulingNow: 'isSchedulingNow'
    }),
    isSchedulingNow: {
      get() {
        return this.$store.state.isSchedulingNow
      },
      set(newValue) {
        return newValue
      }
    }
  },

  methods: {
    submit(e) {
      if(this.date != '' && this.scheduled != '' && this.patientFilled && this.doctorFilled) {
        ScheduleService.insert(this.date, this.scheduled, this.doctor, this.patient)
        this.clean()
      } else {
        alert('Todos os campos devem estar preenchidos!')
        e.preventDefault()
      }
    },
    cancel() {
      this.clean()
    },
    clean() {
      this.date = ''
      this.scheduled = ''
      this.$store.state.patient = {}
      this.$store.state.doctor = {}
      this.$store.state.patientFilled = false
      this.$store.statedoctorFilled = false
    },
    setFromPerson(person) {
      this.$store.state.fromPerson = person
      if(person === 'fromDoctor') DoctorService.findAll()
      if(person === 'fromPatient') PatientService.findByName('')
    },
  }
}
</script>

<style scoped>
.div {
  display: flex;
  width: 100%;
  align-items: center;
  margin-bottom: 1%;
  padding: 0.5%;
  border: dashed black;
}

.name {
  width: 80%;
}
.btnSwitch {
  display: flex;
  justify-content: flex-end;
  width: 20%;
  margin: 0.25%;
}

#patient {

}

#doctor {

}

.labelpicker {
  width: 40%;
}
#datepicker {
  width: 60%;
}
#timepicker {
  width: 60%;
}
#btnAction {
  display: flex;
  justify-content: flex-end;
}


</style>

export default ScheduleRegister