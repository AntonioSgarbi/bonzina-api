import http from "../http-common";
import store from "../vuex-store";

class ScheduleService {

    findByPatientName(search, params) {
        store.state.isScheduleTableLoading = true
        store.state.scheduleServiceMethod = 'findByPatientName'
        http.get("/scheduling/bypatient/" + search, {params})
            .then(response => {
                store.state.schedulingPage = response.data
                store.state.isScheduleTableLoading = false
                console.log('request status: ' + response.status)
            })
            .catch(() =>  console.log('erro na resposta'))
            .finally()
    }

    findByDate(search, params) {
        store.state.isScheduleTableLoading = true
        store.state.scheduleServiceMethod = 'findByDate'
        http.get("/scheduling/bydate/"+ search, {params})
            .then(response => {
                store.state.schedulingPage = response.data
                store.state.isScheduleTableLoading = false
                console.log('request status: ' + response.status)
            })
            .catch(() =>  console.log('erro na resposta'))
            .finally()
    }

    findByDateToday(params) {
        store.state.isScheduleTableLoading = true
        store.state.scheduleServiceMethod = 'findByDateToday'
        http.get("/scheduling/bydate/today", {params})
            .then(response => {
                store.state.schedulingPage = response.data
                store.state.isScheduleTableLoading = false
                console.log('request status: ' + response.status)
            })
            .catch(() =>  console.log('erro na resposta'))
            .finally()
    }

    findByDateWeek(params) {
        store.state.isScheduleTableLoading = true
        store.state.scheduleServiceMethod = 'findByDateWeek'
        http.get("/scheduling/bydate/week", {params})
            .then(response => {
                store.state.schedulingPage = response.data
                store.state.isScheduleTableLoading = false
                console.log('request status: ' + response.status)
            })
            .catch(() =>  console.log('erro na resposta'))
            .finally()
    }

    findByDateMonth(params) {
        store.state.isScheduleTableLoading = true
        store.state.scheduleServiceMethod = 'findByDateMonth'
        http.get("/scheduling/bydate/month", {params})
            .then(response => {
                store.state.schedulingPage = response.data
                store.state.isScheduleTableLoading = false
                console.log('request status: ' + response.status)
            })
            .catch(() =>  console.log('erro na resposta'))
            .finally()
    }

    count() {
        http.get("/scheduling/quantity")
            .then(response => {
                store.state.schedulingQuantity = response.data
                console.log('request status: ' + response.status)
            })
            .catch(() =>  console.log('erro na resposta'))
            .finally()
    }

    countByDate() {
        http.get("/scheduling/quantitybydate")
            .then(response => {
                store.state.counterSchedule = response.data
                console.log('request status: ' + response.status)
                store.state.isServeOn = true
            })
            .catch(() =>  console.log('erro na resposta'))
            .finally()
    }

    insert(date, scheduled, doctor, patient) {
        http.post("/scheduling", {date, scheduled, doctor, patient})
            .then(response => {
                store.state.counterSchedule = response.data
                console.log('request status: ' + response.status)
            })
            .catch(() =>  console.log('erro na resposta'))
            .finally()
    }

    update(date, scheduled, doctor, patient) {
        http.put("/scheduling", {date, scheduled, doctor, patient})
            .then(response => {
                store.state.counterSchedule = response.data
                console.log('request status: ' + response.status)
            })
            .catch(() =>  console.log('erro na resposta'))
            .finally()
    }

    delete(id) {
        http.delete("/scheduling/" + id)
            .then(response => {
                store.state.counterSchedule = response.data
                console.log('request status: ' + response.status)
            })
            .catch(() =>  console.log('erro na resposta'))
            .finally()
    }

}

export default new ScheduleService()