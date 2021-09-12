<template>
  <div>
    <b-modal
        id="modal"
        title="BootstrapVue"
        button-size="lg"
        @ok="submit"
        @cancel="cancel"
        @show="verifyStore">

      <template #modal-header="{ }">
        <h3>{{ isSchedulingEditing ? 'Editar' : 'Cadastrar' }} Consulta</h3>
      </template>

      <template #default="{ hide }">
        <b-form>
          <div id="patient" class="div">
            <div class="name">
              <h2>Paciente: {{ scheduling.patient.name }}</h2>
            </div>
            <div class="btnSwitch">
              <router-link to="/searchpatient">
                <b-button
                    variant="info"
                    @click="hide(), setFromPerson('fromPatient')">
                  {{ isPatientFilled ? 'Trocar' : 'Inserir' }}
                </b-button>
              </router-link>
            </div>
          </div>
          <div id="doctor" class="div">
            <div class="name">
              <h2>Médico: {{ scheduling.doctor.name }}</h2>
            </div>
            <div class="btnSwitch">
              <router-link to="/searchdoctor">
                <b-button
                    variant="info"
                    @click="hide(), setFromPerson('fromDoctor')"
                >
                  {{ isDoctorFilled ? 'Trocar' : 'Inserir' }}
                </b-button>
              </router-link>
            </div>
          </div>
          <div class="div">
            <h2 class="labelpicker">Data: </h2>
            <b-form-datepicker
                id="datepicker"
                size="sm"
                v-model="scheduling.date"
                locale="pt"
                placeholder="Selecione uma data"
                class="mb-2"
            ></b-form-datepicker>
          </div>
          <div class="div">
            <h2 class="labelpicker">Horário: </h2>
            <b-form-timepicker
                id="timepicker"
                v-model="scheduling.scheduled"
                size="sm"
                locale="pt"
                placeholder="Selecione um horário"
                class="mb-2"
            ></b-form-timepicker>
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
import { mapMutations, mapState } from "vuex"
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
      'isPatientFilled',
      'isDoctorFilled',
      'isSchedulingEditing',
      'scheduling'
    ]),
  },

  methods: {
    ...mapMutations([
       'setPatient',
       'setDoctor',
        'resetEditing',
        'setIsSchedulingNow',
        'setIsSchedulingEditing'
    ]),

    verifyStore() {
      this.setIsSchedulingNow()
      this.setPatient(this.scheduling.patient)
      this.setDoctor(this.scheduling.doctor)
      if(this.isSchedulingEditing) {
        this.scheduled = this.scheduling.scheduled
        this.date = this.scheduling.date
      }
    },

    submit(e) {
      if(this.scheduling.date !== '' && this.scheduling.scheduled !== '' && this.isPatientFilled && this.isDoctorFilled) {
        if(this.isSchedulingEditing) {
          ScheduleService.update(this.scheduling.id , this.scheduling.date, this.scheduling.scheduled, this.scheduling.doctor, this.scheduling.patient)
        } else {
          ScheduleService.insert(this.scheduling.date, this.scheduling.scheduled, this.scheduling.doctor, this.scheduling.patient)
        }
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
      this.resetEditing()
    },
    setFromPerson(person) {
      this.$store.state.fromPerson = person
      if(person === 'fromDoctor') DoctorService.findAll()
      if(person === 'fromPatient') PatientService.findByName('')
    },
  },
}
</script>

<style scoped>
.div {
  background-color: #ccffee;
  display: flex;
  width: 100%;
  align-items: center;
  margin-bottom: 1%;
  padding: 0.5%;
  border-radius: 5px;
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