import http from "../http-common"
import store from "../vuex-store"

class PatientService {

    findAll(params) {
        http.get('/patient', params)
            .then(response => {
                store.state.patientPage = response.data
                console.log('request status: ' + response.status)
            })
            .catch(() =>  console.log('erro na resposta'))
            .finally()
    }

    findByName(search, params) {
        store.state.isPersonTableLoading = true
        http.get('/patient/' + search, {params})
            .then(response => {
                store.state.patientPage = response.data
                store.state.isPersonTableLoading = false
                console.log('request status: ' + response.status)
            })
            .catch(() =>  console.log('erro na resposta'))
            .finally()
    }

    count() {
        http.get('/patient/quantity')
            .then(response => {
                store.state.patientQuantity = response.data
                console.log('request status: ' + response.status)
            })
            .catch(() =>  console.log('erro na resposta'))
            .finally()
    }

    insert(params) {
        http.post('/patient', params)
            .then(response => console.log(response.data))
            .catch(() =>  console.log('erro na resposta'))
            .finally()
    }

    update(params) {
        http.put('/patient', params)
            .then(response => console.log(response.data))
            .catch(() =>  console.log('erro na resposta'))
            .finally()
    }

    delete(id) {
        http.delete('/patient/' + id)
            .then(response => {
                alert('paciente deletado!')
                console.log('request status: ' + response.status)
            })
            .catch(() =>  console.log('erro na resposta'))
            .finally()
    }
}

export default new PatientService()


