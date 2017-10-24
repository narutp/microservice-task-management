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
          <span v-if="index == 1">Dashboard</span>
          <span v-if="index == 2">My Project</span>
          <span v-if="index == 3">Department Project</span>
          <span v-if="index == 4">Done Project</span>
          <span v-if="index == 5">Project Management</span>
          <span v-if="index == 6">Request Project</span>
          <span v-if="index == 7">Create Project</span>
          <span v-if="index == 8">Update Project</span>
          <button v-if="$route.path !== '/' && $route.path !== '/register'" class="button is-outlined app--logout-button pull-right" @click="logout()">
            Logout
          </button>
        </div>
        <el-col class="left" :span="4" v-if="$route.path !== '/' && $route.path !== '/register'">
          <div class="app--user">
            <i class="fa fa-user-circle fa-lg"></i>
            <div class="">
              {{ getUser[0].name }}
              {{ getUser[0] }}
            </div>
          </div>
          <el-menu align="left" mode="vertical" :default-active="index" class="el-menu-vertical-demo app--menubar" v-if="$route.path !== '/' && $route.path !== '/register'">
            <el-menu-item index="1" class="app--menu-item" @click="DashboardClicked()">
              <template slot="title">Dashboard</template>
            </el-menu-item>
            <hr><el-menu-item-group title="Project">
              <el-menu-item index="2" class="app--menu-item" @click="MyProjectClicked()">
                <template slot="title">My Project</template>
              </el-menu-item>
              <el-menu-item index="3" class="app--menu-item" @click="DepartmentProjectClicked()">
                <template slot="title">Department Project</template>
              </el-menu-item>
              <el-menu-item index="4" class="app--menu-item" @click="DoneProjectClicked()">
                <template slot="title">Done Project</template>
              </el-menu-item>
            </el-menu-item-group>
            <hr><el-menu-item-group title="Management">
              <el-menu-item index="5" class="app--menu-item" @click="ProjectManagementClicked()">
                <template slot="title">Project Management</template>
              </el-menu-item>
              <el-menu-item index="6" class="app--menu-item" @click="RequestProjectClicked()">
                <template slot="title">Request Project</template>
              </el-menu-item>
            </el-menu-item-group>
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
    ...mapActions({
      setUser: 'SET_USER'
    }),
    logout () {
      this.$router.replace({ path: '/' })
      localStorage.clear()
      console.log(localStorage)
    },
    DashboardClicked () {
      this.$router.replace({ path: '/home' })
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
.app--logout-button {
  margin: 5px;
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
</style>
