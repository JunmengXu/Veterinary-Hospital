<template>
    <el-table
            :data="bookings"
            style="width: 100%"
            :row-class-name="tableRowClassName">
        <el-table-column
                prop="urgency"
                :label="$t('column.urgency')"
                width="180">
            <template slot-scope="props">
                <span v-if="props.row.urgency==0" style="color: darkgreen">{{$t('choices.normal')}}</span><span v-else style="color: crimson">{{$t('choices.urgent')}}</span>
            </template>
        </el-table-column>
        <el-table-column
                prop="hospital"
                :label="$t('column.hospital')"
                width="180">
            <template slot-scope="props">
                <span v-if="props.row.hospital==0">{{$t('choices.Beijing')}}</span><span v-else-if="props.row.hospital==1">{{$t('choices.Shanghai')}}</span><span v-else>{{$t('choices.Chengdu')}}</span>
            </template>
        </el-table-column>
        <el-table-column
                prop="pet.name"
                :label="$t('column.pet')">
        </el-table-column>
        <el-table-column
                prop="symptom"
                :label="$t('column.symptom')">
        </el-table-column>
        <el-table-column
                prop="needtime"
                :label="$t('column.time')">
        </el-table-column>
        <el-table-column
                prop="distribution"
                :label="$t('column.distribution')"
                width="180">
            <template slot-scope="props">
                <span v-if="props.row.distribution==0" style="color: chocolate">{{$t('sideMenu.unassigned')}}</span><span v-else style="color: cornflowerblue">{{$t('sideMenu.assigned')}}</span>
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