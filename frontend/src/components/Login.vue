<template>
  <div class="login--container">
    <div class="hero is-primary">
      <div class="hero-body">
        <p class="title">
          Task Management System
        </p>
      </div>
    </div>
    <div class="login--body">
      <div class="columns">
        <div class="column">
          <!-- Left column -->
        </div>
        <div class="column login--login-box">
          <div class="field">
            <p class="control has-icons-left has-icons-right">
              <input v-model="username" class="input" type="" placeholder="Username">
              <span class="icon is-small is-left">
                <i class="fa fa-envelope"></i>
              </span>
            </p>
          </div>
          <div class="field">
            <p class="control has-icons-left">
              <input v-model="password" class="input" type="password" placeholder="Password">
              <span class="icon is-small is-left">
                <i class="fa fa-lock"></i>
              </span>
            </p>
          </div>
          <div align="center">
            <button class="button is-success" @click="login()">
              Login
            </button>
            <button class="button is-info" @click="register()">
              Register
            </button>
          </div>
          <div class="notification is-danger" v-if="checkLoginFailed">
            Wrong username or password
          </div>
        </div>
        <div class="column">
          <!-- Right column -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Axios from 'axios'
// Axios.defaults.baseURL = 'http://192.168.1.131:8080'
// Axios.defaults.headers.post['Accept'] = 'application/json'
// Axios.defaults.headers.post['Content-Type'] = 'application/json'
export default {
  data () {
    return {
      username: '',
      password: '',
      checkLoginFailed: false
    }
  },
  components: {
  },
  methods: {
    register () {
      this.$router.replace({ path: '/register' })
    },
    login () {
      let self = this
      Axios.get(`http://localhost:8090/login/${this.username}/${this.password}`).then(function (response) {
        if (response.data === true) {
          self.$router.replace({ path: '/document' })
          self.checkLoginFailed = false
        } else {
          self.checkLoginFailed = true
        }
      }).catch(function (error) {
        console.log(error)
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.login--login-box {
  border: 1px solid #c2cbd1;
  padding: 30px;
  margin-top: 50px;
  margin-bottom: 75px;
}

.login--body {
  margin: 30px;
}

.notification.is-danger {
  margin-top: 15px;
}

.login--container {
  background-color: white;
  border-radius: 6px;
  margin-top: 30px;
}
</style>
