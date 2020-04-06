import Vue from 'vue'
import Router from 'vue-router'
import AppIndex from '../components/home/Appindex'
import Register from "../components/Register"

import Login from '../components/staff/LoginStaff'
import StaffHome from "../components/staff/StaffHome"
import BookingDetail from "../components/staff/BookingDetail"
// import DailyDetail from "../components/staff/DailyDetail"
// import Homepage from "../components/staff/Homepage"
// import Console from "../components/staff/Console"
// import Timetable from "../components/staff/Timetable";

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
      path: '/home',
      name: 'StaffHome',
      component: StaffHome,
      redirect: '/index',
      children: [
        {
          path: '/index',
          name: 'AppIndex',
          component: AppIndex,
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/BookingDetail',
          name: 'BookingDetail',
          component: BookingDetail,
          meta: {
            requireAuth: true
          }
        }]
    },
    {
      //注册界面
      path:'/register',
      name: 'Register',
      component: Register
    }
  ]
})
