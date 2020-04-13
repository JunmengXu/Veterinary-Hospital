<template>
    <div class="homepage_contain">
        <div id="homepage_title">
            <div id="homepage_title_inside">
                <svg class="icon" id="icon" aria-hidden="true">
                    <use xlink:href="#icon-gou"></use>
                </svg>
                <h1 id="homepage_title_inside_1">动物医院员工后台管理系统 Management System Of Veterinary hospital</h1>
            </div>
<!--            <hr style="height:1px;border:none;border-top:1px solid "/>-->
        </div>
        <div id="homepage_box_bar">
            <div class="homepage_box">
                <img class="homepage_image" src="@/assets/image/33.jpg" alt="">
                <br/>
                <el-button id="homepage_btn3" size="medium" type="primary" @click="handleClick1()">历史预约单 Historical appointment form</el-button>
            </div>
            <div class="homepage_box">
                <img class="homepage_image" src="@/assets/image/22.jpg" alt="">
                <br/>
                <el-button id="homepage_btn2" size="medium" type="primary" @click="handleClick2()">私信 Messages</el-button>
            </div>
            <div class="homepage_box">
                <img class="homepage_image" src="@/assets/image/11.jpg" alt="">
                <br/>
                <el-button id="homepage_btn1" size="medium" type="primary" @click="handleClick3()">时刻表 Schedule</el-button>
            </div>
        </div>

    </div>
</template>

<script>
    export default {
        name: "Homepage",
        methods: {
            handleClick1() {
                this.$router.push({
                    path: '/history',
                })
            },
            handleClick2() {
                this.$router.push({
                    path: '/message',
                })
            },
            handleClick3() {
                this.$router.push({
                    path: '/timetable',
                })
            }
        }
    }
</script>

<style scoped>
    #homepage_title{
        padding-top: 20px;
        margin: 0 auto;
        width: fit-content;
    }
    .homepage_box{
        width: fit-content;
        text-align: center;
        margin: 20px;
        padding: 20px;
        border-radius: 4px;
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    }
    .homepage_image{
        width: 300px;
        margin-bottom: 15px;
    }
    #homepage_box_bar{
        display: flex;
        justify-content: center;
    }
    #icon{
        width: 60px;
        height: 60px;
        margin: 15px;
    }
    #homepage_title_inside{
        display: flex;
    }

</style>