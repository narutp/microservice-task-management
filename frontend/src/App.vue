<template>
  <div id="app">
    <!--  Font awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <!--  Font for buefy -->
    <link rel="stylesheet" href="//fonts.googleapis.com/icon?family=Material+Icons">
    <div class="app--body">
      <el-row>
        <div class="app--title">
          <b>Task Management | </b>
          <span v-if="$route.path === '/'">Sign in</span>
          <span v-if="$route.path === '/register'">Sign up</span>
          <span v-if="index == 1">Dashboard</span>
          <span v-if="index == 2">My Task</span>
          <span v-if="index == 3">Department Task</span>
          <span v-if="index == 4">Termination Task</span>
          <span v-if="index == 5">Task Management</span>
          <span v-if="index == 6">Request Task</span>
        </div>
        <el-col class="left" :span="4" v-if="$route.path !== '/' && $route.path !== '/register'">
          <!--  Navbar when not login yet  -->
          <!-- <el-menu default-active="2" class="el-menu-vertical-demo" theme="dark" v-if="$route.path === '/' || $route.path === '/register'">
            <el-submenu index="1">
              <template slot="title">Navigator One</template>
              <el-menu-item-group title="Group One">
                <el-menu-item index="1-1">item one</el-menu-item>
                <el-menu-item index="1-2">item two</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </el-menu> -->
          <!--  Navbar when already login  -->
          <div class="app--user">
            <i class="fa fa-user-circle" style="width: 20px; height: 20px"></i>
          </div>
          <el-menu :default-active="index" class="el-menu-vertical-demo" theme="dark" v-if="$route.path !== '/' && $route.path !== '/register'">
            <el-menu-item index="1" @click="TaskStatementClicked()">
              <template slot="title">Task Statement</template>
            </el-menu-item>
            <el-menu-item index="2" @click="MyTaskClicked()">
              <template slot="title">My Task</template>
            </el-menu-item>
            <el-menu-item index="3" @click="DepartmentTaskClicked()">
              <template slot="title">Department Task</template>
            </el-menu-item>
            <el-menu-item index="4" @click="TerminationTaskClicked()">
              <template slot="title">Termination Task</template>
            </el-menu-item>
            <el-menu-item index="5" @click="TaskManagementClicked()">
              <template slot="title">Task Management</template>
            </el-menu-item>
            <el-menu-item index="6" @click="RequestTaskClicked()">
              <template slot="title">Request Task</template>
            </el-menu-item>
          </el-menu>
        </el-col>
        <el-col class="right-app" :span="20" v-if="$route.path !== '/' && $route.path !== '/register'">
          <router-view></router-view>
        </el-col>
        <el-col class="right-login" :span="24" v-else>
          <router-view></router-view>
        </el-col>
      </el-row>
    </div>
    <!-- <navbar></navbar> -->
  </div>
</template>

<script>
import Navbar from '@/components/Navbar.vue'
import Footerbar from '@/components/Footer.vue'
import { mapGetters, mapActions } from 'vuex'
export default {
  name: 'app',
  components: {
    Navbar,
    Footerbar
  },
  async mounted () {
    await this.setUser()
  },
  computed: {
    ...mapGetters({
      getUser: 'GET_USER'
    }),
    index () {
      if (this.$route.path === '/home') { return '1' }
      if (this.$route.path === '/my-task') { return '2' }
      if (this.$route.path === '/department-task') { return '3' }
      if (this.$route.path === '/termination-task') { return '4' }
      if (this.$route.path === '/task-management') { return '5' }
      if (this.$route.path === '/request-task') { return '6' }
    }
  },
  methods: {
    ...mapActions({
      setUser: 'SET_USER'
    }),
    TaskStatementClicked () {
      this.$router.replace({ path: '/home' })
    },
    MyTaskClicked () {
      this.$router.replace({ path: '/my-task' })
    },
    TaskManagementClicked () {
      this.$router.replace({ path: '/task-management' })
    },
    TerminationTaskClicked () {
      this.$router.replace({ path: '/termination-task' })
    },
    RequestTaskClicked () {
      this.$router.replace({ path: '/request-task' })
    },
    DepartmentTaskClicked () {
      this.$router.replace({ path: '/department-task' })
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  /*background-color: #f6f7f7;*/
}
.app--title {
  height: 50px;
  /* Same height to div for vertically center */
  line-height: 50px;
  background-color: black;
  color: white;
  text-align: left;
  padding-left: 20px;
}
.left {
  background-color: #2A323B;
  height: 1000px;
  padding-top: 10px;
}

.right-app {
  padding: 50px;
}
</style>
