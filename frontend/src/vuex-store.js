import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)


const store = new Vuex.Store({
    state: {
        isServeOn: false,
        counterSchedule: {
            fromDay: 0,
            fromWeek: 0,
            fromMonth: 0
        },

        schedulingPage: {
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
        patientPage: {
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
        doctorPage: {
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
        patientQuantity: 0,

        scheduleServiceMethod: "",

        patientPageNumber: 0,

        pageComponentStore: 0,

        scheduling: {
            id: 0,
            scheduled: '',
            date: '',
            doctor: { name:'' },
            patient: { name: '' }
        },
        patient: {name: ''},
        doctor: {name: ''},

        isSchedulingEditing: false,
        isSchedulingNow: false,

        isFromEdit: false,
        fromPerson: '', //'fromPatient' || 'fromDoctor'

        isPatientFilled: false,
        isDoctorFilled: false,

        isPersonTableLoading: true,
        isScheduleTableLoading: true

    },

    mutations: {
        setScheduling(state, scheduling) {
          state.scheduling = scheduling
        },
        setSchedulingDoctor(state, newDoctor) {
            state.scheduling.doctor = newDoctor
        },
        setSchedulingPatient(state, newPatient) {
            state.scheduling.patient = newPatient
        },
        setPatient(state, newPatient) {
          state.patient = newPatient
        },
        setDoctor(state, newDoctor) {
            state.doctor = newDoctor
        },
        resetEditing(state) {
            state.patient = { name: ''}
            state.doctor = { name: ''}
            state.scheduling = {
                id: 0,
                scheduled: '',
                date: '',
                doctor: { name:'' },
                patient: { name: '' }
            }
            state.fromEdit = false
            state.isSchedulingNow = false
            state.isSchedulingEditing = false
            state.patientFilled = false
            state.dcotorFilled = false
        },
        setIsPatientFilled(state) {
            state.isPatientFilled = true
        },
        setIsDoctorFilled(state) {
            state.isDoctorFilled = true
        },
        setIsSchedulingNow(state) {
            state.isSchedulingNow = true
        },
        setIsSchedulingEditing(state) {
            state.isSchedulingEditing = true
        },
        setIsFromEdit(state) {
            state.isFromEdit = true
        },
        setPageComponentStore(state, number) {
            state.pageComponentStore = number
        }
    }
})

export default store