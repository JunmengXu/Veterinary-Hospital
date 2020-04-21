<template>
    <div>
        <el-menu
                :default-active="'/index'"
                router
                mode="horizontal"
                background-color="white"
                text-color="#222"
                active-text-color="blue"
                style="min-width: 1300px">
            <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name">
                {{ item.navItem }}
            </el-menu-item>
            <!--        <a href="#nowhere" style="color: #222;float: right;padding: 20px;">更多功能</a>-->
            <el-button id="up10" size="medium" type="primary" v-on:click="loginOut">{{$t('button.logOut')}}</el-button>
<!--            <el-button id="up11" size="medium" type="primary" v-on:click="langChange">切换语言</el-button>-->
            <el-dropdown id="up11" @command="personInfo">
                <el-avatar shape="circle" fit="scale-down" :src="userinfo.userimage" v-if="userinfo.userimage"></el-avatar><el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" v-else></el-avatar>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item command="inf">修改个人信息</el-dropdown-item>
                    <el-dropdown-item command="ava">修改头像</el-dropdown-item>
                    <el-dropdown-item command="pas">修改密码</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>

            <el-dropdown id="up9" @command="handleCommand">
                <span class="el-dropdown-link">
                        {{$t('button.switchLanguage')}}
                    <i class="el-icon-arrow-down el-icon--right"></i>
                </span>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item command="cn">{{$t('column.Chinese')}}</el-dropdown-item>
                    <el-dropdown-item command="en">{{$t('column.English')}}</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>


            <i class="el-icon-menu" style="float:right;font-size: 45px;color: #222;padding-top: 8px"></i>
            <span style="position: absolute;padding-top: 20px;right: 43%;font-size: 20px;font-weight: bold">{{$t('menu.title')}}</span>
        </el-menu>

        <el-dialog
                title='修改个人信息'
                :visible.sync="dialogFormInfVisible"
        >
            <el-form :model="formInf" :rules="rules" ref="formInf" class="demo-form-inline">
                <el-form-item label="账户" prop="account">
                    <el-input v-model="formInf.account" placeholder="账户"></el-input>
                </el-form-item>

                <el-form-item label="电子邮箱" prop="email">
                    <el-input v-model="formInf.email" placeholder="电子邮箱"></el-input>
                </el-form-item>
                <el-form-item label="电话" prop="phoneNumber">
                    <el-input v-model="formInf.phoneNumber" placeholder="电话号码"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitInfForm()">{{$t('button.add')}}</el-button>
                    <el-button @click="resetInfForm()">{{$t('button.reset')}}</el-button>
                </el-form-item>
            </el-form>

            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormInfVisible = false">{{$t('button.cancel')}}</el-button>
            </div>
        </el-dialog>


        <el-dialog
            title='修改密码'
            :visible.sync="dialogFormPasVisible"
    >
        <el-form :model="formPas" :rules="rules2" ref="formPas" class="demo-form-inline">
            <el-form-item label="原密码" prop="originPas">
                <el-input v-model="formPas.originPas" placeholder="原密码" show-password></el-input>
            </el-form-item>

            <el-form-item label="新密码" prop="newPas">
                <el-input v-model="formPas.newPas" placeholder="新密码" show-password></el-input>
            </el-form-item>
            <el-form-item label="二次确认" prop="new2Pas">
                <el-input v-model="formPas.new2Pas" placeholder="二次确认" show-password></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitPasForm()">{{$t('button.add')}}</el-button>
                <el-button @click="resetPasForm()">{{$t('button.reset')}}</el-button>
            </el-form-item>
        </el-form>

        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormPasVisible = false">{{$t('button.cancel')}}</el-button>
        </div>
    </el-dialog>

        <el-dialog
                title='修改头像'
                :visible.sync="dialogFormAvaVisible"
        >
            <el-form :model="formAva" ref="formAva" class="demo-form-inline">
                <el-form-item label="头像" :label-width="formLabelWidth">
                    <el-input v-model="formAva.avatar" autocomplete="off" placeholder="图片 URL" disabled></el-input>
                    <user-ava @onUpload="uploadImg" @removeUpload="removeImg" ref="UserAva"></user-ava>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitAvaForm()">{{$t('button.add')}}</el-button>
                </el-form-item>
            </el-form>

            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormAvaVisible = false">{{$t('button.cancel')}}</el-button>
            </div>
        </el-dialog>

    </div>
</template>

<script>
    import store from "../../store";
    import UserAva from "../uploadimg/UserAva";

    export default {
        name: "CustomerHeader",
        inject:['reload'],
        components: {UserAva},
        props: {
            msg: String
        },
        data () {
            // <!--二次验证密码-->
            let validatePass2 = (rule, value, callback) => {
                if (value === "") {
                    callback(new Error("请再次输入密码"));
                } else if (value !== this.formPas.newPas) {
                    callback(new Error("两次输入密码不一致!"));
                } else {
                    callback();
                }
            };
            return {
                lang: "",

                userinfo:'',
                originPass:'',

                dialogFormInfVisible: false,
                dialogFormAvaVisible: false,
                dialogFormPasVisible: false,

                formInf: {
                    account: '',
                    email: '',
                    phoneNumber: ''
                },
                rules: {
                    account: [
                        { required: true, message: "请填写账户", trigger: 'blur' }
                    ],
                    email: [
                        { required: true, message: "请填写邮箱", trigger: 'blur' }
                    ],
                    phoneNumber: [
                        { required: true, message: "请填写电话号码", trigger: 'blur' }
                    ]
                },

                formPas: {
                    originPas: '',
                    newPas: '',
                    new2Pas: ''
                },
                rules2: {
                    originPas: [
                        { required: true, message: "请填写原密码", trigger: 'blur' }
                    ],
                    newPas: [
                        { required: true, message: "请填写新密码", trigger: 'blur' }
                    ],
                    new2Pas: [
                        { required: true, validator: validatePass2, trigger: 'blur' }
                    ]
                },

                formAva:{
                    avatar:''
                },

                formLabelWidth: '120px'
            }
        },
        //这里需要把数据写到computed里面否则组件不会实时刷新
        computed:{
           navList:function () {
               return [
                   {name: '/indexc', navItem: this.$t('menu.home')},
                   {name: '/petc', navItem: this.$t('button.customerHomepagePet')},
                   {name: '/appointmentc', navItem: this.$t('button.customerHomepageAppointment')},
                   {name: '/messagec', navItem: this.$t('button.customerHomepageMessage')}
               ]

           }

        },
        created() {
            const _this = this
            var username = store.state.user.username
            var url = 'http://localhost:8181/api/personInfo/' + username
            this.$http.get(url).then(function (resp){
                console.log(resp)
                _this.userinfo = resp.data
                _this.formInf.account = _this.userinfo.account
                _this.formInf.email = _this.userinfo.email
                _this.formInf.phoneNumber = _this.userinfo.phoneNumber
                _this.originPass = _this.userinfo.password
            })
        },
        methods: {
            loginOut () {
                localStorage.removeItem('user')
                // localStorage.removeItem('login')
                alert(this.$t('message.exit'))
                this.$router.replace('/loginc')
            },
            // 根据下拉框的中被选中的值切换语言
            handleCommand(command) {
                // this.$message("click on item " + command);
                switch (command) {
                    case "cn": {
                        this.lang = "cn";
                        this.$i18n.locale = this.lang;
                        //本地保存语言设置，刷新页面或重新进入后也可维持原有的设置
                        localStorage.setItem('lang', this.lang);
                        break;
                    }
                    case "en": {
                        this.lang = "en";
                        this.$i18n.locale = this.lang;
                        localStorage.setItem('lang', this.lang);
                        break;
                    }

                    default:
                        break;
                }
            },
            personInfo(command){
                switch (command) {
                    case "inf": {
                        this.dialogFormInfVisible = true
                        break;
                    }
                    case "ava": {
                        this.dialogFormAvaVisible = true
                        break;
                    }
                    case "pas": {
                        this.dialogFormPasVisible = true
                        break;
                    }
                    default:
                        break;
                }
            },
            submitInfForm() {
                this.$refs.formInf.validate((valid) => {
                    if (valid) {
                        // alert('submit!');
                        // console.log(this.formInline.petName+" "+this.formInline.petType+" "+this.formInline.petGender)
                        var username = store.state.user.username
                        var url = 'http://localhost:8181/api/changeInf/' + username
                        this.$http
                            .post(url, {
                                account: this.formInf.account,
                                email: this.formInf.email,
                                phoneNumber: this.formInf.phoneNumber,
                            }).then(resp => {
                            if (resp && resp.status === 200) {
                                this.dialogFormVisible = false
                                this.$emit('onSubmit')
                                this.$notify({
                                    title: this.$t('message.successed'),
                                    message: "修改成功",
                                    type: 'success'
                                });
                                console.log(JSON.stringify(resp))
                                this.reload();
                            }else{
                                this.$notify.error({
                                    title: this.$t('message.failed'),
                                    message: "修改失败"
                                });
                            }
                        })

                    } else {
                        // console.log('error submit!!');
                        this.$notify.error({
                            title: this.$t('message.failed'),
                            message: "修改失败"
                        });
                        return false;
                    }
                });
            },
            resetInfForm() {
                this.$refs.formInf.resetFields();
            },
            submitPasForm() {
                this.$refs.formPas.validate((valid) => {
                    if (valid) {
                        // alert('submit!');
                        // console.log(this.formInline.petName+" "+this.formInline.petType+" "+this.formInline.petGender)
                        if (this.originPass == this.formPas.originPas){
                            var username = store.state.user.username
                            const _this = this
                            var url = 'http://localhost:8181/api/changePas/' + username
                            this.$http
                                .post(url, {
                                    password: this.formPas.newPas
                                }).then(resp => {
                                if (resp && resp.status === 200) {
                                    this.dialogFormVisible = false
                                    this.$emit('onSubmit')
                                    this.$notify({
                                        title: this.$t('message.successed'),
                                        message: "修改成功，请重新登录",
                                        type: 'success'
                                    });
                                    setTimeout(function () {
                                        _this.loginOut()
                                    },100);
                                }else{
                                    this.$notify.error({
                                        title: this.$t('message.failed'),
                                        message: "修改失败"
                                    });
                                }
                            })
                        }else{
                            this.$notify.error({
                                title: this.$t('message.failed'),
                                message: "修改失败,原密码错误"
                            });
                        }


                    } else {
                        // console.log('error submit!!');
                        this.$notify.error({
                            title: this.$t('message.failed'),
                            message: "修改失败"
                        });
                        return false;
                    }
                });
            },
            resetPasForm() {
                this.$refs.formPas.resetFields();
            },
            uploadImg () {
                this.formAva.avatar = this.$refs.UserAva.imageUrl
            },
            removeImg () {
                this.formAva.avatar = ''
            },
            submitAvaForm() {
                this.$refs.formAva.validate((valid) => {
                    if (valid) {
                        var username = store.state.user.username
                        var url = 'http://localhost:8181/api/changeAva/' + username
                        this.$http
                            .post(url, {
                                userimage: this.formAva.avatar
                            }).then(resp => {
                            if (resp && resp.status === 200) {
                                this.dialogFormVisible = false
                                this.$emit('onSubmit')
                                this.$notify({
                                    title: this.$t('message.successed'),
                                    message: "上传头像成功",
                                    type: 'success'
                                });
                                console.log(JSON.stringify(resp))
                                this.reload();
                            }else{
                                this.$notify.error({
                                    title: this.$t('message.failed'),
                                    message: "上传头像失败"
                                });
                            }
                        })

                    } else {
                        // console.log('error submit!!');
                        this.$notify.error({
                            title: this.$t('message.failed'),
                            message: "上传头像失败"
                        });
                        return false;
                    }
                });
            },
        }
    }
</script>

<style scoped>
    #icon{
        float: left;
        margin-top: 10px;
        font-size: 20px;
        margin-left: 40px;
    }
    #up1,#up2,#up3,#up4{
        float: left;
        margin-top: 10px;
        margin-left: 15px;
    }
    #up1{
        margin-right: 35px;
    }
    #up9,#up10,#up11 {
        margin-top: 3px;
        margin-right: 10px;
        margin-left: 10px;
        float: right;
    }
    #up10,#up9{
        margin-top: 12px;
    }
    #up11{
        margin-top: 10px;
    }
    #up{
        height: fit-content;
        padding: 1px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
        /*float: right;*/
    }
    .icon {
        width: 1em;
        height: 1em;
        vertical-align: -0.15em;
        fill: currentColor;
        overflow: hidden;
    }
    ul {
        list-style-type: none;
        margin: 6px 0px 14px;
    }
    li {
        display: inline-block;
        margin: 0 10px;
    }
</style>