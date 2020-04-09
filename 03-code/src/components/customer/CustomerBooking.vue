<template>
    <el-container>
        <el-aside>
            <switch></switch>
            <SideMenuBookingC></SideMenuBookingC>
        </el-aside>
        <el-main>
            <BookingItemC class="booking-area"></BookingItemC>
        </el-main>
    </el-container>
</template>

<script>
    import SideMenuBookingC from "./Booking/SideMenuBookingC";
    import BookingItemC from "./Booking/BookingItemC";

    export default {
        name: "CustomerBooking",
        components: {SideMenuBookingC, BookingItemC},
        methods: {
            handleClick(row) {
                console.log(row);
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