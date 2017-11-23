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

// Link each url with vue components
export default new Router({
  routes: [
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
      component: Dashboard
    },
    {
      path: '/my-project',
      name: 'MyProject',
      component: MyProject
    },
    {
      path: '/create-project',
      name: 'CreateProject',
      component: CreateProject
    },
    {
      path: '/create-card',
      name: 'CreateProjectCard',
      component: CreateProjectCard
    },
    {
      path: '/update-card',
      name: 'UpdateProjectCard',
      component: UpdateProjectCard
    },
    {
      path: '/update-participants',
      name: 'UpdateParticipants',
      component: UpdateParticipants
    },
    {
      path: '/add-participants',
      name: 'AddParticipants',
      component: AddParticipants
    },
    {
      path: '/project-management',
      name: 'ProjectManagement',
      component: ProjectManagement
    },
    {
      path: '/department-project',
      name: 'DepartmentProject',
      component: DepartmentProject
    },
    {
      path: '/done-project',
      name: 'DoneProject',
      component: DoneProject
    },
    {
      path: '/request-project',
      name: 'RequestProject',
      component: RequestProject
    },
    {
      path: '/update-user',
      name: 'UpdateUser',
      component: UpdateUser
    }
  ]
})
