<template>
    <div>
        <div class="daily_detail">
            <h2>紧急预约</h2>
            <div class="bar">
                <el-table
                        :data="bookingU.slice((currentPage-1)*pagesize,currentPage*pagesize)"
                        style="width: 100%">
                    <el-table-column type="expand">
                        <template slot-scope="props">
                            <el-form label-position="left" inline class="demo-table-expand">
                                <el-form-item label="联系电话">
                                    <span>{{ props.row.pet.user.phoneNumber }}</span>
                                </el-form-item>
                                <el-form-item label="下单时间">
                                    <span>{{ props.row.time }}</span>
                                </el-form-item>
                                <el-form-item label="病症描述">
                                    <span>{{ props.row.symptom }}</span>
                                </el-form-item>
                            </el-form>
                        </template>
                    </el-table-column>
                    <el-table-column
                            label="编号"
                            prop="id">
                    </el-table-column>
                    <el-table-column
                            label="需要时间"
                            prop="needtime">
                    </el-table-column>
                    <el-table-column
                            label="客户姓名"
                            prop="pet.user.username">
                    </el-table-column>
                    <el-table-column
                            label="宠物名字"
                            prop="pet.name">
                    </el-table-column>
                    <el-table-column
                            fixed="right"
                            label="操作"
                            width="100">
                        <template slot-scope="scope">
                            <el-button @click="handleClick(scope.row.id)" type="text" size="small">详情</el-button>
                            <el-button type="text" size="small">私信</el-button>
                            <el-button size="small" >分配</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
            <h2>非紧急预约</h2>
            <div class="bar">
                <el-table
                        :data="booking"
                        style="width: 100%">
                    <el-table-column type="expand">
                        <template slot-scope="props">
                            <el-form label-position="left" inline class="demo-table-expand">
                                <el-form-item label="联系电话">
                                    <span>{{ props.row.pet.user.phoneNumber }}</span>
                                </el-form-item>
                                <el-form-item label="下单时间">
                                    <span>{{ props.row.time }}</span>
                                </el-form-item>
                                <el-form-item label="病症描述">
                                    <span>{{ props.row.symptom }}</span>
                                </el-form-item>
                            </el-form>
                        </template>
                    </el-table-column>
                    <el-table-column
                            label="编号"
                            prop="id">
                    </el-table-column>
                    <el-table-column
                            label="需要时间"
                            prop="needtime">
                    </el-table-column>
                    <el-table-column
                            label="客户姓名"
                            prop="pet.user.username">
                    </el-table-column>
                    <el-table-column
                            label="宠物名字"
                            prop="pet.name">
                    </el-table-column>
                    <el-table-column
                            fixed="right"
                            label="操作"
                            width="100">
                        <template slot-scope="scope">
                            <el-button @click="handleClick(scope.row.id)" type="text" size="small">详情</el-button>
                            <el-button type="text" size="small">私信</el-button>
                            <el-button size="small" >分配</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </div>
        <el-row>
            <div class="block">
                <span class="demonstration">完整功能</span>
                <el-pagination
                        @size-change="handleSizeChange"
                        @current-change="handleCurrentChange"
                        :current-page="currentPage"
                        :page-sizes="[5, 10, 15, 20]"
                        :page-size="5"
                        layout="total, sizes, prev, pager, next, jumper"
                        :total="bookingU.length">
                </el-pagination>
            </div>
        </el-row>
    </div>
</template>

<script>

    export default {
        name: 'BookingItem',
        data () {
            return {
                tableData: [{
                    id: '12987122',
                    need_time: '3月13日 下午； 3月14日 上午+下午',
                    name: '张三',
                    pet: '张二狗',
                    make_time: '3月10日 13:00',
                    phone: '13912341234',
                    description: '晕车'
                }],
                tableData2: [{
                    id: '12987122',
                    need_time: '3月13日 下午； 3月14日 上午+下午',
                    name: '李四',
                    pet: '李二狗',
                    make_time: '3月10日 13:00',
                    phone: '13912341234',
                    description: '晕车'
                },{
                    id: '12987122',
                    need_time: '3月13日 下午； 3月14日 上午+下午',
                    name: '李四',
                    pet: '李二狗',
                    make_time: '3月10日 13:00',
                    phone: '13912341234',
                    description: '晕车'
                }],
                bookingU: [],
                booking: [],
                currentPage: 1, //初始页
                pagesize: 5  //每页的数据
            }
        },
        created() {
            const _this = this
            this.$http.get('http://localhost:8181/api/0/bookings').then(function (resp){
                console.log(resp)
                _this.bookingU = resp.data
            })
            this.$http.get('http://localhost:8181/api/1/bookings').then(function (resp){
                console.log(resp)
                _this.booking = resp.data
            })
        },
        methods: {
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
                this.pagesize = val;
            },
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
                this.currentPage = val;
            },
            handleClick(id){
                this.$router.push({
                    path:'/appointment',
                    query:{
                        booking:id},
                })
            }
        }
    }
</script>

<style scoped>
    .demo-table-expand {
        font-size: 0;
    }
    .demo-table-expand label {
        width: 90px;
        color: #99a9bf;
    }
    .demo-table-expand {
        margin-right: 0;
        margin-bottom: 0;
        width: 100%;
    }
    .bar{
        margin-right: 20px;
        margin-top: 20px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
    }
    h2{
        text-align: center;
    }

    img {
        width: 115px;
        height: 172px;
        /*margin: 0 auto;*/
    }

    a {
        text-decoration: none;
    }

    a:link, a:visited, a:focus {
        color: #3377aa;
    }
</style>