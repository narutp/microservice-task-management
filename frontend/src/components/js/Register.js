import Axios from 'axios'
import moment from 'moment'
// Axios.defaults.baseURL = 'http://192.168.1.131:8080'
export default {
  data () {
    let checkName = (rule, value, callback) => {
      // alphabetic regular expression (Both Uppercase and Lowercase)
      let regex = /^[A-Za-z ]+$/
      if (!value) {
        callback(new Error('Please input your name'))
      } else if (value.length > 20) {
        callback(new Error('Your name must be at most 20 characters'))
      } else if (!value.match(regex)) {
        callback(new Error('Name must only be in alphabetic'))
      } else {
        callback()
      }
    }
    let checkUsername = (rule, value, callback) => {
      let regex = /^[A-Za-z0-9]+$/
      let numericRegex = /^(0|[1-9][0-9]*)$/
      let alphabeticRegex = /^[A-Za-z]+$/
      if (!value) {
        callback(new Error('Please input your username'))
      } else if (value.length < 6 || value.length > 16) {
        callback(new Error('Your username length must be 6-16 characters'))
      } else if (value.match(numericRegex) || value.match(alphabeticRegex)) {
        callback(new Error('Username must contain with both letters and numbers'))
      } else if (!value.match(regex)) {
        callback(new Error('Username must contain with only letters and numbers'))
      } else {
        callback()
      }
    }
    let checkPass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please input the password'))
      } else if (value.length < 8 || value.length > 20) {
        callback(new Error('Your password length must be 8-20 characters'))
      } else {
        if (this.form.rePassword !== '') {
          this.$refs.form.validateField('rePassword')
        }
        callback()
      }
    }
    let checkRePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please input the re-password'))
      } else if (value !== this.form.password) {
        callback(new Error('Your both password is not match'))
      } else {
        callback()
      }
    }
    let checkPhone = (rule, value, callback) => {
      let numericRegex = /^(0|[0-9][0-9]*)$/
      if (value === '') {
        callback(new Error('Please input your phone number'))
      } else if (value.length > 11) {
        callback(new Error('Your phone must be at most 11 numbers'))
      } else if (!value.match(numericRegex)) {
        callback(new Error('Your phone must be in numeric'))
      } else {
        callback()
      }
    }
    return {
      form: {
        name: '',
        birthdate: '',
        phone: '',
        department: '',
        position: '',
        email: '',
        username: '',
        password: '',
        rePassword: ''
      },
      dateOption: {
        disabledDate (time) {
          return time.getTime() > Date.now() - 8.64e7
        }
      },
      rules: {
        name: [
          { validator: checkName }
        ],
        phone: [
          { validator: checkPhone }
        ],
        email: [
          { required: true, message: 'Please input your email address', trigger: 'blur' },
          { type: 'email', message: 'Please input correct email address format', trigger: 'blur,change' }
        ],
        username: [
          { validator: checkUsername }
        ],
        password: [
          { validator: checkPass }
        ],
        rePassword: [
          { validator: checkRePass }
        ],
        department: [
          { required: true, message: 'Please pick your department' }
        ],
        position: [
          { required: true, message: 'Please pick your position' }
        ]
      }
    }
  },
  methods: {
    loginUser () {
      this.$router.replace({ path: '/' })
    },
    submitForm (form) {
      var self = this
      this.$refs[form].validate((valid) => {
        if (valid) {
          self.registerUser()
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    registerUser () {
      let self = this
      // Axios.get(`http://localhost:8090/create/department/${this.form.department}`).then(function (response) {
      // }).catch(function (error) {
      //   console.log(error)
      // })
      // Format date YYYY-MM-DD
      let date = moment(this.form.birthdate).format('YYYY-MM-DD')
      Axios.post(`//210.121.158.165:8090/register?name=${this.form.name}&birth=${date}&phone=${this.form.phone}&department=${this.form.department}&position=${this.form.position}&email=${this.form.email}&username=${this.form.username}&password=${this.form.password}`).then(function (response) {
        self.$router.replace({ path: '/' })
      }).catch(function (error) {
        console.log(error)
      })
    }
  }
}
