<template lang="html">
  <div class="register--container">
    <div class="hero is-info">
      <div class="hero-body">
        <p class="title">
          Task Management System
        </p>
      </div>
    </div>
    <div class="register--body">
      <el-row>
        <el-col :span="10" :offset="7">
          <div class="register--register-box">
            <el-form label-position="left" label-width="150px" :model="form">
              <el-form-item class="register--form-item" label="Name">
                <el-input v-model="form.name"></el-input>
              </el-form-item>
              <el-form-item class="register--form-item" label="Birthdate">
                <el-date-picker
                  class="register--form-item"
                  v-model="form.birthdate"
                  type="date"
                  placeholder="Pick a day">
                </el-date-picker>
              </el-form-item>
              <el-form-item class="register--form-item" label="Phone">
                <el-input v-model="form.phone"></el-input>
              </el-form-item>
              <el-form-item class="register--form-item" label="Department">
                <el-select class="register--form-item" v-model="form.department" placeholder="Department">
                  <el-option label="Engineer" value="Engineer"></el-option>
                  <el-option label="Business" value="Business"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item class="register--form-item" label="Position">
                <el-select class="register--form-item" v-model="form.position" placeholder="Position">
                  <el-option label="1" value="1"></el-option>
                  <el-option label="2" value="2"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item class="register--form-item" label="Email">
                <el-input v-model="form.email"></el-input>
              </el-form-item>
              <el-form-item class="register--form-item" label="ID">
                <el-input v-model="form.userId"></el-input>
              </el-form-item>
              <el-form-item class="register--form-item" label="Password">
                <el-input v-model="form.userPass"></el-input>
              </el-form-item>
              <el-form-item class="register--form-item" label="Re-password">
                <el-input></el-input>
              </el-form-item>
            </el-form>
            <div align="right">
              <button class="button is-success" @click="register()">
                Register
              </button>
              <button class="button is-danger" @click="backLogin()">
                Cancle
              </button>
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
      Axios.get(`http://localhost:8090/register/${this.form.userId}/${this.form.userPass}`).then(function (response) {
        self.$router.replace({ path: '/' })
      }).catch(function (error) {
        console.log(error)
      })
    }
  }
}
</script>

<style scoped>
.register--register-box {
  border: 1px solid #c2cbd1;
  padding: 15px 15px 15px 15px;
}

.register--body {
  margin: 30px;
}

.register--register-field {
  margin-bottom: 15px;
}

.register--register-field span {
  float: left;
}

.register--form-item {
  width: 100%;
}
</style>
