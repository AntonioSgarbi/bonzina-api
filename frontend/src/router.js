import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from './views/Home.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
    meta: { title: 'Clinica Bonzina' }
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ './views/About.vue'),
    meta: { title: 'Sobre' }
  },
  {
    path: '/registerdoctor',
    name: 'DoctorRegister',
    component: () => import('./views/DoctorRegister.vue'),
    meta: { title: 'Cadastrar Médico' }
  },
  {
    path: '/registerpatient',
    name: 'PatientRegister',
    component: () => import('./views/PatientRegister.vue'),
    meta: { title: 'Cadastrar Paciente' }
  },
  {
    path: '/searchdoctor',
    name: 'DoctorSearch',
    component: () => import('./views/DoctorSearch.vue'),
    meta: { title: 'Procurar Médico' }
  },
  {
    path: '/searchpatient',
    name: 'PatientSearch',
    component: () => import('./views/PatientSearch.vue'),
    meta: { title: 'Procurar Paciente' }
  },
  {
    path: '/schedule',
    name: 'ScheduleSearch',
    component: () => import('./views/ScheduleSearch.vue'),
    meta: { title: 'Consultas' }
  },
  {
    path: '/registerschedule',
    name: 'ScheduleRegister',
    component: () => import('./components/ScheduleRegister.vue'),
    meta: { title: 'Agendar Consulta' }
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

const DEFAULT_TITLE = 'default title';
router.afterEach((to, from) => {
  document.title = to.meta.title || DEFAULT_TITLE;
});

export default router
