import http from "../http-common"
import store from "../vuex-store"

class DoctorService {
    doctorQuantity;
    findAll() {
        store.state.isPersonTableLoading = true
        http.get('/doctor')
            .then(response => {
                store.state.doctorPage = response.data
                store.state.isPersonTableLoading = false
                console.log('request status: ' + response.status)
            })
            .catch(() =>  console.log('erro na resposta'))
            .finally()
    }

    count() {
        http.get('/doctor/quantity')
            .then(response => {
                store.state.doctorQuantity = response.data
                console.log('request status: ' + response.status)
            })
            .catch(() =>  console.log('erro na resposta'))
            .finally()
    }

    insert(params) {
        http.post('/doctor', params)
            .then(response => {
                console.log(response.data)
                alert('Cadastrado com sucesso!')
                console.log('request status: ' + response.status)
            })
            .catch(() =>  console.log('erro na resposta'))
            .finally()
    }

    update(params) {
        http.put('/doctor', params)
            .then(response => {
                console.log(response.data)
                alert('Editado com sucesso!')
            })
            .catch(() =>  console.log('erro na resposta'))
            .finally()
    }

    delete(id) {
        http.delete('/doctor/' + id)
            .then(response => {
                alert('Médico deletado!')
                console.log('request status: ' + response.status)
            })
            .catch(() =>  console.log('erro na resposta'))
            .finally()
    }

}

export default new DoctorService()