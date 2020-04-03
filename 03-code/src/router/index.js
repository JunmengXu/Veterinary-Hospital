import Vue from 'vue'
import Router from 'vue-router'
// import AppIndex from '../components/home/Appindex'
import Login from '../components/LoginCustomer'
import StaffHome from "../components/StaffHome"
import BookingDetail from "@/components/BookingDetail"
import DailyDetail from "@/components/DailyDetail"
import Homepage from "@/components/Homepage"
import Console from "@/components/Console"
import Timetable from "@/components/Timetable";

Vue.use(Router)

//路由， 控制不同的路径导向不同的页面
export default new Router({
  mode: 'history',
  routes: [
      // 默认路由
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      //登录界面
      path: '/login',
      name: 'Login',
      component: Login
    },
    // {
    //   //主界面
    //   path: '/index',
    //   name: 'AppIndex',
    //   component: AppIndex,
    //   // 区分页面是否需要拦截
    //   meta: {
    //     requireAuth: true
    //   }
    // },
    {
      path: '/home',
      name: 'StaffHome',
      component: StaffHome,
      // home页面并不需要被访问
      redirect: '/index',
      children: [
        {
          path: '/index',
          name: 'Homepage',
          component: Homepage,
          // 区分页面是否需要拦截
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/appointment',
          name: 'BookingDetail',
          component: BookingDetail,
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/message',
          name: 'Console',
          component: Console,
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/history',
          name: 'DailyDetail',
          component: DailyDetail,
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/timetable',
          name: 'Timetable',
          component: Timetable,
          meta: {
            requireAuth: true
          }
        }
      ]
    }
  ]
})
