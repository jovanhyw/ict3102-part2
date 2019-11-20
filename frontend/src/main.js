import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import ApiService from './services/api.service'

Vue.config.productionTip = false

// ApiService.init('http://localhost:5000/api')
ApiService.init('http://localhost:8762/yolo/api')

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
