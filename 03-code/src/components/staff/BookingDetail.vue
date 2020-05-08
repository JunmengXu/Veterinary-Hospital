<template>
    <div class="daily_detail">
        <h2>{{$t('menu.bookingDetail')}}</h2>

        <div :class="[{'bar': (bookings.ratedis==0 )},{'bar2': (bookings.ratedis==1)}]" style="margin-left: 50px; margin-right: 50px">
            <div style="max-width: 60%">
                <p class="title">{{$t('column.customerInfo')}}  <span>{{bookings.pet.user.username}} / {{bookings.pet.user.phoneNumber}} / {{bookings.pet.user.email}} / {{bookings.pet.user.username}}</span> </p>
                <p class="title">{{$t('column.petInfo')}}  <span>{{bookings.pet.name}} / {{bookings.pet.type}} / <span v-if="bookings.pet.gender==0">male</span><span v-else>female</span></span> </p>
                <p class="title">{{$t('column.symptomInfo')}}  <span>{{bookings.symptom}}</span></p>
                <p class="title">{{$t('column.bookingMadeTime')}}  <span>{{bookings.time}}</span></p>
                <p class="title">{{$t('column.bookingNeedTime')}}  <span>{{bookings.needtime}}</span>
                    <el-switch v-if="bookings.distribution==1"
                               v-model="value1"
                               class="el-switch"
                               disabled>
                    </el-switch><span v-if="bookings.distribution==1" class="el-switchtitle">{{$t('sideMenu.assigned')}}</span>
                    <el-switch v-if="bookings.distribution==0"
                               v-model="value2"
                               class="el-switch"
                               disabled>
                    </el-switch><span v-if="bookings.distribution==0" class="el-switchtitle">Unassigned</span>
                    <el-button size="small" @click="dialogFormVisible = true" style="margin-left: 15px" v-if="bookings.distribution==1 && bookings.pet.status==2">{{$t('button.reassign')}}</el-button><el-button size="small" @click="dialogFormVisible = true" style="margin-left: 15px" v-if="bookings.distribution==0 && bookings.pet.status==1">{{$t('button.assign')}}</el-button>
                </p>
                <p class="title">{{$t('column.releaseTime')}} <span v-if="bookings.releasetime">{{bookings.releasetime}}</span><span v-else>{{$t('choices.pending')}}</span>
                    <el-button size="small" @click="dialogFormReVisible = true" style="margin-left: 15px" v-if="bookings.pet.status==2">{{$t('button.release')}}</el-button><el-button size="small" style="margin-left: 15px" v-else disabled>{{$t('button.release')}}</el-button>
                </p>
                <p class="title">{{$t('column.hospital')}}  <span v-if="bookings.hospital==0">{{$t('choices.Beijing')}}</span><span v-else-if="bookings.hospital==1">{{$t('choices.Shanghai')}}</span><span v-else>{{$t('choices.Chengdu')}}</span></p>
            </div>

            <div style="position: absolute;left: 780px;top: 200px">
                <img :src="bookings.pet.cover" v-if="bookings.pet.cover" style="max-width: 600px"> <img src="http://n.sinaimg.cn/sinacn20119/654/w1417h837/20181219/8fc7-hqnkypr2320016.jpg" alt="封面" style="max-width: 600px" v-else>
            </div>

            <div style= "padding-bottom: 20px">
                <p class="title">{{$t('column.status')}} </p>
                <el-steps :active="bookings.pet.status+1" align-center finish-status="success">
                    <el-step :title="$t('choices.status1')" :description="$t('sideMenu.noAppointment')"></el-step>
                    <el-step :title="$t('choices.status2')" :description="$t('sideMenu.waitingDistribution')"></el-step>
                    <el-step :title="$t('choices.status3')" :description="$t('sideMenu.waitingOperation')"></el-step>
                    <el-step :title="$t('choices.status4')" :description="$t('sideMenu.waitingRelease')"></el-step>
                </el-steps>
            </div>

            <div>
                <p class="title">{{$t('column.customerRate')}}<span v-if="!bookings.ratevalue && !bookings.ratecontent">{{$t('choices.noRate')}}</span>:  <span class="demonstration">{{$t('column.star')}} 1-5: {{bookings.ratevalue}}</span> <span>| {{$t('column.comment')}} {{bookings.ratecontent}}</span></p>
                <div class="block" style="margin-left: 10%;padding-bottom: 20px">
                    <el-rate
                            v-model="bookings.ratevalue"
                            :colors="colors"
                            disabled
                            show-score>
                    </el-rate>
                </div>
            </div>

            <el-dialog
                    :title="$t('menu.setOperationTime')"
                    :visible.sync="dialogFormVisible">
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

                    <el-form-item :label="$t('column.date')" required>
                        <el-col :span="11">
                            <el-form-item prop="date">
                                <el-date-picker type="date" :placeholder="$t('placeholder.time')" v-model="ruleForm.date" value-format="yyyy-MM-dd" style="width: 100%;"></el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-form-item>

                    <el-form-item>
                        <el-button type="primary" @click="submitForm()">{{$t('button.assign')}}</el-button>
                        <el-button @click="resetForm()">{{$t('button.reset')}}</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>

            <el-dialog
                    :title="$t('button.release')"
                    :visible.sync="dialogFormReVisible">
                <el-form :model="ruleForm2" :rules="rules2" ref="ruleForm2" label-width="100px" class="demo-ruleForm">

                    <el-form-item :label="$t('column.date')" required>
                        <el-col :span="11">
                            <el-form-item prop="date">
                                <el-date-picker type="date" :placeholder="$t('placeholder.time')" v-model="ruleForm2.date" value-format="yyyy-MM-dd" style="width: 100%;"></el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-form-item>

                    <el-form-item>
                        <el-button type="primary" @click="submitForm2()">{{$t('button.assign')}}</el-button>
                        <el-button @click="resetForm2()">{{$t('button.reset')}}</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>

        </div>
    </div>
</template>

<script>
    export default {
        name: "BookingDetail",
        inject:['reload'],
        data() {
            return {
                tableData: [{
                    name: '客户姓名',
                    contain: '张三；123456789@qq.com；北京市东城区王府井大街1号；13812341234'
                },{
                    name: '宠物信息',
                    contain: '张二狗；3岁；柴犬；雄；13kg'
                },{
                    name: '病症描述',
                    contain: '晕车，影响我开法拉利'
                },{
                    name: '下单时间',
                    contain: '2020年3月10日'
                },{
                    name: '预约时间',
                    contain: '3月13日 下午； 3月14日 上午+下午'
                }],
                bookings: [],
                bookingid: [],
                value1: true,
                value2: false,
                colors: ['#99A9BF', '#F7BA2A', '#FF9900'],  // 等同于 { 2: '#99A9BF', 4: { value: '#F7BA2A', excluded: true }, 5: '#FF9900' }
                dialogFormVisible: false,
                dialogFormReVisible: false,
                ruleForm: {
                    date: ''
                },
                rules: {
                    date: [
                        { required: true, message:this.$t('message.time') , trigger: 'change' }
                    ]
                },
                ruleForm2: {
                    date: ''
                },
                rules2: {
                    date: [
                        { required: true, message:this.$t('message.time') , trigger: 'change' }
                    ]
                }
            }

        },
        //向后端发送请求，获得customer的数据
        // created() {
        //     const _this = this
        //     this.$http.get('http://localhost:8181/customer/findAll').then(function (resp){
        //         console.log(resp)
        //         _this.customer = resp.data
        //         // alert(_this.customer[1].id + ' ' + _this.customer[1].name + ' ' + _this.customer[1].pet)
        //     })
        // },
        created:function(){
            this.getParams();
            const _this = this
            var cid = this.bookingid
            var url = 'http://localhost:8181/api/' + cid + '/oneBooking'
            this.$http.get(url).then(function (resp){
                console.log(resp)
                _this.bookings = resp.data
            })
        },
        watch: {
            // 监测路由变化,只要变化了就调用获取路由参数方法将数据存储本组件即可
            '$route': 'getParams'
        },
        methods: {
            getParams: function () {
                // 取到路由带过来的参数
                var routerParams = this.$route.query.booking
                // 将数据放在当前组件的数据内
                console.log("传来的参数==" + routerParams)
                this.bookingid = routerParams
            },
            submitForm() {
                this.$refs.ruleForm.validate((valid) => {
                    if (valid) {
                        var url = 'http://localhost:8181/api/update/'+this.bookingid+'/'+this.ruleForm.date+'/bookings'
                        this.$http
                            .put(url, {

                            }).then(resp => {
                            if (resp && resp.status === 200) {
                                this.dialogFormVisible = false
                                this.$emit('onSubmit')
                                this.$notify({
                                    title: this.$t('message.successed'),
                                    message: this.$t('message.successAssign'),
                                    type: 'success'
                                });
                                console.log(JSON.stringify(resp))
                                this.reload();
                            }else{
                                this.$notify.error({
                                    title: this.$t('message.failed'),
                                    message: this.$t('message.failingAssign')
                                });
                            }
                        })
                    } else {
                        // console.log('error submit!!');
                        this.$notify.error({
                            title: this.$t('message.failed'),
                            message: this.$t('message.failingAssign')
                        });
                        return false;
                    }
                });
            },
            resetForm() {
                this.$refs.ruleForm.resetFields();
            },
            submitForm2() {
                this.$refs.ruleForm2.validate((valid) => {
                    if (valid) {
                        var url = 'http://localhost:8181/api/updateRe/'+this.bookingid+'/'+this.ruleForm2.date+'/bookings'
                        this.$http
                            .put(url, {

                            }).then(resp => {
                            if (resp && resp.status === 200) {
                                this.dialogFormReVisible = false
                                this.$emit('onSubmit')
                                this.$notify({
                                    title: this.$t('message.successed'),
                                    message: this.$t('message.successAssign'),
                                    type: 'success'
                                });
                                console.log(JSON.stringify(resp))
                                this.reload();
                            }else{
                                this.$notify.error({
                                    title: this.$t('message.failed'),
                                    message: this.$t('message.failingAssign')
                                });
                            }
                        })
                    } else {
                        // console.log('error submit!!');
                        this.$notify.error({
                            title: this.$t('message.failed'),
                            message: this.$t('message.failingAssign')
                        });
                        return false;
                    }
                });
            },
            resetForm2() {
                this.$refs.ruleForm2.resetFields();
            }

        }
    }
</script>

<style scoped>
    .bar{
        margin-top: 20px;
        margin-bottom: 20px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
    }
    .bar2{
        margin-top: 20px;
        margin-bottom: 20px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
        background-image: url("../../assets/image/completed.jpg")
    }
    h2{
        text-align: center;
    }
    .title{
        font-weight: bold;
        padding-top: 20px;
        padding-bottom: 20px;
        padding-left: 15px;
    }

    .el-switch{
        padding-left: 25px;
    }

    .el-switchtitle{
        padding-left: 5px;
    }

    .title span{
        font-weight: normal;
    }
</style>