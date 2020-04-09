<template>
  <div id="app">
<!--    <Header/>-->
<!--    <Homepage/>-->
<!--    <Timetable/>-->
<!--    <Console/>-->
<!--    <DailyDetail/>-->
<!--    <BookingDetail/>-->
<!--    <router-link to="/login">Login</router-link>-->
      <keep-alive>
      <router-view v-show="$route.meta.keepAlive"/>
      </keep-alive>

      <router-view v-if="isRouterAlive"/>
      <!--在注册页面不显示公共组件也就是登录页面-->
<!--      <LoginStaff v-show="!(path ==='/register') "></LoginStaff>-->


  </div>
</template>

<script>
  // import Header from './components/Header.vue'
  // import BookingDetail from "@/components/BookingDetail";
  // import DailyDetail from "@/components/DailyDetail";
  // import Homepage from "./components/Homepage";
  // import Console from "@/components/Console";
  // import Timetable from "@/components/Timetable";
 // import Register from "../components/Register"
 //  import LoginStaff from "./components/staff/LoginStaff";
  
  export default {
    name: 'App',
      //声明path变量
      provide(){
        return{
            reload:this.reload
        }
      },
      data(){
        return{
            path : '',
            isRouterAlive:true
        }
      },
      components: {

    },
      mounted() {
          this.path = this.$route.path;
          // console.log(this.$route.path)
      },
      //配置监听，方法参考https://www.cnblogs.com/hz-handofgod/p/11982095.html
      watch: {
          // eslint-disable-next-line no-unused-vars
          $route(to, from) {
              this.path = to.path
          }
      },
      methods:{
        reload(){
            this.isRouterAlive = false
            this.$nextTick(function () {
                this.isRouterAlive = true
            })
        }
      }

  }
</script>

<style>
  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    /*text-align: center;*/
    color: #2c3e50;
    /*margin-top: 60px;*/
  }
</style>
