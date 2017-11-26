<template>
  <div id="app">
    <!--  Font awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <!--  Font for buefy -->
    <link rel="stylesheet" href="//fonts.googleapis.com/icon?family=Material+Icons">
    <div class="app--body">
      <el-row>
        <div class="app--title">
          <b>Microservice Task Management | </b>
          <span v-if="$route.path === '/'">Sign in</span>
          <span v-if="$route.path === '/register'">Sign up</span>
          <span v-if="$route.path === '/dashboard'">Dashboard</span>
          <span v-if="index == 2">My Project</span>
          <span v-if="index == 3">Department Project</span>
          <span v-if="index == 4">Done Project</span>
          <span v-if="index == 5">Project Management</span>
          <span v-if="index == 6">Request Project</span>
          <span v-if="index == 7">Create Project</span>
          <span v-if="index == 8">Update Project</span>
          <button v-if="$route.path !== '/' && $route.path !== '/register'" class="app--logout-button button is-small is-danger pull-right" @click="logoutUser()">
            <i class="fa fa-sign-out" aria-hidden="true"></i>Logout
          </button>
        </div>
        <el-col class="left" :span="4" v-if="$route.path !== '/' && $route.path !== '/register'">
          <div class="app--user">
            <i class="fa fa-user-circle fa-lg"></i>
            <div class="">
              <u><span @click="updateUser()" class="app--user-detail"> {{ userName }} <br> {{ userEmail }}</span></u>
            </div>
          </div><hr>
          <el-menu align="left" mode="vertical" :default-active="index" class="el-menu-vertical-demo app--menubar" v-if="$route.path !== '/' && $route.path !== '/register'">
            <el-menu-item index="1" class="app--menu-item" @click="DashboardClicked()">
              <i class="app--icon fa fa-pie-chart" aria-hidden="true" style="display: inline"></i>Dashboard
            </el-menu-item>
            <b-collapse>
              <div slot="trigger" class="full-width">
                <hr><el-menu-item-group title="Project" class="inline">
                  <template slot="title">Project</template>
                </el-menu-item-group>
                <a class="card-header-icon inline">
                  <b-icon :icon="'arrow_drop_down'">
                  </b-icon>
                </a>
              </div>
              <div class="card-content">
                <el-menu-item index="2" class="app--menu-item" @click="MyProjectClicked()">
                  <i class="app--icon fa fa-address-book-o" aria-hidden="true" style="display: inline"></i>My Project
                </el-menu-item>
                <el-menu-item index="3" class="app--menu-item" @click="DepartmentProjectClicked()">
                  <i class="app--icon fa fa-building" aria-hidden="true" style="display: inline"></i>Department Project
                </el-menu-item>
                <el-menu-item index="4" class="app--menu-item" @click="DoneProjectClicked()">
                  <i class="app--icon fa fa-check-circle" aria-hidden="true" style="display: inline"></i>Done Card
                </el-menu-item>
              </div>
            </b-collapse>
            <b-collapse>
              <div slot="trigger" class="full-width">
                <hr><el-menu-item-group title="Management" class="inline">
                </el-menu-item-group>
                <a class="card-header-icon inline">
                  <b-icon :icon="'arrow_drop_down'">
                  </b-icon>
                </a>
              </div>
              <div class="card-content">
                <el-menu-item index="5" class="app--menu-item" @click="ProjectManagementClicked()">
                  <i class="app--icon fa fa-get-pocket" aria-hidden="true" style="display: inline"></i>Project Management
                </el-menu-item>
                <el-menu-item index="6" class="app--menu-item" @click="RequestProjectClicked()">
                  <i class="app--icon fa fa-reply-all" aria-hidden="true" style="display: inline"></i>Request Project
                </el-menu-item>
              </div>
            </b-collapse>
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
import Navbar from '@/components/Navbar'
import Footerbar from '@/components/Footer'
import Auth from '@/components/Auth'
// import { mapGetters } from 'vuex'
export default {
  name: 'app',
  data () {
    return {
      userName: 'user',
      userEmail: 'email'
      // userTaskAuthority: false
    }
  },
  components: {
    Navbar,
    Footerbar
  },
  mounted () {
    this.userName = localStorage.getItem('user_name')
    this.userEmail = localStorage.getItem('user_email')
  },
  computed: {
    index () {
      // TODO: add dialog when click logout
      // TODO: manage vuex
      // TODO: check all logic
      // TODO: add footer?
      // TODO: authenticate?
      if (this.$route.path === '/dashboard') { return '1' }
      if (this.$route.path === '/my-project') { return '2' }
      if (this.$route.path === '/department-project') { return '3' }
      if (this.$route.path === '/done-project') { return '4' }
      if (this.$route.path === '/project-management') { return '5' }
      if (this.$route.path === '/request-project') { return '6' }
      if (this.$route.path === '/create-project') { return '7' }
      if (this.$route.path === '/update-project') { return '8' }
    }
  },
  methods: {
    updateUser () {
      this.$router.replace({ path: '/update-user' })
    },
    logoutUser () {
      localStorage.clear()
      Auth.authenticate.logout()
      this.$router.replace({ path: '/' })
    },
    DashboardClicked () {
      this.$router.replace({ path: '/dashboard' })
    },
    MyProjectClicked () {
      this.$router.replace({ path: '/my-project' })
    },
    ProjectManagementClicked () {
      this.$router.replace({ path: '/project-management' })
    },
    DoneProjectClicked () {
      this.$router.replace({ path: '/done-project' })
    },
    RequestProjectClicked () {
      this.$router.replace({ path: '/request-project' })
    },
    DepartmentProjectClicked () {
      this.$router.replace({ path: '/department-project' })
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
  background-color: #EEF1F6;
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
  position: relative;
}
.app--menu-item {
  padding-left: 40px !important;
}
.app--menu-item.is-active {
  background-color: #2A323B;
  color: white;
}
.app--user-detail {
  cursor:pointer;
  color: #DC4C46;
  text-decoration:underline;
}
.app--icon {
  margin-right: 8px;
}
.left {
  /*background-color: #2A323B;*/
  background-color: #EEF1F6;
  height: 1000px;
  padding-top: 20px;
}

.right-app {
  padding: 50px;
}

.app--logout-button {
  border-radius: 50%;
  top: 20%;
  right: 1%;
}

.notification {
  background: 0;
  padding: 0;
  margin: 0;
}
.inline {
  display: inline-block;
}
.card-content {
  padding: 0px;
  padding-top: 5px;
}
.full-width {
  width: 100%;
}
.hr {
  margin-top: 0.25rem;
}
</style>
