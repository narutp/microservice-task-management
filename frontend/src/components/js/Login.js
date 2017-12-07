import Axios from 'axios'
import Auth from '@/components/js/Auth'

export default {
  data () {
    return {
      username: '',
      password: '',
      checkLoginFailed: false,
      isLoading: false
    }
  },
  components: {
  },
  async mounted () {
    Auth.authenticate.logout()
  },
  methods: {
    registerUser () {
      this.$router.replace({ path: '/register' })
    },
    loginUser () {
      let self = this
      Axios.get(`//210.121.158.165:8090/login?username=${this.username}&password=${this.password}`).then(function (response) {
        if (response.data === true) {
          Auth.authenticate.login()
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
      let response = await Axios.get(`//210.121.158.165:8090/get/user/username?username=${username}`)
      // Save data to the current local store
      this.isLoading = true
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

      let departmentResponse = await Axios.get(`//210.121.158.165:8090/get/department/id?id=${idDepartment}`)
      let positionResponse = await Axios.get(`//210.121.158.165:8090/get/position/id?id=${idPosition}`)

      localStorage.setItem('user_department', departmentResponse.data.name)
      localStorage.setItem('user_position', positionResponse.data.name)

      setTimeout(() => {
        this.isLoading = false
        this.$router.go({ path: '/dashboard', force: true })
        this.$router.replace({ path: '/dashboard' })
      }, 2 * 1000)
    }
  }
}
