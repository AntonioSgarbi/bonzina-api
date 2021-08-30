import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)


const store = new Vuex.Store({
    state: {
        schedulings: [],
        patients: [],
        doctors: [],
        patient: {name: 'vazioVuex'},
        doctor: {name: 'vazioVuex'},
        fromEdit: false,
        fromPerson: '' //'patient' || 'doctor'
    },
    mutations: {
    }
})

export default store