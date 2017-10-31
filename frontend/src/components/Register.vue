<template lang="html">
  <div class="register--container">
    <div class="register--body">
      <el-row>
        <el-col>
          <div class="register--register-box">
            <i class="fa fa-user-circle fa-5x register--user-icon" aria-hidden="true"></i>
            <el-form label-position="top" label-width="150px" :model="form" :rules="rules">
              <div class="register--form-title" align="left">
                <b>Account</b>
              </div>
              <el-form-item class="register--form-item">
                <el-row>
                  <el-col :span="12">
                    <el-input v-model="form.name" placeholder="Name"></el-input>
                  </el-col>
                  <el-col :span="12">
                    <el-date-picker
                      class="register--form-item"
                      v-model="form.birthdate"
                      type="date"
                      placeholder="Birthdate">
                    </el-date-picker>
                  </el-col>
                </el-row>
              </el-form-item>
              <el-form-item class="register--form-item" prop="phone">
                <el-input v-model="form.phone" placeholder="Phone"></el-input>
              </el-form-item>
              <div class="register--form-title" align="left">
                <b>Personal Information</b>
              </div>
              <el-row>
                <el-col :span="12">
                  <el-form-item class="register--form-item" prop="email">
                    <el-input v-model="form.email" placeholder="Email"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item class="register--form-item" prop="username">
                    <el-input v-model="form.userId" placeholder="Username"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-form-item class="register--form-item">
                <el-row>
                  <el-col :span="12">
                    <el-input type="password" v-model="form.userPass" placeholder="Password"></el-input>
                  </el-col>
                  <el-col :span="12">
                    <el-input type="password" placeholder="Re-enter Password"></el-input>
                  </el-col>
                </el-row>
              </el-form-item>
              <div class="register--form-title" align="left">
                <b>Information</b>
              </div>
              <el-row>
                <el-col :span="12">
                  <el-form-item class="register--form-item" prop="department">
                      <el-select class="register--form-item" v-model="form.department" placeholder="Department">
                      <el-option label="A" value="A"></el-option>
                      <el-option label="B" value="B"></el-option>
                      <el-option label="C" value="C"></el-option>
                      <el-option label="D" value="D"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item class="register--form-item" prop="position">
                    <el-select class="register--form-item" v-model="form.position" placeholder="Position">
                      <el-option label="Intern" value="Intern"></el-option>
                      <el-option label="Professor" value="Professor"></el-option>
                      <el-option label="Student" value="Student"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form>
            <div>
              <button class="button is-success is-outlined register--button" @click="register()">
                Register
              </button>
              <span class="register--backlogin" @click="backLogin()">Already have account? <b>Sign in</b></span>
              <!-- <button class="button is-danger" @click="backLogin()">
                Cancle
              </button> -->
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import Axios from 'axios'
// Axios.defaults.baseURL = 'http://192.168.1.131:8080'
export default {
  data () {
    return {
      form: {
        name: '',
        birthdate: '',
        phone: '',
        department: '',
        position: '',
        email: '',
        userId: '',
        userPass: ''
      }
    }
  },
  methods: {
    backLogin () {
      this.$router.replace({ path: '/' })
    },
    register () {
      let self = this
      Axios.get(`http://localhost:8090/create/department/${this.form.department}`).then(function (response) {
      }).catch(function (error) {
        console.log(error)
      })
      Axios.post(`http://localhost:8090/register/${this.form.name}/${this.form.birthdate}/${this.form.phone}/${this.form.department}/${this.form.position}/${this.form.email}/${this.form.userId}/${this.form.userPass}`).then(function (response) {
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
  margin-top: 15px;
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
</style>
