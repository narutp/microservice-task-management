import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Register from '@/components/Register'
import Home from '@/components/Home'
import GenerateTask from '@/components/GenerateTask'
import MyTask from '@/components/MyTask'
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
      path: '/generate-task',
      name: 'GenerateTask',
      component: GenerateTask
    },
    {
      path: '/task-management',
      name: 'TaskManagement',
      component: TaskManagement
    }
  ]
})
