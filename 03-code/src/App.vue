<template>
  <div id="app">
    <Header/>
    <Homepage/>
    <Timetable/>
    <Console/>
    <DailyDetail/>
    <BookingDetail/>
  </div>
</template>

<script>
  import Header from './components/Header.vue'
  import BookingDetail from "@/components/BookingDetail";
  import DailyDetail from "@/components/DailyDetail";
  import Homepage from "./components/Homepage";
  import Console from "@/components/Console";
  import Timetable from "@/components/Timetable";

  export default {
    name: 'App',
    components: {
      BookingDetail: BookingDetail,
      DailyDetail: DailyDetail,
      Timetable: Timetable,
      Console: Console,
      Homepage: Homepage,
      Header: Header
    }
  }
</script>

<style>
  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    /*text-align: center;*/
    color: #2c3e50;
    /*margin-top: 60px;*/
  }
</style>
