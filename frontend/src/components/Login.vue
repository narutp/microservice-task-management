<template>
  <div class="login--container inline-template">
    <div class="col">
      <div class="login--panel">
        <div class="column login--box">
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
          <hr>
          <button class="button is-danger is-outlined button margin-right" @click="register()">
            Register
          </button>
          <button class="button is-danger button" @click="login()">
            Login
          </button>
          <span class="login--signup">Forget your password?</span>
          </div>
          <div class="notification is-danger" v-if="checkLoginFailed">
            Wrong username or password
          </div>
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
      localStorage.setItem('user_username', response.data.username)
      localStorage.setItem('user_userId', response.data.idUser)
      localStorage.setItem('user_departmentId', response.data.idDepartment)
      let idDepartment = response.data.idDepartment
      let idPosition = response.data.idPosition
      // console.log(response.data)

      let departmentResponse = await Axios.get(`http://localhost:8090/get/department/id/${idDepartment}`)
      let positionResponse = await Axios.get(`http://localhost:8090/get/position/id/${idPosition}`)

      localStorage.setItem('user_department', departmentResponse.data.name)
      localStorage.setItem('user_position', positionResponse.data.name)

      console.log(localStorage.getItem('user_name'))
      console.log(localStorage.getItem('user_task_authority'))
      console.log(localStorage.getItem('user_email'))
      console.log(localStorage.getItem('user_birthdate'))
      console.log(localStorage.getItem('user_phone'))
      console.log(localStorage.getItem('user_password'))
      console.log(localStorage.getItem('user_department'))
      console.log(localStorage.getItem('user_position'))

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
  width: 100%;
  color: white;
  height: 900px;
  display: inline-block;
}
.login--box {
  padding: 0;
}
.login--user-icon {
  margin-top: 40px;
  margin-bottom: 40px;
  background-color: black;
  color: white;
  border-radius: 50%;
}
.button {
  width: 180px;
  height: 50px;
  border-radius: 30px;
  margin-bottom: 15px;
  display: inline-block;
}
.margin-right {
  margin-right: 20px;
}
.login--signup {
  color: #8E9296;
  font-size: 14px;
  cursor: pointer;
}
.notification.is-danger {
  margin-top: 15px;
}
.login--body {
  width: 50%;
  margin: 0;
  display: inline;
}
.login--panel {
  width: 448px;
  margin: auto;
  padding: 30px;
}
.field {
  margin-bottom: 30px;
}
.icon--field {
  vertical-align: middle;
  padding-left: 10px;
}
hr {
  background-color: #848484;
}
</style>
