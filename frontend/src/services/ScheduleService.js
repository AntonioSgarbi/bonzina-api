import http from "../http-common";
import store from "../vuex-store";

class ScheduleService {

    findByPatientName(search, params) {
        store.state.scheduleServiceMethod = 'findByPatientName'
        http.get("/scheduling/bypatient/" + search, {params})
            .then(response => store.state.schedulingPages = response.data)
            .catch()
            .finally()
    }

    findByDate(search, params) {
        store.state.scheduleServiceMethod = 'findByDate'
        http.get("/scheduling/bydate/"+ search, params)
            .then(response => store.state.schedulingPages = response.data)
            .catch()
            .finally()
    }

    findByDateToday(params) {
        store.state.scheduleServiceMethod = 'findByDateToday'
        http.get("/scheduling/bydate/today", {params})
            .then(response => store.state.schedulingPages = response.data)
            .catch()
            .finally()
    }

    findByDateWeek(params) {
        store.state.scheduleServiceMethod = 'findByDateWeek'
        http.get("/scheduling/bydate/week", {params})
            .then(response => store.state.schedulingPages = response.data)
            .catch()
            .finally()
    }

    findByDateMonth(params) {
        store.state.scheduleServiceMethod = 'findByDateMonth'
        http.get("/scheduling/bydate/month", {params})
            .then(response => store.state.schedulingPages = response.data)
            .catch()
            .finally()
    }

    count() {
        http.get("/scheduling/quantity")
            .then(response => store.state.schedulingQuantity = response.data)
            .catch()
            .finally()
    }

    countByDate() {
        http.get("/scheduling/quantitybydate")
            .then(response => store.state.counterSchedule = response.data)
            .catch()
            .finally()
    }

    insert(params) {
        alert(params)
        http.post("/scheduling", params)
            .then(response => store.state.counterSchedule = response.data)
            .catch()
            .finally()
    }

    update(params) {
        http.put("/scheduling",  params)
            .then(response => store.state.counterSchedule = response.data)
            .catch()
            .finally()
    }

    delete(id) {
        http.delete("/scheduling/" + id)
            .then(response => store.state.counterSchedule = response.data)
            .catch()
            .finally()
    }

}

export default new ScheduleService()