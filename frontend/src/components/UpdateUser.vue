<template lang="html">
  <div class="content">
    <div class="body user--container" align="left">
      <el-row>
        <el-form label-position="top" label-width="150px" :model="form" ref="form" :rules="rules">
          <b>Personal Information</b>
          <el-row>
            <el-form-item prop="name">
              <el-input v-model="form.name" placeholder="Name"></el-input>
            </el-form-item>
          </el-row>
          <el-col :span="12">
            <el-form-item>
              <el-date-picker
                class="full--width"
                v-model="form.birthdate"
                type="date"
                placeholder=form.birthdate
                :picker-options="dateOption">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="phone">
              <el-input v-model="form.phone" placeholder="Phone"></el-input>
            </el-form-item>
          </el-col>

          <b>Account</b>
          <el-row>
            <el-col :span="12">
              <el-form-item  prop="email">
                <el-input v-model="form.email" placeholder="Email"></el-input>
              </el-form-item>
            </el-col>
            <!-- <el-col :span="12">
              <el-form-item class="register--form-item" prop="username">
                <el-input v-model="form.username" placeholder="Username"></el-input>
              </el-form-item>
            </el-col> -->
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item class="register--form-item" prop="password">
                <el-input type="password" v-model="form.password" placeholder="Old password"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item class="register--form-item" prop="rePassword">
                <el-input type="password" v-model="form.newPassword" placeholder="New password"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <b>Information</b>
          <el-row>
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
        </el-form>
        <button class="button is-success is-outlined full--width" @click="updateUser()">
          Update User
        </button>
      </el-row>
    </div>
  </div>
</template>

<script>
import Axios from 'axios'
export default {
  data () {
    return {
      form: {
        name: 'Makhamwan',
        birthdate: '2017-11-01',
        phone: '01073094860',
        department: 'A',
        position: 'Intern',
        email: 'mkhv@gmail.com',
        username: 'makhamwan',
        password: '',
        newPassword: ''
      },
      dateOption: {
        disabledDate (time) {
          return time.getTime() > Date.now() - 8.64e7
        }
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
.user--container {
  padding-left: 100px;
  padding-right: 100px;
}
.full--width {
  width: 100%;
}

</style>
