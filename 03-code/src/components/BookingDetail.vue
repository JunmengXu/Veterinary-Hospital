<template>
    <div class="daily_detail">
        <h2>预约单详细信息</h2>
        <div class="bar">
            <el-table
                :data="tableData"
                style="width: 100%">
            <el-table-column
                    prop="name"
                    label="条目"
                    width="200px"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="contain"
                    label="信息"
                    align="center">
            </el-table-column>
        </el-table>
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
                }]
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