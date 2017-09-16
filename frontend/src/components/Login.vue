<template>
  <div class="login--container">
    <div class="hero is-primary">
      <div class="hero-body">
        <p class="title">
          Spring Framework practice
        </p>
        <p class="subtitle">
          Everything you need to <strong>create a website</strong> with Vue, Bulma, Buefy and MongoDb 2017/09/03
        </p>
      </div>
    </div>
    <div class="login--body">
      <div class="columns">
        <div class="column login--login-box">
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
            <button class="button is-success" @click="login()">
              Login
            </button>
            <button class="button is-info" @click="register()">
              Register
            </button>
          </div>
        </div>
        <div class="column">
          <img src="https://image.freepik.com/free-icon/male-user-shadow_318-34042.jpg" width="200px;">
        </div>
      </div>
      <div class="columns">
        <div class="column">
          <div class="notification is-danger" v-if="checkLoginFailed">
            Wrong username or password <strong>Please register</strong> first.
          </div>
        </div>
        <div class="column">

        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Axios from 'axios'
// Axios.defaults.baseURL = 'http://192.168.1.131:8080'
// Axios.defaults.headers.post['Accept'] = 'application/json'
// Axios.defaults.headers.post['Content-Type'] = 'application/json'
export default {
  data () {
    return {
      username: '',
      password: '',
      checkLoginFailed: false
    }
  },
  components: {
  },
  methods: {
    register () {
      this.$router.replace({ path: '/register' })
    },
    login () {
      let self = this
      Axios.get(`http://localhost:8090/login/${this.username}/${this.password}`).then(function (response) {
        if (response.data === true) {
          self.$router.replace({ path: '/document' })
          self.checkLoginFailed = false
        } else {
          self.checkLoginFailed = true
        }
      }).catch(function (error) {
        console.log(error)
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.login--login-box {
  border: 1px solid #c2cbd1;
  padding: 15px 15px 15px 15px;
}

.login--body {
  margin: 30px;
}
h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
