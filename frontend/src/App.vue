<template>
  <div id="app">
    <!--  Font awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <!--  Font for buefy -->
    <link rel="stylesheet" href="//fonts.googleapis.com/icon?family=Material+Icons">
    <div class="app--body">
      <el-row>
        <el-col class="left" :span="4">
          <!--  Navbar when not login yet  -->
          <el-menu default-active="2" class="el-menu-vertical-demo" theme="dark" v-if="$route.path === '/' || $route.path === '/register'">
            <el-submenu index="1">
              <template slot="title">Navigator One</template>
              <el-menu-item-group title="Group One">
                <el-menu-item index="1-1">item one</el-menu-item>
                <el-menu-item index="1-2">item two</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </el-menu>

          <!--  Navbar when already login  -->
          <el-menu default-active="2" class="el-menu-vertical-demo" theme="dark" v-else>
            <el-submenu index="1">
              <template slot="title">LOGIN</template>
              <el-menu-item-group title="Group One">
                <el-menu-item index="1-1">item one</el-menu-item>
                <el-menu-item index="1-2">item two</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </el-menu>
        </el-col>
        <el-col class="right" :span="20">
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
import { mapGetters } from 'vuex'
export default {
  name: 'app',
  components: {
    Navbar,
    Footerbar
  },
  computed: {
    ...mapGetters(['user'])
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

.left {
  background-color: #2A323B;
  height: 1000px;
  padding-top: 10px;
}

.right {
  padding: 30px;
}
</style>
