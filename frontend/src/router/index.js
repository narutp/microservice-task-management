import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Register from '@/components/Register'
import Home from '@/components/Home'
import AddParticipants from '@/components/AddParticipants'
import CreateTask from '@/components/CreateTask'
import CreateTaskCard from '@/components/CreateTaskCard'
import UpdateTaskCard from '@/components/UpdateTaskCard'
import MyTask from '@/components/MyTask'
import DepartmentTask from '@/components/DepartmentTask'
import TaskManagement from '@/components/TaskManagement'
import DoneTask from '@/components/DoneTask'
import RequestProject from '@/components/RequestProject'

Vue.use(Router)

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
      path: '/home',
      name: 'Home',
      component: Home
    },
    {
      path: '/my-project',
      name: 'MyTask',
      component: MyTask
    },
    {
      path: '/create-project',
      name: 'CreateTask',
      component: CreateTask
    },
    {
      path: '/create-card',
      name: 'CreateTaskCard',
      component: CreateTaskCard
    },
    {
      path: '/update-card',
      name: 'UpdateTaskCard',
      component: UpdateTaskCard
    },
    {
      path: '/add-participants',
      name: 'AddParticipants',
      component: AddParticipants
    },
    {
      path: '/project-management',
      name: 'TaskManagement',
      component: TaskManagement
    },
    {
      path: '/department-project',
      name: 'DepartmentTask',
      component: DepartmentTask
    },
    {
      path: '/done-project',
      name: 'DoneTask',
      component: DoneTask
    },
    {
      path: '/request-project',
      name: 'RequestProject',
      component: RequestProject
    }
  ]
})
