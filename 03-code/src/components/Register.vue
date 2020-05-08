<template>

        <div id = 'a'>
           <el-form class="register-container" label-position="left"
                     label-width="0px" :rules="rules" ref = "registerForm" :model = "registerForm">
              <h3 class="register_title">{{$t('menu.register')}}</h3>
                <el-form-item  prop="username">
                    <el-input type="text" v-model="registerForm.username"
                              auto-complete="off" :placeholder= "$t('placeholder.account')" ></el-input>
                </el-form-item>
                 <el-form-item prop="password">
                    <el-input type="password" v-model="registerForm.password"
                              auto-complete="off" :placeholder="$t('placeholder.password')" show-password></el-input>
                </el-form-item>
               <el-form-item  prop="email">
                   <el-input type="text" v-model="registerForm.email"
                             auto-complete="off" :placeholder="$t('placeholder.email')"></el-input>
               </el-form-item>
               <el-form-item prop="phone">
                   <el-input type="text" v-model="registerForm.phone"
                             auto-complete="off" :placeholder="$t('placeholder.phone')"></el-input>
               </el-form-item>
                <el-form-item style="width: 100%">
                    <el-button type="primary" style="width: 100%;background: #505458;border: none" v-on:click="register">{{$t('button.register')}}</el-button>
                </el-form-item>
               <el-form-item style="width: 100%">
                   <el-button type="primary" style="width: 100%;background: #505458;border: none" v-on:click="back">{{$t('button.back')}}</el-button>
               </el-form-item>
            </el-form>
        </div>
</template>

<script>
    import crypto from 'crypto'
    export default {
        name: "Register",
        data(){
            var validateEmail = (rule, value, callback) => {
                const emailRegex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
                if (!emailRegex.test(value)) {
                    callback(new Error('邮箱格式不正确！'))
                } else {
                    callback()
                }
            };
            return{
                registerForm: {
                    username: '',
                    password: '',
                    email: '',
                    phone: ''
                },
                rules: {
                    username: [
                        { required: true, message: "请输入用户名", trigger: 'blur' },
                        { min: 1, max: 10, message: "长度需在1至10个字符", trigger: 'change' }
                    ],
                    password: [
                        { required: true, message: "请输入密码", trigger: 'blur' },
                        { min: 1, max: 10, message: "长度需在1至10个字符", trigger: 'change' }
                    ],
                    email: [
                        { required: true, message: "请输入邮箱", trigger: 'blur' },
                        {validator: validateEmail}
                    ],
                    phone: [
                        { required: true, message: "请输入电话号码", trigger: 'blur' }
                    ]

                },
                responseResult: []
            }
        },
        methods:{
            register () {
                this.$refs.registerForm.validate((valid) => {
                    if(valid){
                        var md5 = crypto.createHash("md5")
                        md5.update(this.registerForm.password)
                        this.pw = md5.digest('hex')

                        this.$http
                            .post('http://localhost:8181/api/register', {
                                username: this.registerForm.username,
                                password: this.pw,
                                email:this.registerForm.email,
                                phoneNumber:this.registerForm.phone
                            })
                            .then(successResponse => {
                                if (successResponse.data.code === 200) {
                                    // var data = this.loginForm
                                    this.$store.commit('register', this.registerForm)
                                    var path = this.$route.query.redirect
                                    this.$router.replace({path: path === '/' || path === undefined ? '/loginc' : path ,query:{username:this.registerForm.username, password: this.registerForm.password}})
                                }
                                else if(successResponse.data.code === 400){
                                    this.$notify.error({
                                        title: this.$t('message.failed'),
                                        message: "注册失败，用户已存在！"
                                    });
                                }
                            })
                            // eslint-disable-next-line no-unused-vars
                            .catch(failResponse => {
                            })
                    }
                    else{
                        this.$notify.error({
                            title: this.$t('message.failed'),
                            message: "注册失败，请填写全部项目！"
                        });
                    }
                });

            },
            back(){
                this.$router.push({path:'/loginc'})
            }
        }
    }
</script>

<style scoped>
    #a {
        background:url("../assets/image/login_bg.jpg") no-repeat;
        background-position: center;
        height: 100%;
        width: 100%;
        background-size: cover;
        position: fixed;

    }
    .register-container {
        border-radius: 15px;
        background-clip: padding-box;
        margin: 90px auto;
        width: 350px;
        padding: 35px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }
    .register_title {
        margin: 0px auto 40px auto;
        text-align: center;
        color: #505458;
    }
</style>