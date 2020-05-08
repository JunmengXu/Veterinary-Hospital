<template>

    <body id="poster">
    <el-form class="login-container" label-position="left"
             label-width="0px" >
        <h3 class="login_title">{{$t('menu.customerLogin')}}</h3>
        <el-form-item>
            <el-input type="text" v-model="loginForm.username"
                      auto-complete="off" :placeholder="$t('placeholder.account')"></el-input>
        </el-form-item>
        <el-form-item>
            <el-input type="password" v-model="loginForm.password"
                      auto-complete="off" :placeholder="$t('placeholder.password')"></el-input>
        </el-form-item>
        <el-form-item style="width: 100%">
            <el-button type="primary" style="width: 100%;background: #505458;border: none" v-on:click="login">{{$t('button.login')}}</el-button>
        </el-form-item>
        <el-form-item style="width: 100%">
          <!--  <router-link to = '/register' target="_blank">去注册</router-link>-->
            <el-button type="primary" style="width: 100%;background: #505458;border: none" v-on:click="goRegister">{{$t('button.goRegister')}}</el-button>
        </el-form-item>
        <el-form-item style="width: 100%">
            <el-button type="primary" style="width: 100%;background: #505458;border: none" v-on:click="staff">{{$t('button.staffEntrance')}}</el-button>
        </el-form-item>
    </el-form>
    </body>

</template>

<script>
    import crypto from 'crypto'
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
        created:function(){
            this.getParams();

            //添加监听回车按键
            var _this = this;
            document.addEventListener("keydown", _this.watchEnter);
        },
        destroyed() {
            //移除监听回车按键
            var _this = this;
            document.removeEventListener("keydown", _this.watchEnter);
        },
        watch: {
            // 监测路由变化,只要变化了就调用获取路由参数方法将数据存储本组件即可
            '$route': 'getParams'
        },
        //向后端发送用户名密码，进行验证
        methods: {
            getParams(){
                var name = this.$route.query.username
                var password = this.$route.query.password
                // 将数据放在当前组件的数据内
                this.loginForm.username = name
                this.loginForm.password = password
            },
            login () {
                // var _this = this
                var md5 = crypto.createHash("md5")
                md5.update(this.loginForm.password)
                this.pw = md5.digest('hex')
                console.log(this.$store.state)
                this.$http
                    .post('http://localhost:8181/api/login', {
                        username: this.loginForm.username,
                        password: this.pw
                    })
                    .then(successResponse => {
                        if (successResponse.data.code === 300) {
                            // var data = this.loginForm
                            this.$store.commit('login', this.loginForm)
                            var path = this.$route.query.redirect
                            this.$router.replace({path: path === '/' || path === undefined ? '/indexc' : path})
                        }else{
                            this.$message.error('账号或密码错误');
                        }
                    })
                    // eslint-disable-next-line no-unused-vars
                    .catch(failResponse => {
                    })
            },
            goRegister(){
                this.$router.push({path:'/register'})
            },
            staff(){
                this.$router.push({path:'/Login'})
            },
            //监听回车按钮事件
            watchEnter(e) {
                var keyNum = window.event ? e.keyCode : e.which; //获取被按下的键值
                //判断如果用户按下了回车键（keycody=13）
                if (keyNum == 13) {
                    this.login();
                }
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