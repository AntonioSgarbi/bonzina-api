<template>
  <div>
    <b-modal
        id="modal"
        title="BootstrapVue"
        button-size="lg"
        @ok="submit"
        @cancel="cancel"
        @show="updateData">

      <template #modal-header="{ close }">
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
                <b-button variant="info" @click="hide()">{{ patientFilled ? 'Trocar' : 'Inserir' }}</b-button>
              </router-link>
            </div>
          </div>
          <div id="doctor" class="div">
            <div class="name">
              <h2>Médico: {{ doctor.name }}</h2>
            </div>
            <div class="btnSwitch">
              <router-link to="/searchdoctor">
                <b-button variant="info" @click="hide()">{{ doctorFilled ? 'Trocar' : 'Inserir' }}</b-button>
              </router-link>
            </div>
          </div>
          <div class="div">
            <h2 class="labelpicker">Data: </h2>
            <b-form-datepicker id="datepicker" size="sm" v-model="fieldDate" class="mb-2"></b-form-datepicker>
          </div>
          <div class="div">
            <h2 class="labelpicker">Horário: </h2>
            <b-form-timepicker id="timepicker" size="sm" v-model:id="fieldTime" locale="en" class="mb-2"></b-form-timepicker>
          </div>
        </b-form>
      </template>

      <template #modal-footer="{ ok, cancel, hide }">
      <div id="btnAction">
        <b-button size="lg" variant="outline-success" @click="ok()" >
          Agendar
        </b-button>
        <b-button size="lg" variant="outline-danger" @click="cancel">
          Cancelar
        </b-button>
        <!-- Button with custom close trigger value -->
        <b-button size="lg" variant="outline-secondary" @click="clean">
          Limpar
        </b-button>
      </div>
      </template>
    </b-modal>
  </div>
</template>

<script>
import store from "@/vuex";

export default {
  name: "ScheduleRegister",
  props: {
    patientFilled: false,
    doctorFilled: false,
  },
  data() {
    return {
      patient: {
        name:'nomeComponente'
      },
      doctor: {
        name: 'nomeComponente'
      },
      fieldDate: '',
      fieldTime: '',

    }
  },
  mounted() {
    this.$root.$on('bv::modal::show', (bvEvent, modalId) => {
      this.patient = store.state.patient
      this.doctor = store.state.doctor
      console.log('Modal is about to be shown', bvEvent, modalId)
    })

  },
  computed() {
    this.patient = store.state.patient
    this.doctor = store.state.doctor
    console.log('computado')
  },
  methods: {
    submit() {
      console.log('submit')
      alert('submited')
    },
    cancel() {
      console.log('cancel')
      alert('canceled')
    },
    clean() {
      this.patient = {}
      this.doctor = {}
      this.fieldDate = ''
      this.fieldTime = ''
      this.patientFilled = false
      store.state.patient = {}
      store.state.doctor = {}
      console.log('limpo')
    },
    updateData() {
      this.patient = store.state.patient
      this.doctor = store.state.doctor
      console.log('trocado')
    }
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