<template>
    <div class="daily_detail">
        <h2>{{$t('menu.bookingDetail')}}</h2>
        <!--        <div class="bar">-->
        <!--            <el-table-->
        <!--                :data="tableData"-->
        <!--                style="width: 100%">-->
        <!--            <el-table-column-->
        <!--                    prop="name"-->
        <!--                    label="条目"-->
        <!--                    width="200px"-->
        <!--                    align="center">-->
        <!--            </el-table-column>-->
        <!--            <el-table-column-->
        <!--                    prop="contain"-->
        <!--                    label="信息"-->
        <!--                    align="center">-->
        <!--            </el-table-column>-->
        <!--        </el-table>-->
        <!--        </div>-->
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
                    </el-switch><span v-if="bookings.distribution==0" class="el-switchtitle">{{$t('sideMenu.unassigned')}}</span>
                </p>
                <p class="title">{{$t('column.releaseTime')}} <span v-if="bookings.releasetime">{{bookings.releasetime}}</span><span v-else>{{$t('choices.pending')}}</span></p>
                <p class="title">{{$t('column.hospital')}} <span v-if="bookings.hospital==0">{{$t('choices.Beijing')}}</span><span v-else-if="bookings.hospital==1">{{$t('choices.Shanghai')}}</span><span v-else>{{$t('choices.Chengdu')}}</span></p>
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

            <el-button style="margin-left: 50%;margin-bottom: 20px" @click="writeRate" v-if="bookings.ratedis==0 && bookings.pet.status==3">{{$t('button.rate')}}</el-button><el-button style="margin-left: 50%;margin-bottom: 20px" v-else disabled>{{$t('button.rate')}}</el-button>
            <span v-if="bookings.ratedis==1" style="margin-left: 10px">{{$t('choices.rated')}}</span>
            <el-dialog
                    :title="$t('menu.rate')"
                    :visible.sync="dialogFormInfVisible">
                <el-form :model="formInf" :rules="rules" ref="formInf" class="demo-form-inline" @submit.native.prevent>
                    <el-form-item :label="$t('column.star')" prop="ratevalue">
                        <div class="block">
                            <span class="demonstration">1-5</span>
                            <el-rate
                                    v-model="formInf.ratevalue"
                                    :colors="colors"
                                    @change="changerate">
                            </el-rate>
                        </div>
                    </el-form-item>

                    <el-form-item :label="$t('column.comment')" prop="ratecontent">
                        <el-input v-model="formInf.ratecontent" :placeholder="$t('column.comment')"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="submitInfForm()">{{$t('button.add')}}</el-button>
                        <el-button @click="resetInfForm()">{{$t('button.reset')}}</el-button>
                    </el-form-item>
                    <p style="color: crimson">{{$t('message.statusChange')}}</p>
                </el-form>

                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormInfVisible = false">{{$t('button.cancel')}}</el-button>
                </div>
            </el-dialog>

        </div>
    </div>
</template>

<script>

    export default {
        name: "BookingDetailC",
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
                dialogFormInfVisible: false,
                formInf: {
                    ratevalue: null,
                    ratecontent: ''
                },
                rules: {
                    ratevalue: [
                        { required: true, message: "请评价等级", trigger: 'blur' }
                    ],
                    ratecontent: [
                        { required: true, message: "请填写评价", trigger: 'blur' }
                    ]
                },
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
            changerate(){
                console.log(this.formInf.ratevalue)
            },
            writeRate(){
                this.dialogFormInfVisible = true
            },
            resetInfForm() {
                this.$refs.formInf.resetFields();
            },
            submitInfForm() {
                this.$refs.formInf.validate((valid) => {
                    if (valid) {
                        // alert('submit!');
                        // console.log(this.formInline.petName+" "+this.formInline.petType+" "+this.formInline.petGender)
                        var url = 'http://localhost:8181/api/updateRate/' + this.bookingid + '/' + this.formInf.ratevalue + '/' + this.formInf.ratecontent + '/bookings'
                        this.$http
                            .put(url, {

                            }).then(resp => {
                            if (resp && resp.status === 200) {
                                this.dialogFormVisible = false
                                this.$emit('onSubmit')
                                this.$notify({
                                    title: this.$t('message.successed'),
                                    message: this.$t('message.successRate'),
                                    type: 'success'
                                });
                                console.log(JSON.stringify(resp))
                                this.reload();
                            }else{
                                this.$notify.error({
                                    title: this.$t('message.failed'),
                                    message: this.$t('message.failingRate')
                                });
                            }
                        })

                    } else {
                        // console.log('error submit!!');
                        this.$notify.error({
                            title: this.$t('message.failed'),
                            message: this.$t('message.failingRate')
                        });
                        return false;
                    }
                });
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
        background-image: url("../../../assets/image/completed.jpg")
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