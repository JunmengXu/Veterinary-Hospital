<template>
    <div class="daily_detail">
        <h2>预约单详细信息</h2>
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
        <div class="bar" style="margin-left: 50px; margin-right: 50px">
            <p>客户信息  <span>{{bookings.pet.user.username}} / {{bookings.pet.user.phoneNumber}} / {{bookings.pet.user.email}} / {{bookings.pet.user.username}}</span> </p>
            <p>宠物信息 <span>{{bookings.pet.name}} / {{bookings.pet.type}} / <span v-if="bookings.pet.gender==0">male</span><span v-else>female</span></span> </p>
            <p>病症描述 <span>{{bookings.symptom}}</span></p>
            <p>下单时间 <span>{{bookings.time}}</span></p>
            <p>预约时间 <span>{{bookings.needtime}}</span></p>
        </div>
    </div>
</template>

<script>
    export default {
        name: "BookingDetail",
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
                bookingid: []
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
            }
        }
    }
</script>

<style scoped>
    .bar{
        margin-top: 20px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
    }
    h2{
        text-align: center;
    }
</style>