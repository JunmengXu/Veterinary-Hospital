<template>

        <div id = 'a'>
           <el-form class="register-container" label-position="left"
                     label-width="0px">
              <h3 class="register_title">{{$t('menu.register')}}</h3>
                <el-form-item>
                    <el-input type="text" v-model="registerForm.username"
                              auto-complete="off" :placeholder= "$t('placeholder.account')" ></el-input>
                </el-form-item>
                 <el-form-item>
                    <el-input type="password" v-model="registerForm.password"
                              auto-complete="off" :placeholder="$t('placeholder.password')"></el-input>
                </el-form-item>
               <el-form-item>
                   <el-input type="text" v-model="registerForm.email"
                             auto-complete="off" :placeholder="$t('placeholder.email')"></el-input>
               </el-form-item>
               <el-form-item>
                   <el-input type="text" v-model="registerForm.phone"
                             auto-complete="off" :placeholder="$t('placeholder.phone')"></el-input>
               </el-form-item>
                <el-form-item style="width: 100%">
                    <el-button type="primary" style="width: 100%;background: #505458;border: none" v-on:click="register">{{$t('button.register')}}</el-button>
                </el-form-item>
            </el-form>
        </div>
</template>

<script>
    export default {
        name: "Register",
        data(){
            return{
                registerForm: {
                    username: '',
                    password: '',
                    email: '',
                    phone: ''
                },
                responseResult: []
            }
        },
        methods:{
            register () {
                // var _this = this
                console.log(this.$store.state)
                this.$http
                    .post('http://localhost:8181/api/register', {
                        username: this.registerForm.username,
                        password: this.registerForm.password,
                        email:this.registerForm.email,
                        phoneNumber:this.registerForm.phone
                    })
                    .then(successResponse => {
                        if (successResponse.data.code === 200) {
                            // var data = this.loginForm
                            this.$store.commit('register', this.registerForm)
                            var path = this.$route.query.redirect
                            this.$router.replace({path: path === '/' || path === undefined ? '/index' : path})
                        }
                        else if(successResponse.data.code === 400){
                            alert(this.$t('message.alreadyExist'))
                        }
                    })
                    // eslint-disable-next-line no-unused-vars
                    .catch(failResponse => {
                    })
            },
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