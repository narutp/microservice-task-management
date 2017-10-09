<template>
  <div class="login--container">
    <div class="login--title">
      Task Management System | Sign in
    </div>
    <div class="login--body">
      <div class="columns">
        <div class="column">
          <!-- Left column -->
        </div>
        <div class="column login--login-box">
          <i class="fa fa-user-circle fa-5x login--user-icon" aria-hidden="true"></i>
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
          self.$router.replace({ path: '/home' })
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
.login--container {
  background-color: #2A323B;
}
.login--title {
  height: 50px;
  /* Same height to div for vertically center */
  line-height: 50px;
  background-color: black;
  color: white;
  text-align: left;
  padding-left: 20px;
}
.login--login-box {
  padding: 30px;
  margin-bottom: 75px;
}
.login--user-icon {
  margin-bottom: 15px;
  background-color: black;
  color: white;
  border-radius: 50%;
}
.notification.is-danger {
  margin-top: 15px;
}
</style>
