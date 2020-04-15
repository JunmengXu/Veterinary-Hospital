<template>
    <div>
        <div class="daily_detail">
            <h2>{{$t('menu.urgentAppointment')}}</h2>
            <div class="bar">
                <el-table
                        :data="bookingU.slice((currentPageU-1)*pagesizeU,currentPageU*pagesizeU)"
                        style="width: 100%">
                    <el-table-column type="expand">
                        <template slot-scope="props">
                            <el-form label-position="left" inline class="demo-table-expand">
                                <el-form-item :label="$t('column.phone')">
                                    <span>{{ props.row.pet.user.phoneNumber }}</span>
                                </el-form-item>
                                <el-form-item :label="$t('column.reserveTime')">
                                    <span>{{ props.row.time }}</span>
                                </el-form-item>
                                <el-form-item :label="$t('column.symptom')">
                                    <span>{{ props.row.symptom }}</span>
                                </el-form-item>
                            </el-form>
                        </template>
                    </el-table-column>
                    <el-table-column
                            :label="$t('column.appointmentNumber')"
                            prop="id">
                    </el-table-column>
                    <el-table-column
                            :label="$t('column.time')"
                            prop="needtime">
                    </el-table-column>
                    <el-table-column
                            :label="$t('column.owner')"
                            prop="pet.user.username">
                    </el-table-column>
                    <el-table-column
                            :label="$t('column.petName')"
                            prop="pet.name">
                    </el-table-column>
                    <el-table-column
                            fixed="right"
                            :label="$t('column.operation')"
                            width="100">
                        <template slot-scope="scope">
                            <el-button @click="handleClick(scope.row.id)" type="text" size="small">{{$t('button.detail')}}</el-button>
                            <el-button type="text" size="small">{{$t('button.sendMessage')}}</el-button>
                            <el-button size="small" @click="dialogFormVisible = true,bookingId=scope.row.id" v-if="scope.row.distribution==0">{{$t('button.assign')}}</el-button><el-button size="small" disabled v-else>{{$t('sideMenu.assigned')}}</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>

            <el-row>
                <div class="block">
                    <span class="demonstration"></span>
                    <el-pagination
                            @size-change="handleSizeChange"
                            @current-change="handleCurrentChange"
                            :current-page="currentPageU"
                            :page-sizes="[5, 10, 15, 20]"
                            :page-size="5"
                            layout="total, sizes, prev, pager, next, jumper"
                            :total="bookingU.length">
                    </el-pagination>
                </div>
            </el-row>

            <h2>{{$t('menu.normalAppointment')}}</h2>
            <div class="bar">
                <el-table
                        :data="booking.slice((currentPage-1)*pagesize,currentPage*pagesize)"
                        style="width: 100%">
                    <el-table-column type="expand">
                        <template slot-scope="props">
                            <el-form label-position="left" inline class="demo-table-expand">
                                <el-form-item :label="$t('column.phone')">
                                    <span>{{ props.row.pet.user.phoneNumber }}</span>
                                </el-form-item>
                                <el-form-item :label="$t('column.reserveTime')">
                                    <span>{{ props.row.time }}</span>
                                </el-form-item>
                                <el-form-item :label="$t('column.symptom')">
                                    <span>{{ props.row.symptom }}</span>
                                </el-form-item>
                            </el-form>
                        </template>
                    </el-table-column>
                    <el-table-column
                            :label="$t('column.appointmentNumber')"
                            prop="id">
                    </el-table-column>
                    <el-table-column
                            :label="$t('column.time')"
                            prop="needtime">
                    </el-table-column>
                    <el-table-column
                            :label="$t('column.owner')"
                            prop="pet.user.username">
                    </el-table-column>
                    <el-table-column
                            :label="$t('column.petName')"
                            prop="pet.name">
                    </el-table-column>
                    <el-table-column
                            fixed="right"
                            :label="$t('column.operation')"
                            width="100">
                        <template slot-scope="scope">
                            <el-button @click="handleClick(scope.row.id)" type="text" size="small">{{$t('button.detail')}}</el-button>
                            <el-button type="text" size="small">{{$t('button.sendMessage')}}</el-button>
                            <el-button size="small" @click="dialogFormVisible = true,bookingId=scope.row.id" v-if="scope.row.distribution==0">{{$t('button.assign')}}</el-button><el-button size="small" disabled v-else>{{$t('sideMenu.assigned')}}</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </div>
        <el-row>
            <div class="block">
                <span class="demonstration"></span>
                <el-pagination
                        @size-change="handleSizeChange"
                        @current-change="handleCurrentChange"
                        :current-page="currentPage"
                        :page-sizes="[5, 10, 15, 20]"
                        :page-size="5"
                        layout="total, sizes, prev, pager, next, jumper"
                        :total="booking.length">
                </el-pagination>
            </div>
        </el-row>

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

    </div>
</template>

<script>

    export default {
        name: 'BookingItem',
        inject:['reload'],
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
                currentPageU: 1, //初始页
                pagesizeU: 5,  //每页的数据
                currentPage: 1, //初始页
                pagesize: 5,  //每页的数据
                dialogFormVisible: false,
                bookingId: 0,
                distribution: 0,
                ruleForm: {
                    date: ''
                },
                rules: {
                    date: [
                        { required: true, message:this.$t('message.time') , trigger: 'change' }
                    ]
                }
            }
        },
        created() {
            const _this = this
            this.$http.get('http://localhost:8181/api/1/bookings').then(function (resp){
                console.log(resp)
                _this.bookingU = resp.data
            })
            this.$http.get('http://localhost:8181/api/0/bookings').then(function (resp){
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
            },
            submitForm() {
                this.$refs.ruleForm.validate((valid) => {
                    if (valid) {
                        var url = 'http://localhost:8181/api/update/'+this.bookingId+'/'+this.ruleForm.date+'/bookings'
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