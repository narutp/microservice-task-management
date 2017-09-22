import Vue from 'vue'
import Vuex from 'vuex'
import Axios from 'axios'
Vue.use(Vuex)

let vuex = {
  state: {
    getUser: []
  },
  getters: {
    getUser: state => state.getUser
  },
  mutations: {
    getUser (state, data) {
      state.coupons = data
    }
  },
  actions: {
    getUser ({ commit }) {
      Axios.get(`http://localhost:8090/users/`).then(function (response) {
        console.log('get user' + response)
        commit('getUser', response.data)
      }).catch(function (error) {
        console.log(error)
      })
    }
  }
}
export let store = new Vuex.Store(vuex)
