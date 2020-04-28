<template>
    <el-container>
        <el-aside>
            <switch></switch>
            <SideMenuBooking @indexSelect="listByBookingCity" ref="sideMenu"></SideMenuBooking>
        </el-aside>
        <el-main>
            <BookingItem class="booking-area" ref="bookingsArea"></BookingItem>
        </el-main>
    </el-container>

</template>

<script>
    import SideMenuBooking from "./Booking/SideMenuBooking";
    import BookingItem from "./Booking/BookingItem";

    export default {
        name: "DailyDetail",
        components: {SideMenuBooking, BookingItem},
        methods: {
            handleClick(row) {
                console.log(row);
            },
            listByBookingCity() {
                if (this.$refs.sideMenu.select == 0) {
                    const _this = this
                    var url = 'http://localhost:8181/api/1/bookings'
                    this.$http.get(url).then(function (resp) {
                        console.log(resp)
                        _this.$refs.bookingsArea.bookingU = resp.data
                    })
                    var url2 = 'http://localhost:8181/api/0/bookings'
                    this.$http.get(url2).then(function (resp) {
                        console.log(resp)
                        _this.$refs.bookingsArea.booking = resp.data
                    })
                } else {
                    const _this = this
                    var t = this.$refs.sideMenu.select - 1
                    var url3 = 'http://localhost:8181/api/0/' + t + '/bookings'
                    this.$http.get(url3).then(function (resp) {
                        console.log(resp)
                        _this.$refs.bookingsArea.booking = resp.data
                    })
                    var url4 = 'http://localhost:8181/api/1/' + t + '/bookings'
                    this.$http.get(url4).then(function (resp) {
                        console.log(resp)
                        _this.$refs.bookingsArea.bookingU = resp.data
                    })
                }
            }
        }

    }
</script>

<style scoped>
    /*.demo-table-expand {*/
    /*    font-size: 0;*/
    /*}*/
    .demo-table-expand label {
        width: 90px;
        color: #99a9bf;
    }
    /*.demo-table-expand {*/
    /*    margin-right: 0;*/
    /*    margin-bottom: 0;*/
    /*    width: 100%;*/
    /*}*/
    /*.bar{*/
    /*    margin-right: 20px;*/
    /*    margin-top: 20px;*/
    /*    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)*/
    /*}*/
    h2{
        text-align: center;
    }
    .booking-area{
        width: 1180px;
        margin-left: auto;
        margin-right: auto;
    }
</style>