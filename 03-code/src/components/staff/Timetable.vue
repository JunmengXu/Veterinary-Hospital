<template>
    <ul id="time_table">
        <li>
            <div class="days" >
                <p>March 11</p>
                <el-button id="days_btn1" size="medium" type="primary">morning 3e 2ue</el-button>
                <br/>
                <p> </p>
                <el-button id="days_btn2" size="medium" type="primary">afternoon 1e 2ue</el-button>
            </div>
        </li>
        <li>
            <div class="days" >
                <p>March 12</p>
                <el-button id="days_btn3" size="medium" type="primary">morning 3e 2ue</el-button>
                <br/>
                <p> </p>
                <el-button id="days_btn4" size="medium" type="primary">afternoon 1e 2ue</el-button>
            </div>
        </li>
        <li>
            <div class="days" >
                <p>March 13</p>
                <el-button id="days_btn5" size="medium" type="primary">morning 3e 2ue</el-button>
                <br/>
                <p> </p>
                <el-button id="days_btn6" size="medium" type="primary">afternoon 1e 2ue</el-button>
            </div>
        </li>
        <li>
            <div class="days" >
                <p>March 14</p>
                <el-button id="days_btn7" size="medium" type="primary">morning 3e 2ue</el-button>
                <br/>
                <p> </p>
                <el-button id="days_btn8" size="medium" type="primary">afternoon 1e 2ue</el-button>
            </div>
        </li>
        <li>
            <div class="days" >
                <p>March 15</p>
                <el-button id="days_btn9" size="medium" type="primary">morning 3e 2ue</el-button>
                <br/>
                <p> </p>
                <el-button id="days_btn10" size="medium" type="primary">afternoon 1e 2ue</el-button>
            </div>
        </li>
        <li>
            <div class="days" >
                <p>March 16</p>
                <el-button id="days_btn11" size="medium" type="primary">morning 3e 2ue</el-button>
                <br/>
                <p> </p>
                <el-button id="days_btn12" size="medium" type="primary">afternoon 1e 2ue</el-button>
            </div>
        </li>
        <li>
            <div class="days" >
                <p>March 17</p>
                <el-button id="days_btn13" size="medium" type="primary">morning 3e 2ue</el-button>
                <br/>
                <p> </p>
                <el-button id="days_btn14" size="medium" type="primary">afternoon 1e 2ue</el-button>
            </div>
        </li>
    </ul>
</template>

<script>
    export default {
        name: "Timetable"
    }
</script>

<style scoped>
    ul {
        list-style:none;
        margin: 0 auto;
        width: fit-content;
    }
    li{
        float:left;
        margin: 0px 20px;
    }
    .days{
        width: fit-content;
        text-align: center;
        margin-bottom: 20px;
    }
</style>