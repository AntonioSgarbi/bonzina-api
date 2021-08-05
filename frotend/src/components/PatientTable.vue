<template>
  <b-table bordered small hover stacked="md" :items="patients" :fields="fields"></b-table>
</template>

<script>
export default {
  name: "PatientTable",
  data() {
    return {
      fields: [
        {
          key: 'name',
          label: 'Nome',
          sortable: true,
        },
        {
          key: 'birthdate',
          label: 'Data de Nascimento',
          sortable: true
        },
        {
          key: 'phone',
          label: 'Telefone',
          sortable: false
        },
        {
          key: 'address',
          label: 'Endereço',
          sortable: false
        }

      ],
      patients: [
          {name:'antonio', birthdate:'1997-12-03', phone:'(48)99170-6401', address:'minha ruaminha ruaminha ruaminha ruaminha ruaminha rua'},
          {name:'antonio1', birthdate:'1997-12-04', phone:'(48)99170-6401', address:'minha ruaminha ruaminha ruaminha ruaminha ruaminha rua'},
          {name:'antonio2', birthdate:'1997-12-05', phone:'(48)99170-6401', address:'minha ruaminha ruaminha ruaminha ruaminha ruaminha rua'},
          {name:'antonio3', birthdate:'1997-12-06', phone:'(48)99170-6401', address:'minha ruaminha ruaminha ruaminha ruaminha ruaminha rua'}
      ]
    }
  }
}
</script>

<style scoped>


</style>