<template lang="html">
  <div class="update-user--container">
    <div class="content" align="left">
      <el-row>
        <div class="update-user--header">
          <el-button size="small" @click="index=true">User information</el-button>
          <span>|</span>
          <el-button size="small" @click="index=false">User history</el-button>
        </div>
        <!--  index is attribute that show user information or user history  -->
        <!--  User Information part  -->
        <el-form label-position="top" label-width="150px" :model="form" ref="form" :rules="rules" v-if="index === true">
          <div class=""><b>Personal Information</b></div>
          <hr>
          <el-row>
            <el-col :span="12">
              <el-form-item>
                <b>Name</b>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item prop="name">
                <el-input v-model="form.name"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item>
                <b>Birth date</b>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item>
                <el-date-picker
                  class="full--width"
                  v-model="form.birthdate"
                  type="date"
                  :picker-options="dateOption">
                </el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item>
                <b>Phone number</b>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item prop="phone">
                <el-input v-model="form.phone"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <br>
          <div class="margin-bottom"><b>Account</b></div>
          <hr>
          <el-row>
            <el-col :span="12">
              <el-form-item>
                <b>Email</b>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item  prop="email">
                <el-input v-model="form.email"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item>
                <b>Old password</b>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item prop="password">
                <el-input type="password" v-model="form.password"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item>
                <b>New password</b>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item prop="password">
                <el-input type="password" v-model="form.newPassword"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <br>
          <div class=""><b>Other Information</b></div>
          <hr>
          <el-row>
            <el-col :span="12">
              <el-form-item>
                <b>Department</b>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item prop="department">
                  <el-select class="full--width" v-model="form.department" placeholder="Department">
                  <el-option label="A" value="A"></el-option>
                  <el-option label="B" value="B"></el-option>
                  <el-option label="C" value="C"></el-option>
                  <el-option label="D" value="D"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item>
                <b>Position</b>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item prop="position">
                <el-select class="full--width" v-model="form.position" placeholder="Position">
                  <el-option label="Intern" value="Intern"></el-option>
                  <el-option label="Professor" value="Professor"></el-option>
                  <el-option label="Student" value="Student"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <br>
          <el-row>
            <el-col :span="12">
                <p class="update-user--update-information"><i>Update your account with these data</i></p>
            </el-col>
            <el-col :span="12">
              <el-button round type="info" class="full--width" @click="updateUser()">
                Update User
              </el-button>
            </el-col>
          </el-row>
        </el-form>
      </el-row>
      <!--  User history part  -->
      <el-row v-if="index === false">
        <div class=""><b>User history</b></div>
      </el-row>
    </div>
  </div>
</template>

<script>
import Axios from 'axios'
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
      index: true,
      form: {
        name: '',
        birthdate: '',
        phone: '',
        department: '',
        position: '',
        email: '',
        username: '',
        password: '',
        newPassword: ''
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
        department: [
          { required: true, message: 'Please pick your department' }
        ],
        position: [
          { required: true, message: 'Please pick your position' }
        ]
      }
    }
  },
  mounted () {
    this.form.name = localStorage.getItem('user_name')
    this.form.birthdate = localStorage.getItem('user_birthdate')
    this.form.phone = localStorage.getItem('user_phone')
    this.form.department = localStorage.getItem('user_department')
    this.form.position = localStorage.getItem('user_position')
    this.form.email = localStorage.getItem('user_email')
    this.form.username = localStorage.getItem('user_username')
    this.form.password = localStorage.getItem('user_password')
    this.form.newPassword = this.form.password
  },
  methods: {
    async updateUser () {
      let response = await Axios.post(`http://localhost:8090/edit/user/${this.form.username}/
        ${this.form.name}/${this.form.birthdate}/${this.form.phone}/${this.form.department}/
        ${this.form.position}/${this.form.email}/${this.form.newPassword}`)

      console.log(response.data)
      if (response.data === true) {
        this.setUser()
      } else {
        alert('Update failed')
      }
    },
    async setUser () {
      // localStorage.clear()
      // let response = await Axios.get(`http://localhost:8090/get/user/username/${username}`)
      // Save data to the current local store
      localStorage.setItem('user_name', this.form.name)
      // localStorage.setItem('user_task_authority', response.data.taskAuthority)
      localStorage.setItem('user_email', this.form.email)
      localStorage.setItem('user_birthdate', this.form.birthdate)
      localStorage.setItem('user_phone', this.form.phone)
      localStorage.setItem('user_password', this.form.newPassword)
      localStorage.setItem('user_username', this.form.username)
      localStorage.setItem('user_department', this.form.department)
      localStorage.setItem('user_position', this.form.position)

      this.$router.go({ path: '/home', force: true })
      this.$router.replace({ path: '/home' })
    }
  }
}
</script>

<style scoped>
.update-user--container {
  background-color: #fff;
  padding: 30px;
  padding-left: 100px;
  padding-right: 100px;
}
.update-user--header {
  margin-bottom: 30px;
}
.content {
  width: 75%;
}
.full--width {
  width: 100%;
}
.update-user--update-information {
  font-size: 14px;
}
p {
  padding-top: 10px;
}
</style>
