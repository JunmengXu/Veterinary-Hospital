import Vue from 'vue'
import App from './App.vue'
import Element from 'element-ui'
import router from './router'
import axios from 'axios'
Vue.prototype.$http = axios
import store from './store'
import 'element-ui/lib/theme-chalk/index.css';
import './font/iconfont.js';
// 控件中英文切换
// import locale from 'element-ui/lib/locale/lang/en'

Vue.config.productionTip = false
// Vue.use(Element, { locale })
Vue.use(Element)

//使用钩子函数判断是否拦截.首先判断访问的路径是否需要登录，如果需要，判断 store 里有没有存储 user 的信息，
// 如果存在，则放行，否则跳转到登录页面，并存储访问的页面路径（以便在登录后跳转到访问页）。
router.beforeEach((to, from, next) => {
      if (to.meta.requireAuth) {
        if (store.state.user.username) {
          next()
        } else {
          next({
            path: 'login',
            query: {redirect: to.fullPath}
          })
        }
      } else {
        next()
      }
    }
)

new Vue({
    render: h => h(App),
    router,
    store,
    components: { App },
    template: '<App/>'
}).$mount('#app')
