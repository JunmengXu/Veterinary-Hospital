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
                    <el-dropdown-item command="inf">{{$t('button.modifyInformation')}}</el-dropdown-item>
                    <el-dropdown-item command="ava">{{$t('button.modifyImage')}}</el-dropdown-item>
                    <el-dropdown-item command="pas">{{$t('button.modifyPassword')}}</el-dropdown-item>
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
                :title="$t('button.modifyInformation')"
                :visible.sync="dialogFormInfVisible"
        >
            <el-form :model="formInf" :rules="rules" ref="formInf" class="demo-form-inline">
                <el-form-item :label="$t('column.account')" prop="account">
                    <el-input v-model="formInf.account" :placeholder="$t('column.account')"></el-input>
                </el-form-item>

                <el-form-item :label="$t('column.email')" prop="email">
                    <el-input v-model="formInf.email" :placeholder="$t('column.email')"></el-input>
                </el-form-item>
                <el-form-item :label = "$t('column.phone')" prop="phoneNumber">
                    <el-input v-model="formInf.phoneNumber" :placeholder="$t('column.phone')"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitInfForm()">{{$t('button.modify')}}</el-button>
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
                <el-form-item :label="$t('column.oldPassword')" prop="originPas">
                    <el-input v-model="formPas.originPas" :placeholder="$t('column.oldPassword')" show-password></el-input>
                </el-form-item>

                <el-form-item :label="$t('column.newPassword')" prop="newPas">
                    <el-input v-model="formPas.newPas" :placeholder="$t('column.newPassword')" show-password></el-input>
                </el-form-item>
                <el-form-item :label="$t('column.doubleCheck')" prop="new2Pas">
                    <el-input v-model="formPas.new2Pas" :placeholder="$t('column.doubleCheck')"  show-password></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitPasForm()">{{$t('button.modify')}}</el-button>
                    <el-button @click="resetPasForm()">{{$t('button.reset')}}</el-button>
                </el-form-item>
            </el-form>

            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormPasVisible = false">{{$t('button.cancel')}}</el-button>
            </div>
        </el-dialog>

        <el-dialog
                :title="$t('button.modifyImage')"
                :visible.sync="dialogFormAvaVisible"
        >
            <el-form :model="formAva" ref="formAva" class="demo-form-inline">
                <el-form-item :label="$t('column.userImage')"  :label-width="formLabelWidth">
                    <el-input v-model="formAva.avatar" autocomplete="off" :placeholder="$t('placeholder.URL')"  disabled></el-input>
                    <user-ava @onUpload="uploadImg" @removeUpload="removeImg" ref="UserAva"></user-ava>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitAvaForm()">{{$t('button.modify')}}</el-button>
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
    import crypto from 'crypto'

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
                    callback(new Error(this.$t('message.doubleCheck')));
                } else if (value !== this.formPas.newPas) {
                    callback(new Error(this.$t('message.inconsistent')));
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
                        { required: true, message: this.$t('placeholder.account'), trigger: 'blur' }
                    ],
                    email: [
                        { required: true, message: this.$t('placeholder.email'), trigger: 'blur' }
                    ],
                    phoneNumber: [
                        { required: true, message: this.$t('placeholder.phone'), trigger: 'blur' }
                    ]
                },

                formPas: {
                    originPas: '',
                    newPas: '',
                    new2Pas: ''
                },
                rules2: {
                    originPas: [
                        { required: true, message: this.$t('message.oldPassword'), trigger: 'blur' }
                    ],
                    newPas: [
                        { required: true, message: this.$t('message.newPassword'), trigger: 'blur' }
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
                    {name: '/index', navItem: this.$t('menu.home')},
                    {name: '/history', navItem: this.$t('button.allAppointments')},
                    {name: '/message', navItem: this.$t('button.customerMessages')},
                    {name: '/timetable', navItem: this.$t('button.schedule')},
                    //  {name: '/appointment', navItem: 'Process appointment form'}
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
                // localStorage.removeItem('user')
                // // localStorage.removeItem('login')
                // alert(this.$t('message.exit'))
                // this.$router.replace('/login')
                this.$confirm(this.$t('message.logOut'), this.$t('menu.hint'), {
                    confirmButtonText: this.$t('button.confirm'),
                    cancelButtonText: this.$t('button.cancel'),
                    type: 'warning'
                }).then(() => {
                    this.$message({
                        type: 'success',
                        message: this.$t('message.successLogout')
                    });
                    localStorage.removeItem('user')
                    this.$router.replace('/login')
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: this.$t('message.failingLogout')
                    });
                });
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
                                    message: this.$t('message.successModify'),
                                    type: 'success'
                                });
                                console.log(JSON.stringify(resp))
                                this.reload();
                            }else{
                                this.$notify.error({
                                    title: this.$t('message.failed'),
                                    message: this.$t('message.failingModify')
                                });
                            }
                        })

                    } else {
                        // console.log('error submit!!');
                        this.$notify.error({
                            title: this.$t('message.failed'),
                            message: this.$t('message.failingModify')
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
                        var md5 = crypto.createHash("md5")
                        md5.update(this.formPas.originPas)
                        this.pw1 = md5.digest('hex')

                        var md52 = crypto.createHash("md5")
                        md52.update(this.formPas.newPas)
                        this.pw2 = md52.digest('hex')

                        if (this.originPass == this.pw1){
                            var username = store.state.user.username
                            const _this = this
                            var url = 'http://localhost:8181/api/changePas/' + username
                            this.$http
                                .post(url, {
                                    password: this.pw2
                                }).then(resp => {
                                if (resp && resp.status === 200) {
                                    this.dialogFormVisible = false
                                    this.$emit('onSubmit')
                                    this.$notify({
                                        title: this.$t('message.successed'),
                                        message: this.$t('message.reLogin'),
                                        type: 'success'
                                    });
                                    setTimeout(function () {
                                        _this.loginOut()
                                    },100);
                                }else{
                                    this.$notify.error({
                                        title: this.$t('message.failed'),
                                        message: this.$t('message.failingModify')
                                    });
                                }
                            })
                        }else{
                            this.$notify.error({
                                title: this.$t('message.failed'),
                                message:this.$t('message.oldPasswordWrong')
                            });
                        }


                    } else {
                        // console.log('error submit!!');
                        this.$notify.error({
                            title: this.$t('message.failed'),
                            message: this.$t('message.failingModify')
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
                                    message:this.$t('message.successUpload'),
                                    type: 'success'
                                });
                                console.log(JSON.stringify(resp))
                                this.reload();
                            }else{
                                this.$notify.error({
                                    title: this.$t('message.failed'),
                                    message: this.$t('message.failingUpload')
                                });
                            }
                        })

                    } else {
                        // console.log('error submit!!');
                        this.$notify.error({
                            title: this.$t('message.failed'),
                            message: this.$t('message.failingUpload')
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
