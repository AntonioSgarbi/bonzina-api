import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)


const store = new Vuex.Store({
    state: {
        schedules: [],
        patients: [],
        doctors: [],
        patient: {name: 'vazioVuex'},
        doctor: {name: 'vazioVuex'},
        fromEdit: false,
        fromPerson: '' //'fromPatient' || 'fromDoctor'
    },
    mutations: {
    }
})

export default store