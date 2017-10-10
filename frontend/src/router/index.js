import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Register from '@/components/Register'
import Home from '@/components/Home'
import AddParticipants from '@/components/AddParticipants'
import CreateTask from '@/components/CreateTask'
import UpdateTask from '@/components/UpdateTask'
import MyTask from '@/components/MyTask'
import DepartmentTask from '@/components/DepartmentTask'
import TaskManagement from '@/components/TaskManagement'

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
      path: '/my-task',
      name: 'MyTask',
      component: MyTask
    },
    {
      path: '/create-task',
      name: 'CreateTask',
      component: CreateTask
    },
    {
      path: '/update-task',
      name: 'UpdateTask',
      component: UpdateTask
    },
    {
      path: '/add-participants',
      name: 'AddParticipants',
      component: AddParticipants
    },
    {
      path: '/task-management',
      name: 'TaskManagement',
      component: TaskManagement
    },
    {
      path: '/department-task',
      name: 'DepartmentTask',
      component: DepartmentTask
    }
  ]
})
