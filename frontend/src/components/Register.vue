<template lang="html">
  <div class="register--container">
    <div class="hero is-info">
      <div class="hero-body">
        <p class="title">
          Spring Framework practice
        </p>
        <p class="subtitle">
          Everything you need to <strong>create a website</strong> with Vue, Bulma and MongoDb 2017/09/03
        </p>
      </div>
    </div>
    <div class="register--body">
      <div class="columns">
        <div class="column register--register-box">
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
          <div align="right">
            <button class="button is-success" @click="register()">
              Submit
            </button>
            <button class="button is-danger" @click="backLogin()">
              Back
            </button>
          </div>
        </div>
        <div class="column">
          <img src="https://www.flmontreal.com/wp-content/uploads/2014/09/Register-Icon.png" width="200px">
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Axios from 'axios'
// Axios.defaults.baseURL = 'http://192.168.1.131:8080'
export default {
  data () {
    return {
      username: '',
      password: ''
    }
  },
  methods: {
    backLogin () {
      this.$router.replace({ path: '/' })
    },
    register () {
      let self = this
      Axios.get(`http://localhost:8090/register/${this.username}/${this.password}`).then(function (response) {
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
</style>
