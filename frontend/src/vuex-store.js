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

        patient: {name: ''},
        doctor: {name: ''},

        isSchedulingNow: false,

        isFromEdit: false,
        fromPerson: '', //'fromPatient' || 'fromDoctor'

        patientFilled: false,
        doctorFilled: false,

        isPersonTableLoading: true,
        isScheduleTableLoading: true

    },

    getters: {
        pageComponentStore: (state) => {
            return state.pageComponentStore;
        },
        isSchedulingNow: (state) => {
            return state.isSchedulingNow;
        }
    }
})

export default store