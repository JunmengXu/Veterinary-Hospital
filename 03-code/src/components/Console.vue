<template>
    <div class="console">
        <el-table
                    :data="tableData"
                    style="width: 100%">
                <el-table-column type="expand">
                    <template slot-scope="props">
                        <el-form label-position="left" inline class="demo-table-expand">
                            <el-form-item label="联系电话">
                                <span>{{ props.row.phone }}</span>
                            </el-form-item>
                            <el-form-item label="下单时间">
                                <span>{{ props.row.make_time }}</span>
                            </el-form-item>
                            <el-form-item label="病症描述">
                                <span>{{ props.row.description }}</span>
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
                        prop="need_time">
                </el-table-column>
                <el-table-column
                        label="客户姓名"
                        prop="name">
                </el-table-column>
                <el-table-column
                        label="宠物名字"
                        prop="pet">
                </el-table-column>
                <el-table-column
                        fixed="right"
                        label="操作"
                        width="170">
                    <template slot-scope="scope">
                        <el-button @click="handleClick(scope.row)" type="text" size="small">详情</el-button>
                        <el-button type="text" size="small">私信</el-button>
                        <el-button size="small" >分配</el-button>
                    </template>
                </el-table-column>
            </el-table>
    </div>
</template>

<script>
    export default {
        name: "Console",
        methods: {
            handleClick(row) {
                console.log(row);
            }
        },
        data() {
            return {
                tableData: [{
                    id: '12987122',
                    need_time: '3月13日 下午； 3月14日 上午+下午',
                    name: '张三',
                    pet: '张二狗',
                    make_time: '3月10日 13:00',
                    phone: '13912341234',
                    description: '晕车'
                },{
                    id: '12987122',
                    need_time: '3月13日 下午； 3月14日 上午+下午',
                    name: '张三',
                    pet: '张二狗',
                    make_time: '3月10日 13:00',
                    phone: '13912341234',
                    description: '晕车'
                },{
                    id: '12987122',
                    need_time: '3月13日 下午； 3月14日 上午+下午',
                    name: '张三',
                    pet: '张二狗',
                    make_time: '3月10日 13:00',
                    phone: '13912341234',
                    description: '晕车'
                },{
                    id: '12987122',
                    need_time: '3月13日 下午； 3月14日 上午+下午',
                    name: '张三',
                    pet: '张二狗',
                    make_time: '3月10日 13:00',
                    phone: '13912341234',
                    description: '晕车'
                },{
                    id: '12987122',
                    need_time: '3月13日 下午； 3月14日 上午+下午',
                    name: '张三',
                    pet: '张二狗',
                    make_time: '3月10日 13:00',
                    phone: '13912341234',
                    description: '晕车'
                },{
                    id: '12987122',
                    need_time: '3月13日 下午； 3月14日 上午+下午',
                    name: '张三',
                    pet: '张二狗',
                    make_time: '3月10日 13:00',
                    phone: '13912341234',
                    description: '晕车'
                },{
                    id: '12987122',
                    need_time: '3月13日 下午； 3月14日 上午+下午',
                    name: '张三',
                    pet: '张二狗',
                    make_time: '3月10日 13:00',
                    phone: '13912341234',
                    description: '晕车'
                },{
                    id: '12987122',
                    need_time: '3月13日 下午； 3月14日 上午+下午',
                    name: '张三',
                    pet: '张二狗',
                    make_time: '3月10日 13:00',
                    phone: '13912341234',
                    description: '晕车'
                },{
                    id: '12987122',
                    need_time: '3月13日 下午； 3月14日 上午+下午',
                    name: '张三',
                    pet: '张二狗',
                    make_time: '3月10日 13:00',
                    phone: '13912341234',
                    description: '晕车'
                }]
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
    .console{
        box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
    }
</style>