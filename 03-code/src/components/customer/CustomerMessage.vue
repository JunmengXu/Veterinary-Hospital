<template>
    <div id="message_box">
        <div id="box_head">{{$t('menu.contactStaff')}}</div>
        <div id="message_show" class="message_show">
            <div v-for="item in myMessages" :key="item.id">
                <div class="message_time">
                    <span class="time">{{item.time}}</span>
                </div>
                <div class="message_not_me" v-if="item.type == 1">
                    <div class="col_not_me">
                        <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                    </div>
                    <div class="message_not_me_content">
                        {{item.content}}
                    </div>
                </div>
                <div class="message_is_me" v-if="item.type == 0">
                    <div class="col_is_me">
                        <el-avatar shape="circle" fit="scale-down" :src="userinfo.userimage" v-if="userinfo.userimage"></el-avatar><el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" v-else></el-avatar>
                    </div>
                    <div class="message_is_me_content">
                        <span>{{item.content}}</span>
                    </div>
                </div>
            </div>
        </div>

        <div id="message_send">
            <svg class="icon" id="icon" aria-hidden="true">
                <use xlink:href="#icon-tupian"></use>
            </svg>

            <br/>
<!--            <textarea  id="input_box"  style="border:none;outline:none;background: #f4f5f7"></textarea>-->
            <el-input
                    type="textarea"
                    :autosize="{ minRows: 3, maxRows: 4}"
                    :placeholder="$t('placeholder.content')"
                    v-model="textarea"
                    maxlength="300"
                    show-word-limit>
            </el-input>
            <br/>
            <el-button id="send" size="medium" type="primary" @click="sendMessage()">send</el-button>
        </div>

    </div>

</template>

<script>
    import store from "../../store";

    export default {
        name: "CustomerMessage",
        inject:['reload'],
        data () {
            return{
                myMessages: [],
                textarea: '',
                userinfo: ''
            }
        },
        created() {
            const _this = this
            var username = store.state.user.username
            var url = 'http://localhost:8181/api/messages/' + username
            this.$http.get(url).then(function (resp){
                console.log(resp)
                _this.myMessages = resp.data
            })

            var url2 = 'http://localhost:8181/api/personInfo/' + username
            this.$http.get(url2).then(function (resp){
                console.log(resp)
                _this.userinfo = resp.data
            })

            //延缓100毫秒执行，将滑动栏默认到最底端
            setTimeout(function () {
                var container = _this.$el.querySelector("#message_show");
                container.scrollTop = container.scrollHeight;
            },100);
        },
        methods: {
            sendMessage() {
                var username = store.state.user.username
                var url = 'http://localhost:8181/api/addMessage/' + username
                this.$http
                    .post(url, {
                        content: this.textarea,
                        type: 0
                    }).then(resp => {
                    if (resp && resp.status === 200) {
                        // this.textarea = ''
                        console.log(JSON.stringify(resp))
                        this.reload();
                    }else{
                        this.$notify.error({
                            title: this.$t('message.failed'),
                            message: this.$t('message.failingMessage')
                        });
                        return false;
                    }
                })
            }
        }
    }
</script>

<style scoped>
    .icon{
        height: 20px;
        width: 20px;
        padding: 10px 10px 10px 0px;
    }
    #message_box{
        border: 1px solid #d7dae2;
        border-radius: 4px ;
        background: #f4f5f7;
        box-shadow: 0 1px 2px rgba(0, 0, 0, .12);
        width: 80%;
        height: 650px;
        margin: 0 auto;
        margin-top: 20px;
    }
    #box_head{
        background: white;
        border-bottom: 1px solid #d7dae2;
        border-radius: 4px 4px 0px 0px ;
        height: 35px;
        text-align: center;
        line-height: 35px;
    }
    #message_show{
        height: 430px;
        border-bottom: 1px solid #d7dae2;
        overflow-y:auto;
    }
    ::-webkit-scrollbar {/*滚动条整体样式*/
        width: 10px;     /*高宽分别对应横竖滚动条的尺寸*/
        height: 1px;
    }
    ::-webkit-scrollbar-thumb {/*滚动条里面小方块*/
        border-radius: 10px;
        background: #aaaaaa;
    }
    #message_send{
        padding-left: 10px;
        width: 97%;
    }
    #input_box{
        resize: none;
        height: 85px;
        width: 100%;
        font-size: 15px;
    }
    #send{
        float: right;
        margin-top: 5px;
    }
    .message_not_me{
        width: 100%;
        float: left;
        overflow: hidden;
    }
    .message_is_me{
        width: 100%;
        float: right;
        overflow: hidden;
    }
    .message_is_me_content{
        float: right;
        color: white;
        margin: 10px;
        padding: 10px;
        border-radius: 8px 0px 8px 8px ;
        background: #409EFF;
        max-width: 70%;
        width: fit-content;
        word-wrap: break-word;
        word-break: break-all;
        overflow: hidden;
    }
    .message_not_me_content{
        float: left;
        margin: 10px;
        padding: 10px;
        border-radius: 0px 8px 8px 8px ;
        background: white;
        width: fit-content;
        max-width: 70%;
        word-wrap: break-word;
        word-break: break-all;
        overflow: hidden;
    }
    .message_time{
        text-align: center;
        font-size: 15px;
        color: gray;
        padding: 10px 0px;
    }
    .col_not_me{
        width: 40px;
        float: left;
        padding: 0px 10px;
    }
    .col_is_me{
        width: 40px;
        float: right;
        padding: 0px 10px;
    }
</style>