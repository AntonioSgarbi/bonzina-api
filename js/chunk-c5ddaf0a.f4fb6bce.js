(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-c5ddaf0a"],{"0961":function(e,t,a){},"5aca":function(e,t,a){"use strict";a("0961")},"6f72":function(e,t,a){"use strict";a.r(t);var s=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("b-input-group",{attrs:{size:"lg"}},[a("b-form-input",{attrs:{placeholder:"Nome do Paciente"},on:{keypress:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.findPatient.apply(null,arguments)}},model:{value:e.fieldName,callback:function(t){e.fieldName=t},expression:"fieldName"}}),a("b-button",{attrs:{variant:"info"},on:{click:e.findPatient}},[e._v(" Pesquisar ")])],1),a("PersonTable",{attrs:{fields:e.fields,items:e.patientPage.content,"is-list-empty":e.patientPage.empty,enabled:!0,"is-from-doctor":!1,"edit-route":"/registerpatient"},on:{pageChanged:e.findPatientFromPagination}})],1)},i=[],n=a("5530"),o=a("b94d"),r=a("e87a"),l=a("2f62"),c={name:"PatientSearch",components:{PersonTable:o["a"]},data:function(){return{isListEmpty:!1,fieldName:"",fields:[{key:"show_details",label:""},{key:"name",label:"Nome",sortable:!0},{key:"phone",label:"Telefone",sortable:!1},{key:"email",label:"Email",sortable:!1},{key:"address",label:"Endereço",sortable:!1},{key:"birthdate",label:"Data de Nascimento",sortable:!1}],pageComponent:1}},computed:Object(n["a"])({},Object(l["c"])(["patientPage","patientPageNumber"])),methods:Object(n["a"])(Object(n["a"])({},Object(l["b"])(["setPageComponentStore"])),{},{findPatient:function(){this.pageComponentStore(1),r["a"].findByName(this.fieldName)},findPatientFromPagination:function(){var e={page:this.patientPageNumber};r["a"].findByName(this.fieldName,e)}})},d=c,m=(a("5aca"),a("2877")),u=Object(m["a"])(d,s,i,!1,null,"c40af7f2",null);t["default"]=u.exports},a241:function(e,t,a){},b94d:function(e,t,a){"use strict";var s=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("h1",{directives:[{name:"show",rawName:"v-show",value:e.isListEmpty&&!this.$store.state.isPersonTableLoading,expression:"isListEmpty && !this.$store.state.isPersonTableLoading"}]},[e._v(" lista vazia ")]),a("div",{attrs:{id:"spinner"}},[a("b-spinner",{directives:[{name:"show",rawName:"v-show",value:this.$store.state.isPersonTableLoading,expression:"this.$store.state.isPersonTableLoading"}],staticStyle:{width:"3rem",height:"3rem"},attrs:{variant:"primary",label:"Spinning"}})],1),a("b-table",{directives:[{name:"show",rawName:"v-show",value:!e.isListEmpty&&!this.$store.state.isPersonTableLoading,expression:"!isListEmpty && !this.$store.state.isPersonTableLoading"}],ref:"selectableTable",attrs:{bordered:"",small:"",hover:"",responsive:"sm",stacked:"md",id:"my-table",items:e.items,fields:e.fields,"per-page":this.$store.state.patientPage.numberOfElements},scopedSlots:e._u([{key:"cell(show_details)",fn:function(t){return[a("b-button",{staticClass:"mr-2",attrs:{size:"sm",variant:"info"},on:{click:t.toggleDetails}},[e._v(" "+e._s(t.detailsShowing?"Cancelar":"Opções")+" ")])]}},{key:"row-details",fn:function(t){return[a("b-card",[a("b-button",{directives:[{name:"b-modal",rawName:"v-b-modal.modal",modifiers:{modal:!0}}],staticClass:"mr-2",attrs:{size:"sm",variant:"success"},on:{click:function(a){return e.toSchedule(t.item)}}},[e._v(" "+e._s(e.isSchedulingNow?"Inserir na consulta":"Criar Agendamento")+" ")]),e._v("  "),a("router-link",{attrs:{to:e.routeToEditPage,custom:""},scopedSlots:e._u([{key:"default",fn:function(s){var i=s.navigate;return[a("span",{attrs:{role:"link"},on:{click:i,keypress:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:i.apply(null,arguments)}}},[a("b-button",{staticClass:"mr-2",attrs:{variant:"warning",size:"sm"},on:{click:function(a){return e.toEdit(t.item)}}},[e._v(" Editar Cadastro ")])],1)]}}],null,!0)})],1)]}}])}),a("div",{directives:[{name:"show",rawName:"v-show",value:!e.isFromDoctor&&!e.isListEmpty,expression:"!isFromDoctor && !isListEmpty"}],attrs:{id:"pagination"}},[a("b-pagination",{attrs:{pills:"",enabled:!e.isFromDoctor,"total-rows":this.$store.state.patientPage.totalElements,"number-of-pages":this.$store.state.patientPage.totalPages,"aria-controls":"my-table","first-number":"","last-number":"",variant:"dark"},on:{change:e.handlePageChange},model:{value:e.pageComponentLocal,callback:function(t){e.pageComponentLocal=t},expression:"pageComponentLocal"}})],1)],1)},i=[],n=a("5530"),o=a("2f62"),r={name:"PersonTable",emits:["pageChanged"],props:{fields:[],items:[],isListEmpty:{type:Boolean,default:!1},isFromDoctor:{type:Boolean,default:!1}},data:function(){return{routeToEditPage:"",pageComponentLocal:1}},computed:Object(n["a"])({},Object(o["c"])(["isSchedulingNow","isPersonTableLoading","isSchedulingEditing","fromPerson"])),methods:Object(n["a"])(Object(n["a"])({},Object(o["b"])(["setPatient","setDoctor","setIsFromEdit","setIsDoctorFilled","setIsPatientFilled","setSchedulingDoctor","setSchedulingPatient","setIsPatientFilled","setIsSchedulingNow"])),{},{toSchedule:function(e){console.log(this.fromPerson),this.setIsSchedulingNow(),"fromPatient"===this.fromPerson?(this.setIsPatientFilled(),this.setSchedulingPatient(e),this.setPatient(e)):(this.setIsDoctorFilled(),this.setSchedulingDoctor(e),console.log(e),this.setDoctor(e))},toEdit:function(e){this.setIsFromEdit(),"fromPatient"===this.$store.state.fromPerson?(console.log("from patient "+e),console.log(e),this.setPatient(e),this.routeToEditPage="registerpatient"):(console.log("from doctor "+e),console.log(e),this.setDoctor(e),this.routeToEditPage="registerdoctor")},handlePageChange:function(e){this.pageComponentLocal=e,this.$store.state.patientPageNumber=e-1,this.$emit("pageChanged")}})},l=r,c=(a("e887"),a("2877")),d=Object(c["a"])(l,s,i,!1,null,"548b8458",null);t["a"]=d.exports},e887:function(e,t,a){"use strict";a("a241")}}]);
//# sourceMappingURL=chunk-c5ddaf0a.f4fb6bce.js.map