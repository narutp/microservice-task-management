<template>
  <div class="login--container inline-template">
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
        <button class="button is-success is-outlined button margin-right" @click="registerUser()">
          Register
        </button>
        <button class="button is-success button" @click="loginUser()">
          Login
        </button>
        <span class="login--signup">Forget your password?</span>
        </div>
        <div class="notification is-danger" v-if="checkLoginFailed">
          Wrong username or password
        </div>
      </div>
    </div>
    <b-loading :active.sync="isLoading"></b-loading>
  </div>

</template>

<script src="@/components/js/Login">
// import { mapActions } from 'vuex'
// import Axios from 'axios'
// import Auth from '@/components/js/Auth'
//
// export default {
//   data () {
//     return {
//       username: '',
//       password: '',
//       checkLoginFailed: false,
//       isLoading: false
//     }
//   },
//   components: {
//   },
//   async mounted () {
//     Auth.authenticate.logout()
//   },
//   methods: {
//     registerUser () {
//       this.$router.replace({ path: '/register' })
//     },
//     loginUser () {
//       let self = this
//       Axios.get(`//210.121.158.165:8090/login?username=${this.username}&password=${this.password}`).then(function (response) {
//         if (response.data === true) {
//           Auth.authenticate.login()
//           self.setUser(self.username)
//           self.checkLoginFailed = false
//         } else {
//           self.checkLoginFailed = true
//         }
//       }).catch(function (error) {
//         console.log(error)
//       })
//     },
//     async setUser (username) {
//       let response = await Axios.get(`//210.121.158.165:8090/get/user/username?username=${username}`)
//       // Save data to the current local store
//       this.isLoading = true
//       localStorage.setItem('user_name', response.data.name)
//       localStorage.setItem('user_task_authority', response.data.taskAuthority)
//       localStorage.setItem('user_email', response.data.email)
//       localStorage.setItem('user_birthdate', response.data.birthdate)
//       localStorage.setItem('user_phone', response.data.mobilePhone)
//       localStorage.setItem('user_password', response.data.password)
//       localStorage.setItem('user_username', response.data.username)
//       localStorage.setItem('user_userId', response.data.idUser)
//       localStorage.setItem('user_departmentId', response.data.idDepartment)
//       let idDepartment = response.data.idDepartment
//       let idPosition = response.data.idPosition
//       // console.log(response.data)
//
//       let departmentResponse = await Axios.get(`//210.121.158.165:8090/get/department/id?id=${idDepartment}`)
//       let positionResponse = await Axios.get(`//210.121.158.165:8090/get/position/id?id=${idPosition}`)
//
//       localStorage.setItem('user_department', departmentResponse.data.name)
//       localStorage.setItem('user_position', positionResponse.data.name)
//
//       setTimeout(() => {
//         this.isLoading = false
//         this.$router.go({ path: '/dashboard', force: true })
//         this.$router.replace({ path: '/dashboard' })
//       }, 2 * 1000)
//     }
//   }
// }
</script>

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
