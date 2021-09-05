import http from "../http-common"
import store from "../vuex-store"

class PatientService {

    findAll(params) {
        http.get('/patient', params)
            .then(response => store.state.patientPage = response.data)
            .catch()
            .finally()
    }

    findByName(search, params) {
        http.get('/patient/' + search, params)
            .then(response => store.state.patientPage = response.data)
            .catch()
            .finally()
    }

    count() {
        http.get('/patient/quantity')
            .then(response => store.state.patientQuantity = response.data)
            .catch()
            .finally()
    }

    insert(params) {
        http.post('/patient', params)
            .then(response => console.log(response.data))
            .catch()
            .finally()
    }

    update(params) {
        http.put('/patient', params)
            .then(response => console.log(response.data))
            .catch()
            .finally()
    }

    delete(id) {
        http.delete('/patient/' + id)
            .then(response => console.log('paciente deletado: ' + response.status))
            .catch()
            .finally()
    }
}

export default new PatientService()


