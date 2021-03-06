import Vue from 'vue'
import Vuex from 'vuex'
import Axios from 'axios'
Vue.use(Vuex)

let vuex = {
  strict: process.env.NODE_ENV !== 'production',
  state: {
    user: []
  },
  getters: {
    GET_USER: (state, getters) => state.user
  },
  mutations: {
    SET_USER (state, data) {
      state.user = data
    }
  },
  actions: {
    SET_USER ({ commit, data }, username) {
      // Axios.get(`http://localhost:8090/get/all-user/`).then(function (response) {
      //   console.log('get user' + response)
      //   commit('SET_USER', response.data)
      // }).catch(function (error) {
      //   console.log(error)
      // })
      Axios.get(`http://localhost:8090/get/user/username/${username}`).then(function (response) {
        console.log('get user ' + username)
        commit('SET_USER', response.data)
      }).catch(function (error) {
        console.log(error)
      })
    }
  }
}
export let store = new Vuex.Store(vuex)
