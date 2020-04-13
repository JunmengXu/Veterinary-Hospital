<template>
    <el-table
            :data="bookings"
            style="width: 100%"
            :row-class-name="tableRowClassName">
        <el-table-column
                prop="urgency"
                label="紧急度"
                width="180">
            <template slot-scope="props">
                <span v-if="props.row.urgency==0" style="color: darkgreen">Normal</span><span v-else style="color: crimson">Urgent</span>
            </template>
        </el-table-column>
        <el-table-column
                prop="hospital"
                label="医院"
                width="180">
            <template slot-scope="props">
                <span v-if="props.row.hospital==0">北京</span><span v-else-if="props.row.hospital==1">上海</span><span v-else>成都</span>
            </template>
        </el-table-column>
        <el-table-column
                prop="pet.name"
                label="宠物">
        </el-table-column>
        <el-table-column
                prop="symptom"
                label="症状">
        </el-table-column>
        <el-table-column
                prop="needtime"
                label="需要时间">
        </el-table-column>
        <el-table-column
                prop="distribution"
                label="分配情况"
                width="180">
            <template slot-scope="props">
                <span v-if="props.row.distribution==0" style="color: chocolate">未分配</span><span v-else style="color: cornflowerblue">已分配</span>
            </template>
        </el-table-column>
    </el-table>
</template>

<script>
    import store from "../../../store";

    export default {
        name: 'BookingItemC',
        methods: {
            //rowIndex
            tableRowClassName({row}) {
                if (row.distribution === 0) {
                    return 'warning-row';
                } else if (row.distribution === 1) {
                    return 'nothing';
                }
                return '';
            }
        },
        data() {
            return {
                bookings: []
            }
        },
        created() {
            const _this = this
            var username = store.state.user.username
            var url = 'http://localhost:8181/api/customerBookings/' + username
            this.$http.get(url).then(function (resp) {
                console.log(resp)
                _this.bookings = resp.data
            })
        }
    }
</script>

<style>
    .el-table .warning-row {
        background: oldlace;
    }

    .el-table nothing{
        background: white;
    }

    .el-table .success-row {
        background: #f0f9eb;
    }
</style>