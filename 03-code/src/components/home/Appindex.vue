<template>
  <div>
<!--    <Header/>-->
    <Homepage/>
    <Timetable/>
    <Console/>
    <DailyDetail/>
    <BookingDetail/>
  </div>
</template>

<script>

  // import Header from '@/components/Header.vue'
  import BookingDetail from "@/components/staff/BookingDetail";
  import DailyDetail from "@/components/staff/DailyDetail";
  import Homepage from "@/components/staff/Homepage";
  import Console from "@/components/staff/Console";
  import Timetable from "@/components/staff/Timetable";

  //这里导入了主界面里的所有版块，都在此页面显示
export default {
  name: 'AppIndex',
  components: {
    BookingDetail: BookingDetail,
    DailyDetail: DailyDetail,
    Timetable: Timetable,
    Console: Console,
    Homepage: Homepage,
    // Header: Header
  }
}
</script>

<style scoped>

</style>
