import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Register from '@/components/Register'
import Dashboard from '@/components/Dashboard'
import AddParticipants from '@/components/AddParticipants'
import CreateProject from '@/components/CreateProject'
import CreateProjectCard from '@/components/CreateProjectCard'
import UpdateProjectCard from '@/components/UpdateProjectCard'
import MyProject from '@/components/MyProject'
import DepartmentProject from '@/components/DepartmentProject'
import ProjectManagement from '@/components/ProjectManagement'
import DoneProject from '@/components/DoneProject'
import RequestProject from '@/components/RequestProject'
import UpdateUser from '@/components/UpdateUser'
import UpdateParticipants from '@/components/UpdateParticipants'

Vue.use(Router)

var routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: Dashboard,
    meta: { requiresAuth: true }
  },
  {
    path: '/my-project',
    name: 'MyProject',
    component: MyProject,
    meta: { requiresAuth: true }
  },
  {
    path: '/create-project',
    name: 'CreateProject',
    component: CreateProject,
    meta: { requiresAuth: true }
  },
  {
    path: '/create-card',
    name: 'CreateProjectCard',
    component: CreateProjectCard,
    meta: { requiresAuth: true }
  },
  {
    path: '/update-card',
    name: 'UpdateProjectCard',
    component: UpdateProjectCard,
    meta: { requiresAuth: true }
  },
  {
    path: '/update-participants',
    name: 'UpdateParticipants',
    component: UpdateParticipants,
    meta: { requiresAuth: true }
  },
  {
    path: '/add-participants',
    name: 'AddParticipants',
    component: AddParticipants,
    meta: { requiresAuth: true }
  },
  {
    path: '/project-management',
    name: 'ProjectManagement',
    component: ProjectManagement,
    meta: { requiresAuth: true }
  },
  {
    path: '/department-project',
    name: 'DepartmentProject',
    component: DepartmentProject,
    meta: { requiresAuth: true }
  },
  {
    path: '/done-project',
    name: 'DoneProject',
    component: DoneProject,
    meta: { requiresAuth: true }
  },
  {
    path: '/request-project',
    name: 'RequestProject',
    component: RequestProject,
    meta: { requiresAuth: true }
  },
  {
    path: '/update-user',
    name: 'UpdateUser',
    component: UpdateUser,
    meta: { requiresAuth: true }
  }
]

export default new Router({
  routes
})
