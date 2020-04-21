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
// import VueI18n from 'vue-i18n'
import i18n from "./i18n/i18n";
Vue.use(Element)
//Vue.use(VueI18n)
// import locale from 'element-ui/lib/locale/lang/en'

// const i18n = new VueI18n({
//     locale: 'zh', // 语言标识
//     messages: {
//         'zh': require('./assets/lang/zh'),
//         'en': require('./assets/lang/en')
//     }
// })
//    移动端访问时，在package.json里加 "dev": "webpack-dev-server --inline --progress --config build/webpack.dev.conf.js --host 192.168.1.12",
//    并修改向后端传数据的端口

Vue.config.productionTip = false
// Vue.use(Element, { locale })

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
    i18n,
    store,
    components: { App },
    template: '<App/>'
}).$mount('#app')
