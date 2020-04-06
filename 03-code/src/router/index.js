import Vue from 'vue'
import Router from 'vue-router'
import AppIndex from '../components/home/Appindex'
import Login from '../components/LoginCustomer'
import Register from "../components/Register"



Vue.use(Router)

//路由， 控制不同的路径导向不同的页面
export default new Router({
  mode: 'history',
  routes: [
    {
      //登录界面
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      //主界面
      path: '/index',
      name: 'AppIndex',
      component: AppIndex,
      // 区分页面是否需要拦截
      meta: {
        requireAuth: true
      }
    },
    {
      //注册界面
      path:'/register',
      name: 'Register',
      component: Register
    }
  ]
})
