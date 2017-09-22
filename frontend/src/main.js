// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import bulma from 'bulma/css/bulma.css'
import Buefy from 'buefy'
import moment from 'moment'
import ElementUI from 'element-ui'
import { store } from './store'
import locale from 'element-ui/lib/locale/lang/en'
import 'element-ui/lib/theme-default/index.css'
import 'buefy/lib/buefy.css'

Vue.config.productionTip = false
Vue.use(bulma)
Vue.use(Buefy)
Vue.use(ElementUI, { locale })
Vue.use(moment)
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App }
})
