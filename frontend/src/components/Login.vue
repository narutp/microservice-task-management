<template>
  <div class="login--container">
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
          <div>
            <button class="button is-danger is-outlined login--button" @click="login()">
              Login
            </button>
            <span class="login--signup" @click="register()">Forget your password? <b>Sign up</b></span>
            <!-- <button class="button is-info" @click="register()">
              Register
            </button> -->
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
// import { mapActions } from 'vuex'
import Axios from 'axios'
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
          // self.setUser(self.username)
          self.setUser(self.username)
          self.checkLoginFailed = false
        } else {
          self.checkLoginFailed = true
        }
      }).catch(function (error) {
        console.log(error)
      })
    },
    async setUser (username) {
      let response = await Axios.get(`http://localhost:8090/get/user/username/${username}`)
      // Save data to the current local store
      localStorage.setItem('user_name', response.data.name)
      localStorage.setItem('user_task_authority', response.data.taskAuthority)
      localStorage.setItem('user_email', response.data.email)
      localStorage.setItem('user_birthdate', response.data.birthdate)
      localStorage.setItem('user_phone', response.data.mobilePhone)
      localStorage.setItem('user_password', response.data.password)
      let idDepartment = response.data.idDepartment
      let idPosition = response.data.idPosition

      let departmentResponse = await Axios.get('')

      // Access some stored data
      this.$router.go({ path: '/home', force: true })
      this.$router.replace({ path: '/home' })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.login--container {
  background-color: #2A323B;
  height: 700px;
}
.login--login-box {
  padding: 30px;
  margin-bottom: 75px;
}
.login--user-icon {
  margin-top: 40px;
  margin-bottom: 40px;
  background-color: black;
  color: white;
  border-radius: 50%;
}
.login--button {
  width: 100%;
  margin-top: 15px;
  margin-bottom: 15px;
}
.login--signup {
  color: #8E9296;
  font-size: 14px;
  cursor: pointer;
}
.notification.is-danger {
  margin-top: 15px;
}
</style>
