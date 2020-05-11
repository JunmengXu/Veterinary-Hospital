<template>
    <div class="homepage_contain">
        <div class="block">
            <el-carousel height="160px">
                <el-carousel-item v-for="item in imagebox" :key="item.id" class="inner">
                    <img :src="item.idView" class="image">
                </el-carousel-item>
            </el-carousel>
        </div>
        <div id="homepage_title">
            <div id="homepage_title_inside">
                <svg class="icon" id="icon" aria-hidden="true">
                    <use xlink:href="#icon-gou"></use>
                </svg>
                <h1 id="homepage_title_inside_1">{{$t('menu.title')}}</h1>
            </div>
            <!--            <hr style="height:1px;border:none;border-top:1px solid "/>-->
        </div>
        <div>
            <ul id="homepage_box_bar">
                <li>
                    <div class="homepage_box">
                        <img class="homepage_image" src="@/assets/image/11.jpg" alt="">
                        <br/>
                        <el-button id="homepage_btn1" size="medium" type="primary" @click="handleClick1()">{{$t('button.customerHomepagePet')}}</el-button>
                    </div>
                </li>
                <li>
                    <div class="homepage_box">
                        <img class="homepage_image" src="@/assets/image/33.jpg" alt="">
                        <br/>
                        <el-button id="homepage_btn3" size="medium" type="primary" @click="handleClick2()">{{$t('button.customerHomepageAppointment')}}</el-button>
                    </div>
                </li>
                <li>
                    <div class="homepage_box">
                        <img class="homepage_image" src="@/assets/image/22.jpg" alt="">
                        <br/>
                        <el-button id="homepage_btn2" size="medium" type="primary" @click="handleClick3()">{{$t('button.customerHomepageMessage')}}</el-button>
                    </div>
                </li>

            </ul>
        </div>

        <footer>
            <div class="container">
                <img src="../../assets/image/dog.png" height="40">
                <p>{{$t('choices.Beijing')}}</p>
                <p>{{$t('choices.Shanghai')}}</p>
                <p>{{$t('choices.Chengdu')}}</p>
            </div>
        </footer>

    </div>
</template>

<script>
    export default {
        name: "CustomerHomePage",
        data() {
            return {
                imagebox: [
                    {id: 0, idView: require('../../assets/image/aa.jpg')},
                    {id: 1, idView: require('../../assets/image/bb.jpg')},
                    {id: 2, idView: require('../../assets/image/cc.jpg')},
                ]
            }
        },
        methods: {
            handleClick1() {
                this.$router.push({
                    path: '/petc',
                })
            },
            handleClick2() {
                this.$router.push({
                    path: '/appointmentc',
                })
            },
            handleClick3() {
                this.$router.push({
                    path: '/messagec',
                })
            },
            setSize:function () {
                // 通过浏览器宽度(图片宽度)计算高度
                this.bannerHeight = 400 / 1920 * this.screenWidth;
            }
        },
        mounted() {
            // 首次加载时,需要调用一次
            this.screenWidth = window.innerWidth;
            this.setSize();
            // 窗口大小发生改变时,调用一次
            window.onresize = () => {
                this.screenWidth = window.innerWidth;
                this.setSize();
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
    }
    #icon{
        width: 60px;
        height: 60px;
        margin: 15px;
    }
    #homepage_title_inside{
        display: flex;
    }
    .el-carousel__item h3 {
        color: #475669;
        font-size: 14px;
        opacity: 0.75;
        line-height: 150px;
        margin: 0;
    }
    .block{
        /*设置图片宽度和浏览器宽度一致*/
        width:75%;
        max-width: 1100px;
        margin: 0 auto;
    }
    .inner{
        text-align: center;
        line-height:160px;
        height:160px;
    }
    .image{
        width:100%;
    }
    footer{
        background-color: #00427a;
        height: fit-content;
        margin-top: 50px;
        padding: 30px 0;
    }
    .container {
        max-width: 1100px;
        width: 75%;
        padding-left: 20px;
        margin: 0 auto;
        color: white;
        height: fit-content;
        font-family: 'Open Sans',Helvetica,Arial,sans-serif;
    }
    p{
        padding: 0px;
    }
    ul {
        list-style:none;
        margin: 0 auto;
        width: fit-content;
    }
    li{
        float:left;
        margin: 0px 20px;
    }
</style>
