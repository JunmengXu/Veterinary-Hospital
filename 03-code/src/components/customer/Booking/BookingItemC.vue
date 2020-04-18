<template>
    <el-table
            :data="bookings"
            style="width: 100%"
            :row-class-name="tableRowClassName">
        <el-table-column
                prop="urgency"
                :label="$t('column.urgency')"
                width="120">
            <template slot-scope="props">
                <span v-if="props.row.urgency==0" style="color: darkgreen">{{$t('choices.normal')}}</span><span v-else style="color: crimson">{{$t('choices.urgent')}}</span>
            </template>
        </el-table-column>
        <el-table-column
                prop="hospital"
                :label="$t('column.hospital')"
                width="120">
            <template slot-scope="props">
                <span v-if="props.row.hospital==0">{{$t('choices.Beijing')}}</span><span v-else-if="props.row.hospital==1">{{$t('choices.Shanghai')}}</span><span v-else>{{$t('choices.Chengdu')}}</span>
            </template>
        </el-table-column>
        <el-table-column
                prop="pet.name"
                :label="$t('column.pet')">
            <template slot-scope="props">
                <span>{{props.row.pet.name}}</span>
                <span> (</span>
                <span v-if="props.row.pet.status==1">{{$t('sideMenu.waitingDistribution')}}</span><span v-else-if="props.row.pet.status==2">{{$t('sideMenu.waitingOperation')}}</span><span v-else>{{$t('sideMenu.waitingRelease')}}</span>
                <span>)</span>
            </template>
        </el-table-column>
        <el-table-column
                prop="symptom"
                :label="$t('column.symptom')">
        </el-table-column>
        <el-table-column
                prop="needtime"
                :label="$t('column.time')"
                width="140">
        </el-table-column>
        <el-table-column
                prop="distribution"
                :label="$t('column.distribution')"
                width="140">
            <template slot-scope="props">
                <span v-if="props.row.distribution==0" style="color: chocolate">{{$t('sideMenu.unassigned')}}</span><span v-else style="color: cornflowerblue">{{$t('sideMenu.assigned')}}</span>
            </template>
        </el-table-column>
        <el-table-column
                fixed="right"
                :label="$t('column.operation')"
                width="100">
            <template slot-scope="props">
                <el-button @click="handleClicks(props.row.id)" type="text" size="small">{{$t('button.detail')}}</el-button>
                <el-button size="small" v-if="props.row.distribution==0" @click="deleteBooking(props.row.id)">删除</el-button><el-button size="small" disabled v-else>删除</el-button>
            </template>
        </el-table-column>
    </el-table>
</template>

<script>
    import store from "../../../store";

    export default {
        name: 'BookingItemC',
        inject:['reload'],
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
        },
        methods: {
            //rowIndex
            tableRowClassName({row}) {
                if (row.distribution === 0) {
                    return 'warning-row';
                } else if (row.distribution === 1) {
                    return 'nothing';
                }
                return '';
            },
            handleClicks (id){
                // this.$router.push({
                //     path:'/appointment',
                //     query:{
                //         booking:id},
                // })
                console.log(id)
            },
            deleteBooking (id){
                this.$confirm('此操作将永久删除该预约, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                        this.$http
                            .post('http://localhost:8181/api/bookingDelete', {id: id}).then(resp => {
                            if (resp && resp.status === 200) {
                                console.log(JSON.stringify(resp))
                                this.reload();
                            }
                        })
                    }
                ).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    })
                })
            }
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