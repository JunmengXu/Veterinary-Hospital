<template>
    <div>

<!--        <div id="up">-->
<!--            <ul>-->
<!--                <li>-->
<!--                    <svg class="icon" id="icon" aria-hidden="true">-->
<!--                        <use xlink:href="#icon-gou"></use>-->
<!--                    </svg>-->
<!--                </li>-->
<!--                <li>-->
<!--                    <el-link :underline="false" id="up1">动物医院员工后台管理系统/Management System Of Veterinary hospital</el-link>-->
<!--                </li>-->
<!--                <li>-->
<!--                    <el-link :underline="false" id="up2">处理预约单/Process appointment form</el-link>-->
<!--                </li>-->
<!--                <li>-->
<!--                    <el-link :underline="false" id="up3">私信/Messages</el-link>-->
<!--                </li>-->
<!--                <li>-->
<!--                    <el-link :underline="false" id="up4">历史预约单/Historical appointment form</el-link>-->
<!--                </li>-->
<!--                    <el-button id="up9" size="medium" type="primary" v-on:click="loginOut">登出/Logout</el-button>-->
<!--            </ul>-->
<!--        </div>-->
<!--        <hr style="height:1px;border:none;background-color:#909399 "/>-->

    <el-menu
            :default-active="'/index'"
            router
            mode="horizontal"
            background-color="white"
            text-color="#222"
            active-text-color="blue"
            style="min-width: 1300px">
        <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name">
            {{ item.navItem }}
        </el-menu-item>
<!--        <a href="#nowhere" style="color: #222;float: right;padding: 20px;">更多功能</a>-->
        <el-button id="up10" size="medium" type="primary" v-on:click="loginOut">登出</el-button>

        <el-dropdown id="up9" @command="handleCommand">
                <span class="el-dropdown-link">
                        中英文切换
                    <i class="el-icon-arrow-down el-icon--right"></i>
                </span>
            <el-dropdown-menu slot="dropdown">
                <el-dropdown-item command="cn">中文</el-dropdown-item>
                <el-dropdown-item command="en">英文</el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>

        <i class="el-icon-menu" style="float:right;font-size: 45px;color: #222;padding-top: 8px"></i>
        <span style="position: absolute;padding-top: 20px;right: 20%;font-size: 20px;font-weight: bold">Management System Of Veterinary hospital</span>
    </el-menu>

    </div>
</template>

<script>
export default {
    name: 'HelloWorld',
    props: {
        msg: String
    },
    data () {
        return {
            navList: [
                {name: '/index', navItem: 'Home'},
                {name: '/history', navItem: 'Historical appointment form'},
                {name: '/message', navItem: 'Messages'},
                {name: '/timetable', navItem: 'Schedule'},
                {name: '/appointment', navItem: 'Process appointment form'}
            ]
        }
    },
    methods: {
        loginOut () {
            localStorage.removeItem('user')
            // localStorage.removeItem('login')
            alert('退出成功')
            this.$router.replace('/login')
            },
        // 根据下拉框的中被选中的值切换语言
        handleCommand(command) {
            // this.$message("click on item " + command);
            switch (command) {
                case "cn": {
                    this.lang = "cn";
                    this.$i18n.locale = this.lang;
                    break;
                }
                case "en": {
                    this.lang = "en";
                    this.$i18n.locale = this.lang;
                    break;
                }

                default:
                    break;
            }
        }
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    #icon{
        float: left;
        margin-top: 10px;
        font-size: 20px;
        margin-left: 40px;
    }
    #up1,#up2,#up3,#up4{
        float: left;
        margin-top: 10px;
        margin-left: 15px;
    }
    #up1{
        margin-right: 35px;
    }
    #up9,#up10 {
        margin-top: 3px;
        margin-right: 10px;
        margin-left: 10px;
        float: right;
    }
    #up10{
        margin-top: 12px;
    }
    #up{
        height: fit-content;
        padding: 1px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
        /*float: right;*/
    }
    .icon {
        width: 1em;
        height: 1em;
        vertical-align: -0.15em;
        fill: currentColor;
        overflow: hidden;
    }
ul {
  list-style-type: none;
  margin: 6px 0px 14px;
}
li {
  display: inline-block;
  margin: 0 10px;
}
</style>
