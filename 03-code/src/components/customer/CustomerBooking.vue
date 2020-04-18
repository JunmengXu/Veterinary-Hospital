<template>
    <el-container>
        <el-aside>
            <switch></switch>
            <SideMenuBookingC @indexSelect="listByBookingStatus" ref="sideMenu"></SideMenuBookingC>
        </el-aside>
        <el-main>
            <BookingItemC class="booking-area" ref="bookingsArea"></BookingItemC>
        </el-main>
    </el-container>
</template>

<script>
    import SideMenuBookingC from "./Booking/SideMenuBookingC";
    import BookingItemC from "./Booking/BookingItemC";
    import store from "../../store";

    export default {
        name: "CustomerBooking",
        components: {SideMenuBookingC, BookingItemC},
        methods: {
            handleClick(row) {
                console.log(row);
            },
            listByBookingStatus () {
                var username = store.state.user.username
                if(this.$refs.sideMenu.select==0){
                    const _this = this
                    var url = 'http://localhost:8181/api/customerBookings/' + username
                    this.$http.get(url).then(function (resp) {
                        console.log(resp)
                        _this.$refs.bookingsArea.bookings = resp.data
                    })
                }else{
                    const _this = this
                    var t = this.$refs.sideMenu.select-1
                    var url1 = 'http://localhost:8181/api/customerBookings/' + username + '/' + t
                    this.$http.get(url1).then(function (resp){
                        console.log(resp)
                        _this.$refs.bookingsArea.bookings = resp.data
                    })
                }
            }
        }
    }
</script>

<style scoped>
    .demo-table-expand label {
        width: 90px;
        color: #99a9bf;
    }
    h2{
        text-align: center;
    }
    .booking-area{
        width: 1180px;
        margin-left: auto;
        margin-right: auto;
    }
</style>