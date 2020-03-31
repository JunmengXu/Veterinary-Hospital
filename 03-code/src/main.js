import Vue from 'vue'
import App from './App.vue'
import Element from 'element-ui'
import router from './router'
import axios from 'axios'
Vue.prototype.$http = axios
import 'element-ui/lib/theme-chalk/index.css';
import './font/iconfont.js';

Vue.config.productionTip = false
Vue.use(Element)

new Vue({
  render: h => h(App),
  router,
}).$mount('#app')
