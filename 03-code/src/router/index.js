import Vue from 'vue'
import Router from 'vue-router'
// 员工界面
import Login from '../components/staff/LoginStaff'
import StaffHome from "../components/staff/StaffHome"
import BookingDetail from "../components/staff/BookingDetail"
import DailyDetail from "../components/staff/DailyDetail"
import Homepage from "../components/staff/Homepage"
// import Console from "../components/staff/Console"
import Timetable from "../components/staff/Timetable";
import StaffMessageHome from "../components/staff/StaffMessageHome"
// 客户界面
import Register from "../components/Register"
import LoginCustomer from "../components/customer/LoginCustomer";
import CustomerHome from "../components/customer/CustomerHome";
import CustomerHomePage from "../components/customer/CustomerHomePage";
import CustomerBooking from "../components/customer/CustomerBooking";
import CustomerMessage from "../components/customer/CustomerMessage";
import CustomerPet from "../components/customer/CustomerPet";


Vue.use(Router)

//路由， 控制不同的路径导向不同的页面
export default new Router({
  mode: 'history',
  //----------员工-------------
  routes: [
    {
      //员工登录界面
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      //员工主界面
      path: '/home',
      name: 'StaffHome',
      component: StaffHome,
      redirect: '/index',
      children: [
        {
          path: '/index',
          name: 'Homepage',
          component: Homepage,
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
          path: '/appointment',
          name: 'BookingDetail',
          component: BookingDetail,
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
        },
        {
          path: '/message',
          name: 'StaffMessageHome',
          component: StaffMessageHome,
          meta: {
            requireAuth: true
          }
        }
        ]
    },
    //----------客户-------------
    {
      //客户注册界面
      path:'/register',
      name: 'Register',
      component: Register
    },
    {
      //客户登录界面
      path:'/loginc',
      name: 'LoginCustomer',
      component: LoginCustomer
    },
    {
      //客户主界面
      path:'/homec',
      name: 'CustomerHome',
      component: CustomerHome,
      redirect: '/indexc',
      children: [
        {
          path: '/indexc',
          name: 'CustomerHomePage',
          component: CustomerHomePage,
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/appointmentc',
          name: 'CustomerBooking',
          component: CustomerBooking,
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/messagec',
          name: 'CustomerMessage',
          component: CustomerMessage,
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/petc',
          name: 'CustomerPet',
          component: CustomerPet,
          meta: {
            requireAuth: true
          }
        }
      ]
    }
  ]
})
