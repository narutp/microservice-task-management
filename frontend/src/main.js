// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import bulma from 'bulma/css/bulma.css'
import Buefy from 'buefy'
import moment from 'moment'
import 'buefy/lib/buefy.css'

Vue.config.productionTip = false
Vue.use(bulma)
Vue.use(Buefy)
Vue.use(moment)
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})
