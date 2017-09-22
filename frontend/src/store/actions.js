import axios from 'axios'

getCoupons ({ commit }, payload) {
  Axios.get(`http://localhost:8090/users/`).then(function (response) {
    console.log('get user' + response);
    // commit('getUser', response.data.results.result)
  }).catch(function (error) {
    console.log(error)
  })
},
