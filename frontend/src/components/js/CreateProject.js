import Axios from 'axios'
import { mapGetters } from 'vuex'
export default {
  data () {
    return {
      projectName: '',
      projectDescription: '',
      userId: '',
      alertInfo: true,
      alertError: false,
      alertErrorDuplicate: false
    }
  },
  mounted () {
    this.userId = localStorage.getItem('user_userId')
  },
  methods: {
    async createProject () {
      // check if the project name is same in database
      let checkDuplicateResponse = await Axios.get(`//210.121.158.162:8091/check/project-name?projectName=${this.projectName}`)
      // false is already have this project name
      if (checkDuplicateResponse.data === true) {
        let idDepartment = localStorage.getItem('user_departmentId')
        let response = await Axios.post(`//210.121.158.162:8091/create/project?name=${this.projectName}&description=${this.projectDescription}&idUser=${this.userId}&idDepartment=${idDepartment}`)
        if (response.data === true) {
          this.back()
        } else {
          this.alertInfo = false
          this.alertError = true
          this.alertErrorDuplicate = false
        }
      }
      this.alertInfo = false
      this.alertError = false
      this.alertErrorDuplicate = true
    },
    back () {
      this.$router.replace({ path: '/project-management' })
    }
  },
  computed: {
    ...mapGetters({
      getUser: 'GET_USER'
    })
  }
}
