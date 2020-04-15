<template>
    <div>
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
            <el-button id="up10" size="medium" type="primary" v-on:click="loginOut">{{$t('button.logOut')}}</el-button>
<!--            <el-button id="up11" size="medium" type="primary" v-on:click="langChange">切换语言</el-button>-->
            <el-dropdown id="up9" @command="handleCommand">
                <span class="el-dropdown-link">
                        {{$t('button.switchLanguage')}}
                    <i class="el-icon-arrow-down el-icon--right"></i>
                </span>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item command="cn">{{$t('column.Chinese')}}</el-dropdown-item>
                    <el-dropdown-item command="en">{{$t('column.English')}}</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>


            <i class="el-icon-menu" style="float:right;font-size: 45px;color: #222;padding-top: 8px"></i>
            <span style="position: absolute;padding-top: 20px;right: 43%;font-size: 20px;font-weight: bold">{{$t('menu.title')}}</span>
        </el-menu>
    </div>
</template>

<script>
    export default {
        name: "CustomerHeader",
        props: {
            msg: String
        },
        data () {
            return {
                lang: "",
            }
        },
        //这里需要把数据写到computed里面否则组件不会实时刷新
        computed:{
           navList:function () {
               return [
                   {name: '/indexc', navItem: this.$t('menu.home')},
                   {name: '/petc', navItem: this.$t('button.customerHomepagePet')},
                   {name: '/appointmentc', navItem: this.$t('button.customerHomepageAppointment')},
                   {name: '/messagec', navItem: this.$t('button.customerHomepageMessage')}

               ]

           }

        },
        methods: {
            loginOut () {
                localStorage.removeItem('user')
                // localStorage.removeItem('login')
                alert(this.$t('message.exit'))
                this.$router.replace('/loginc')
            },
            // 根据下拉框的中被选中的值切换语言
            handleCommand(command) {
                // this.$message("click on item " + command);
                switch (command) {
                    case "cn": {
                        this.lang = "cn";
                        this.$i18n.locale = this.lang;
                        //本地保存语言设置，刷新页面或重新进入后也可维持原有的设置
                        localStorage.setItem('lang', this.lang);
                        break;
                    }
                    case "en": {
                        this.lang = "en";
                        this.$i18n.locale = this.lang;
                        localStorage.setItem('lang', this.lang);
                        break;
                    }

                    default:
                        break;
                }
            }
        }
    }
</script>

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