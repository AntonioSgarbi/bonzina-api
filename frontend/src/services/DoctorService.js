import http from "../http-common"
import store from "../vuex-store"

class DoctorService {
    doctorQuantity;
    findAll() {
        http.get('/doctor')
            .then(response => store.state.doctorPage = response.data)
            .catch()
            .finally()
    }

    count() {
        http.get('/doctor/quantity')
            .then(response => store.state.doctorQuantity = response.data)
            .catch()
            .finally()
    }

    insert(params) {
        http.post('/doctor', params)
            .then(response => console.log(response.data))
            .catch()
            .finally()
    }

    update(params) {
        http.put('/doctor', params)
            .then(response => console.log(response.data))
            .catch()
            .finally()
    }

    delete(id) {
        http.delete('/doctor/' + id)
            .then(response => console.log(response.status))
            .catch()
            .finally()
    }


}

export default new DoctorService()