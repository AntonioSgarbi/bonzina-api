import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)


const store = new Vuex.Store({
    state: {

        counterSchedule: {
            fromDay: 0,
            fromWeek: 0,
            fromMonth: 0
        },
        schedulingPages: {
            content: [
                {
                    id: 0,
                    date: "",
                    scheduled: "",
                    doctor: {
                        id: 0,
                        name: "",
                        phone: "",
                        email: "",
                        register: 0,
                        speciality: "",
                        clinic: "",
                        period: "",
                        schedulings: null
                    },
                    patient: {
                        id: 0,
                        name: "",
                        phone: "",
                        email: "",
                        address: "",
                        birthdate: "",
                        schedulings: null
                    }
                }],
            pageable: {
                sort: {
                    sorted: false,
                    unsorted: true,
                    empty: true
                },
                pageNumber: 0,
                pageSize: 0,
                offset: 0,
                paged: true,
                unpaged: false
            },
            last: true,
            totalPages: 0,
            totalElements: 0,
            first: true,
            size: 0,
            number: 0,
            sort: {
                sorted: false,
                unsorted: true,
                empty: true
            },
            numberOfElements: 8,
            empty: false
        },
        schedulingQuantity: 0,
        scheduleServiceMethod: "",

        patientPage: [],
        patientQuantity: 0,
        doctorPage: [],
        patient: {name: 'vazioVuex'},
        doctor: {name: 'vazioVuex'},
        fromEdit: false,
        fromPerson: '', //'fromPatient' || 'fromDoctor'
    },
    mutations: {
    }
})

export default store