<template>
  <div>

    <p>
      Selected Rows:<br>
      {{ selected }}
    </p>

    <b-table
        hover
        :items="items"
        :fields="fields"
        ref="selectableTable"
        responsive="sm"
    >
      <template #cell(show_details)="row" >
        <b-button size="sm" @click="row.toggleDetails" class="mr-2" >
          {{ row.detailsShowing ? 'Cancelar' : 'Opções' }}
        </b-button>
      </template>

      <template #row-details="row">
        <b-card>
              <b-button v-b-modal.modal size="sm" @click="toSchedule(row.item)">Agendar Consulta</b-button>&nbsp;

          <router-link :to="editRoute" custom v-slot="{ navigate }">
            <span @click="navigate" @keypress.enter="navigate" role="link">
              <b-button size="sm" @click="toEdit(row.item)">Editar Cadastro</b-button>
            </span>
          </router-link>
        </b-card>
      </template>
    </b-table>
  </div>
</template>

<script>
import store from "@/vuex";

export default {
  name: "PersonTable",
  emits: [],
  props: {
    fields: [],
    items: [],
    editRoute: ''
  },
  data() {
    return {
      selected: []
    }
  },
  created() {
  },
  methods: {
    toSchedule(item) {
      store.state.patient = item
      cd 
      console.log('item ' + item) //test
    },
    toEdit(item) {
      console.log(item) //test
      store.state.fromEdit = true
      store.state.patient = item
    }
  }
}
</script>

export default PersonTable