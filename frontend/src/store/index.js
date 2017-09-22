import Vuex from 'vuex'
import * as getters from './getters'
import * as actions from './actions'
import * as mutations from './mutations'

const state = {
  user: {}
}

const store = () => {
  return new Vuex.Store({
    state,
    actions,
    mutations,
    getters
  })
}
export default store
