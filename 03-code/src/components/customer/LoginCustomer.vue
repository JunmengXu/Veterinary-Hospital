<template>

    <body id="poster">
    <el-form class="login-container" label-position="left"
             label-width="0px">
        <h3 class="login_title">系统登录</h3>
        <el-form-item>
            <el-input type="text" v-model="loginForm.username"
                      auto-complete="off" placeholder="账号"></el-input>
        </el-form-item>
        <el-form-item>
            <el-input type="password" v-model="loginForm.password"
                      auto-complete="off" placeholder="密码"></el-input>
        </el-form-item>
        <el-form-item style="width: 100%">
            <el-button type="primary" style="width: 100%;background: #505458;border: none" v-on:click="login">登录</el-button>
        </el-form-item>
        <el-form-item style="width: 100%">
          <!--  <router-link to = '/register' target="_blank">去注册</router-link>-->
            <el-button type="primary" style="width: 100%;background: #505458;border: none" v-on:click="goRegister">还没有账户，先去注册</el-button>
        </el-form-item>
    </el-form>
    </body>

</template>

<script>
   // import Register from "../components/Register"
    export default {
        //表单
        name: 'Login',

        data () {
            return {
                loginForm: {
                    username: '',
                    password: ''
                },
                responseResult: []
            }
        },
        //向后端发送用户名密码，进行验证
        methods: {
            login () {
                // var _this = this
                console.log(this.$store.state)
                this.$http
                    .post('http://localhost:8181/api/login', {
                        username: this.loginForm.username,
                        password: this.loginForm.password
                    })
                    .then(successResponse => {
                        if (successResponse.data.code === 200) {
                            // var data = this.loginForm
                            this.$store.commit('login', this.loginForm)
                            var path = this.$route.query.redirect
                            this.$router.replace({path: path === '/' || path === undefined ? '/index' : path})
                        }
                    })
                    // eslint-disable-next-line no-unused-vars
                    .catch(failResponse => {
                    })
            },
            goRegister(){
                this.$router.push({path:'/register'})
            }
        }
        // mounted() {
        //     // 关闭浏览器窗口的时候清空浏览器缓存在localStorage的数据
        //     window.onbeforeunload = function () {
        //         var storage = window.localStorage;
        //         storage.clear()
        //     }
        // }
    }
</script>

<style>
    #poster {
        background:url("../../assets/image/login_bg.jpg") no-repeat;
        background-position: center;
        height: 100%;
        width: 100%;
        background-size: cover;
        position: fixed;
    }
    body{
        margin: 0px;
    }
    .login-container {
        border-radius: 15px;
        background-clip: padding-box;
        margin: 90px auto;
        width: 350px;
        padding: 35px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }
    .login_title {
        margin: 0px auto 40px auto;
        text-align: center;
        color: #505458;
    }

</style>