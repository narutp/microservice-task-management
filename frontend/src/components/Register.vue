<template lang="html">
  <div class="register--container">
    <div class="register--body">
      <el-row>
        <el-col>
          <div class="hero is-info">
            <div class="hero-body">
              <p class="title">
                Registration
              </p>
            </div>
          </div>
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
                  <el-option label="Architecture" value="Architecture"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item class="register--form-item" label="Position">
                <el-select class="register--form-item" v-model="form.position" placeholder="Position">
                  <el-option label="Intern" value="Intern"></el-option>
                  <el-option label="Professor" value="Professor"></el-option>
                  <el-option label="Student" value="Student"></el-option>
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
  background-color: white;
  /*padding: 30px;*/
  border-radius: 6px;
}

.register--register-box {
  border: 1px solid #c2cbd1;
  padding: 50px;
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
