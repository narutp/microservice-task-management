<template lang="html">
  <div class="register--container">
    <div class="hero is-info">
      <div class="hero-body">
        <p class="title">
          Task Management System
        </p>
      </div>
    </div>
    <div class="register--body">
      <div class="columns">
        <div class="column">

        </div>
        <div class="column register--register-box">
          <el-form :label-position="left" label-width="100px" :model="form">
            <el-form-item label="Name">
              <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="Activity zone">
              <el-input v-model="form.region"></el-input>
            </el-form-item>
            <el-form-item label="Activity form">
              <el-input v-model="form.type"></el-input>
            </el-form-item>
          </el-form>
          <div class="register--register-field">
            <span>Name</span>
            <input v-model="username" class="input" placeholder="Username">
          </div>
          <div class="register--register-field">
            <span>Birthdate</span>
            <input v-model="password" class="input" type="password" placeholder="Password">
          </div>
          <div align="right">
            <button class="button is-success" @click="register()">
              Submit
            </button>
            <button class="button is-danger" @click="backLogin()">
              Back
            </button>
          </div>
        </div>
        <div class="column">

        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Axios from 'axios'
// Axios.defaults.baseURL = 'http://192.168.1.131:8080'
export default {
  data () {
    return {
      form: {
        name: '',
        region: '',
        type: ''
      },
      username: '',
      password: ''
    }
  },
  methods: {
    backLogin () {
      this.$router.replace({ path: '/' })
    },
    register () {
      let self = this
      Axios.get(`http://localhost:8090/register/${this.username}/${this.password}`).then(function (response) {
        self.$router.replace({ path: '/' })
      }).catch(function (error) {
        console.log(error)
      })
    }
  }
}
</script>

<style scoped>
.register--register-box {
  border: 1px solid #c2cbd1;
  padding: 15px 15px 15px 15px;
}

.register--body {
  margin: 30px;
}

.register--register-field {
  margin-bottom: 15px;
}
.register--register-field span {
  float: left;
}
</style>
