<template lang="html">
  <div class="register--container">
    <div class="register--body">
      <div class="register--register-box">
        <i class="fa fa-user-circle fa-5x register--user-icon" aria-hidden="true"></i>
        <el-form label-position="top" label-width="150px" :model="form" ref="form" :rules="rules" align="left">
          <div class="register--form-title">
            <b>Account</b>
          </div>
          <el-form-item prop="name">
            <el-input v-model="form.name" placeholder="Name"></el-input>
          </el-form-item>
          <el-form-item>
            <el-date-picker
              class="register--form-item"
              v-model="form.birthdate"
              type="date"
              placeholder="Birthdate"
              :picker-options="dateOption">
            </el-date-picker>
          </el-form-item>
          <el-form-item prop="phone">
            <el-input v-model="form.phone" placeholder="Phone"></el-input>
          </el-form-item>
          <hr>
          <div class="register--form-title" align="left">
            <b>Personal Information</b>
          </div>
          <el-row>
            <el-form-item class="register--form-item" prop="email">
              <el-input v-model="form.email" placeholder="Email"></el-input>
            </el-form-item>
            <el-form-item class="register--form-item" prop="username">
              <el-input v-model="form.username" placeholder="Username"></el-input>
            </el-form-item>
          </el-row>
          <el-form-item class="register--form-item" prop="password">
            <el-input type="password" v-model="form.password" placeholder="Password"></el-input>
          </el-form-item>
          <el-form-item class="register--form-item" prop="rePassword">
            <el-input type="password" v-model="form.rePassword" placeholder="Re-enter Password"></el-input>
          </el-form-item>
          <hr>
          <div class="register--form-title" align="left">
            <b>Information</b>
          </div>
          <el-form-item class="register--form-item" prop="department">
              <el-select class="register--form-item" v-model="form.department" placeholder="Department">
              <el-option label="A" value="A"></el-option>
              <el-option label="B" value="B"></el-option>
              <el-option label="C" value="C"></el-option>
              <el-option label="D" value="D"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item class="register--form-item" prop="position">
            <el-select class="register--form-item" v-model="form.position" placeholder="Position">
              <el-option label="Intern" value="Intern"></el-option>
              <el-option label="Professor" value="Professor"></el-option>
              <el-option label="Student" value="Student"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <hr>
        <div>
          <button class="button is-success is-outlined register--button" @click="submitForm('form')">
            Register
          </button>
          <span class="register--backlogin" @click="backLogin()">Already have account? <b>Sign in</b></span>
          <!-- <button class="button is-danger" @click="backLogin()">
            Cancle
          </button> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
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
    submitForm (form) {
      var self = this
      this.$refs[form].validate((valid) => {
        if (valid) {
          self.register()
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    backLogin () {
      this.$router.replace({ path: '/' })
    },
    register () {
      let self = this
      // Axios.get(`http://localhost:8090/create/department/${this.form.department}`).then(function (response) {
      // }).catch(function (error) {
      //   console.log(error)
      // })
      // Format date YYYY-MM-DD
      let date = moment(this.form.birthdate).format('YYYY-MM-DD')
      Axios.post(`http://localhost:8090/register/${this.form.name}/${date}/${this.form.phone}/${this.form.department}/${this.form.position}/${this.form.email}/${this.form.username}/${this.form.password}`).then(function (response) {
        self.$router.replace({ path: '/' })
      }).catch(function (error) {
        console.log(error)
      })
    }
  }
}
</script>

<style scoped>
.register--container {
  background-color: #2A323B;
  color: white;
  padding-left: 300px;
  padding-right: 300px;
}
.register--user-icon {
  margin-top: 40px;
  margin-bottom: 40px;
  background-color: black;
  color: white;
  border-radius: 50%;
}
.register--register-box {
  padding: 50px;
}
.register--form-title {
  margin-bottom: 20px;
}
.register--register-field {
  margin-bottom: 15px;
}
.register--button {
  width: 100%;
  margin-bottom: 15px;
}
.register--register-field span {
  float: left;
}
.register--backlogin {
  color: #8E9296;
  font-size: 14px;
  cursor: pointer;
}
.register--form-item {
  width: 100%;
}
.register--body {
  width: 448px;
  margin: auto;
}
.register--section{
  border-bottom: 1px;
  margin-bottom: 30px;
}
hr {
  background-color: #848484;
}
</style>
