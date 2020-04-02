<template>
    <div>

        <div id="up">
            <ul>
                <li>
                    <svg class="icon" id="icon" aria-hidden="true">
                        <use xlink:href="#icon-gou"></use>
                    </svg>
                </li>
                <li>
                    <el-link :underline="false" id="up1">动物医院员工后台管理系统</el-link>
                </li>
                <li>
                    <el-link :underline="false" id="up2">处理预约单</el-link>
                </li>
                <li>
                    <el-link :underline="false" id="up3">私信</el-link>
                </li>
                <li>
                    <el-link :underline="false" id="up4">历史预约单</el-link>
                </li>



                        <el-button id="up9" size="medium" type="primary" v-on:click="loginOut">登出</el-button>




            </ul>







        </div>
<!--        <hr style="height:1px;border:none;background-color:#909399 "/>-->
    </div>
</template>

<script>
export default {
    name: 'HelloWorld',
    props: {
        msg: String
    },
    methods: {
        loginOut () {
            localStorage.removeItem('user')
            // localStorage.removeItem('login')
            alert('退出成功')
            this.$router.replace('/login')
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
    #up9 {
        margin-top: 3px;
        margin-right: 10px;
        float: right;
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
