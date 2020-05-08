<template>
    <div>
<!--        <div>-->
<!--            <ul id="time_table">-->
<!--                <li>-->
<!--                    <div class="days" >-->
<!--                        <p>March 11</p>-->
<!--                        <el-button id="days_btn1" size="medium" type="primary">morning 3e 2ue</el-button>-->
<!--                        <br/>-->
<!--                        <p> </p>-->
<!--                        <el-button id="days_btn2" size="medium" type="primary">afternoon 1e 2ue</el-button>-->
<!--                    </div>-->
<!--                </li>-->
<!--                <li>-->
<!--                    <div class="days" >-->
<!--                        <p>March 12</p>-->
<!--                        <el-button id="days_btn3" size="medium" type="primary">morning 3e 2ue</el-button>-->
<!--                        <br/>-->
<!--                        <p> </p>-->
<!--                        <el-button id="days_btn4" size="medium" type="primary">afternoon 1e 2ue</el-button>-->
<!--                    </div>-->
<!--                </li>-->
<!--                <li>-->
<!--                    <div class="days" >-->
<!--                        <p>March 13</p>-->
<!--                        <el-button id="days_btn5" size="medium" type="primary">morning 3e 2ue</el-button>-->
<!--                        <br/>-->
<!--                        <p> </p>-->
<!--                        <el-button id="days_btn6" size="medium" type="primary">afternoon 1e 2ue</el-button>-->
<!--                    </div>-->
<!--                </li>-->
<!--                <li>-->
<!--                    <div class="days" >-->
<!--                        <p>March 14</p>-->
<!--                        <el-button id="days_btn7" size="medium" type="primary">morning 3e 2ue</el-button>-->
<!--                        <br/>-->
<!--                        <p> </p>-->
<!--                        <el-button id="days_btn8" size="medium" type="primary">afternoon 1e 2ue</el-button>-->
<!--                    </div>-->
<!--                </li>-->
<!--                <li>-->
<!--                    <div class="days" >-->
<!--                        <p>March 15</p>-->
<!--                        <el-button id="days_btn9" size="medium" type="primary">morning 3e 2ue</el-button>-->
<!--                        <br/>-->
<!--                        <p> </p>-->
<!--                        <el-button id="days_btn10" size="medium" type="primary">afternoon 1e 2ue</el-button>-->
<!--                    </div>-->
<!--                </li>-->
<!--                <li>-->
<!--                    <div class="days" >-->
<!--                        <p>March 16</p>-->
<!--                        <el-button id="days_btn11" size="medium" type="primary">morning 3e 2ue</el-button>-->
<!--                        <br/>-->
<!--                        <p> </p>-->
<!--                        <el-button id="days_btn12" size="medium" type="primary">afternoon 1e 2ue</el-button>-->
<!--                    </div>-->
<!--                </li>-->
<!--                <li>-->
<!--                    <div class="days" >-->
<!--                        <p>March 17</p>-->
<!--                        <el-button id="days_btn13" size="medium" type="primary">morning 3e 2ue</el-button>-->
<!--                        <br/>-->
<!--                        <p> </p>-->
<!--                        <el-button id="days_btn14" size="medium" type="primary">afternoon 1e 2ue</el-button>-->
<!--                    </div>-->
<!--                </li>-->
<!--            </ul>-->
<!--        </div>-->
        <el-select style="padding-left: 20px" v-model="value" placeholder="请选择" @change="cityChange">
            <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
            </el-option>
        </el-select>

        <div>
            <el-calendar>
                <!-- 这里使用的是 2.5 slot 语法，对于新项目请使用 2.6 slot 语法-->
                <template
                        slot="dateCell"
                        slot-scope="{date, data}">
                    <div>

                    <div :class="data.isSelected ? 'display' : ''">
                        {{ data.day.split('-').slice(1).join(' / ') }} {{ data.isSelected ? '✔️' : ''}}</div>

                        <div v-for="item in booking" :key="item.id">
                            <div v-if="(item.needtime.split('-').slice(0)[0]).indexOf(data.day.split('-').slice(0)[0])!=-1">
                                <div v-if="(item.needtime.split('-').slice(1)[0]).indexOf(data.day.split('-').slice(1)[0])!=-1">
                                    <div v-if="(item.needtime.split('-').slice(2).join('-')).indexOf(data.day.split('-').slice(2).join('-'))!=-1">
                                        <el-tooltip effect="dark" placement="right">
                                            <p class="item" slot="content">{{$t('column.customer')}}{{item.pet.user.username}}</p>
                                            <p class="item" slot="content">{{$t('column.pet')}}{{item.pet.name}}</p>
                                            <p class="item" slot="content">{{$t('column.symptom')}}{{item.symptom}}</p>
                                            <p class="item" slot="content">{{$t('column.urgency')}}<span v-if="item.urgency==0" style="color: lawngreen">{{$t('choices.normal')}}</span><span v-else style="color: crimson">{{$t('choices.urgent')}}</span></p>
                                            <p class="item" slot="content">{{$t('column.hospital')}}<span v-if="item.hospital==0">{{$t('choices.Beijing')}}</span><span v-else-if="item.hospital==1">{{$t('choices.Shanghai')}}</span><span v-else>{{$t('choices.Chengdu')}}</span></p>
                                            <div class="is-selected" @click="handleClick(item.id)">{{$t('column.customer')}}{{item.pet.user.username}} / {{$t('column.pet')}}{{item.pet.name}}</div>
                                        </el-tooltip>
                                    </div>
                                    <div v-else></div>
                                </div>
                                <div v-else></div>
                            </div>
                            <div v-else></div>
                        </div>

                    </div>
                </template>
            </el-calendar>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'Timetable',
        data () {
            return{
                booking: [],
                options: [{
                    value: '0',
                    label: this.$t('choices.Beijing')
                }, {
                    value: '1',
                    label: this.$t('choices.Shanghai')
                }, {
                    value: '2',
                    label: this.$t('choices.Chengdu')
                }],
                value: '0'
            }
        },
        created() {
            const _this = this
            this.$http.get('http://localhost:8181/api/1/0/calendar/bookings').then(function (resp){
                console.log(resp)
                _this.booking = resp.data
            })
        },
        methods: {
            cityChange(){
                const _this = this
                var url = 'http://localhost:8181/api/1/'+ this.value + '/calendar/bookings'
                this.$http.get(url).then(function (resp){
                    console.log(resp)
                    _this.booking = resp.data
                })
            },
            handleClick(id){
                this.$router.push({
                    path:'/appointment',
                    query:{
                        booking:id},
                })
            }
        }
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
    .is-selected {
        color: #1989FA;
        font-size: x-small;
    }
    .display{
        font-size: inherit;
    }
    .item{
        font-size: large;
    }
</style>