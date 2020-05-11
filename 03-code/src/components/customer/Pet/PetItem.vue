<template>
    <div>
        <el-row style="height: 840px;">
            <!--<search-bar></search-bar>-->
            <el-tooltip effect="dark" placement="right"
                        v-for="item in myPets"
                        :key="item.id">
                <p slot="content" style="font-size: 14px;margin-bottom: 6px;">{{$t('column.petNumber')}} {{item.id}}</p>
                <p slot="content" style="font-size: 13px;margin-bottom: 6px">
                    <span>{{$t('column.petName')}} {{item.name}}</span> /
                    {{$t('column.petGender')}}<span v-if="item.gender==0">male</span><span v-else>female</span> /
                    <span>{{$t('column.petType')}}{{item.type}}</span>
                </p>
                <p slot="content" style="width: 300px" class="abstract">{{$t('column.owner')}}{{item.user.username}}</p>

                <el-card style="width: 135px;margin-bottom: 20px;height: 270px;float: left;margin-right: 15px" class="book"
                         bodyStyle="padding:10px" shadow="hover">
                    <div class="cover" @click="petId = item.id, petStatus = item.status, judgeStatus()">
                        <img :src="item.cover" v-if="item.cover"> <img src="http://n.sinaimg.cn/sinacn20119/654/w1417h837/20181219/8fc7-hqnkypr2320016.jpg" alt="封面" v-else>
                    </div>
                    <div class="info">
                        <div class="title">
                            <a>{{item.name}}</a>
                        </div>
                    </div>
                    <div class="author">
                        <span class="petstatus0" v-if="item.status==0||item.status==4">{{$t('sideMenu.noAppointment')}}</span><span class="petstatus1" v-else-if="item.status==1">{{$t('sideMenu.waitingDistribution')}}</span><span class="petstatus2" v-else-if="item.status==2">{{$t('sideMenu.waitingOperation')}}</span><span class="petstatus3" v-else>{{$t('sideMenu.waitingRelease')}}</span>
                    </div>
                    <el-button size="small" style="float: right" v-if="item.status==0" @click="deleteBooking(item.id)">{{$t('button.delete')}}</el-button><el-button size="small" style="float: right" disabled v-else>{{$t('button.delete')}}</el-button>
                </el-card>

            </el-tooltip>

            <el-dialog
                    :title="$t('menu.addAppointment')"
                    :visible.sync="dialogFormVisible">
                <el-form :model="formInline2" :rules="rules" ref="formInline2" class="demo-form-inline">
                    <el-form-item :label="$t('column.symptom')" prop="symptom" >
                        <el-input v-model="formInline2.symptom" :placeholder="$t('placeholder.symptom')"></el-input>
                    </el-form-item>
                    <el-form-item :label="$t('column.urgency')" prop="urgency">
                        <el-select v-model="formInline2.urgency" :placeholder="$t('placeholder.urgency')" style="width:50%">
                            <el-option :label="$t('choices.normal')" value="0"></el-option>
                            <el-option :label="$t('choices.urgent')" value="1"></el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item :label="$t('column.time')" required>
                        <el-col :span="11">
                            <el-form-item prop="needtime">
                                <el-date-picker type="date" :placeholder="$t('placeholder.time')" v-model="formInline2.needtime" style="width: 100%;" :picker-options="pickerOptions0"></el-date-picker>
                            </el-form-item>
                        </el-col>
<!--                        <el-col class="line" :span="2">-</el-col>-->
<!--                        <el-col :span="11">-->
<!--                            <el-form-item prop="time">-->
<!--                                <el-time-picker type="fixed-time" placeholder="选择时间" v-model="formInline2.time" style="width: 100%;"></el-time-picker>-->
<!--                            </el-form-item>-->
<!--                        </el-col>-->
                    </el-form-item>

                    <el-form-item :label="$t('column.hospital')" prop="hospital">
                        <el-select v-model="formInline2.hospital" :placeholder="$t('placeholder.hospital')" style="width:50%">
                            <el-option :label="$t('choices.Beijing')" value="0"></el-option>
                            <el-option :label="$t('choices.Shanghai')" value="1"></el-option>
                            <el-option :label="$t('choices.Chengdu')" value="2"></el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item>
                        <el-button type="primary" @click="submitForm()">{{$t('button.add')}}</el-button>
                        <el-button @click="resetForm()">{{$t('button.reset')}}</el-button>
                    </el-form-item>
                </el-form>

                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">{{$t('button.cancel')}}</el-button>
                </div>
            </el-dialog>
            <pet-add></pet-add>
        </el-row>


    </div>
</template>

<script>
    import store from "../../../store";
    import PetAdd from "./PetAdd";

    export default {
        name: "PetItem",
        components:{PetAdd},
        inject:['reload'],
        data () {
            return {
                pickerOptions0: {
                    disabledDate(time) {
                        return time.getTime() < Date.now() - 8.64e7;
                    },
                },
                myPets: [],
                formInline: {
                    petName: '',
                    petGender: '',
                    petType: ''
                },
                dialogFormVisible: false,
                petStatus: 0,
                petId: 0,
                formInline2: {
                    urgency: '',
                    hospital: '',
                    symptom: '',
                    needtime: ''
                    // time:''
                },
                rules: {
                    symptom: [
                        { required: true, message: this.$t('message.symptom'), trigger: 'blur' },
                        { min: 1, max: 100, message: this.$t('message.symptomLength'), trigger: 'blur' }
                    ],
                    urgency: [
                        { required: true, message: this.$t('message.urgency'), trigger: 'change' }
                    ],
                    hospital: [
                        { required: true, message: this.$t('message.hospital'), trigger: 'change' }
                    ],
                    needtime: [
                        { type: 'date', required: true, message: this.$t('message.time'), trigger: 'change' }
                    ]
                    // time: [
                    //     { type: 'date', required: true, message: '请选择时间', trigger: 'change' }
                    // ]
                },
                formLabelWidth: '120px'
            }
        },
        created() {
            const _this = this
            var username = store.state.user.username
            var url = 'http://localhost:8181/api/pets/' + username
            this.$http.get(url).then(function (resp){
                console.log(resp)
                _this.myPets = resp.data
            })
        },
        methods: {
            submitForm() {
                this.$refs.formInline2.validate((valid) => {
                    if (valid) {
                        var petid = this.petId
                        var url = 'http://localhost:8181/api/' + petid + '/bookings'
                        this.$http
                            .post(url, {
                                urgency: this.formInline2.urgency,
                                hospital: this.formInline2.hospital,
                                symptom: this.formInline2.symptom,
                                needtime: this.formInline2.needtime
                            }).then(resp => {
                            if (resp && resp.status === 200) {
                                this.dialogFormVisible = false
                                this.$emit('onSubmit')
                                this.$notify({
                                    title: this.$t('message.successed'),
                                    message: this.$t('message.successAppointment'),
                                    type: 'success'
                                });
                                console.log(JSON.stringify(resp))
                                this.reload();
                            }else{
                                this.$notify.error({
                                    title: this.$t('message.failed'),
                                    message: this.$t('message.failingAppointment')
                                });
                                return false;
                            }
                        })
                    } else {
                        // console.log('error submit!!');
                        this.$notify.error({
                            title: this.$t('message.failed'),
                            message: this.$t('message.failingAppointment')
                        });
                        return false;
                    }
                });
            },
            resetForm() {
                this.$refs.formInline2.resetFields();
            },
            judgeStatus(){
                if(this.petStatus == 0||this.petStatus == 4){
                    this.dialogFormVisible = true
                }else{
                    this.$message.error(this.$t('message.hasAppointment'));
                }
            },
            deleteBooking (id){
                this.$confirm(this.$t('message.confirmDeletePet'), this.$t('menu.hint'), {
                    confirmButtonText: this.$t('button.confirm'),
                    cancelButtonText: this.$t('button.cancel'),
                    type: 'warning'
                }).then(() => {
                        this.$http
                            .post('http://localhost:8181/api/petDelete', {id: id}).then(resp => {
                            if (resp && resp.status === 200) {
                                console.log(JSON.stringify(resp))
                                this.reload();
                            }
                        })
                    }
                ).catch(() => {
                    this.$message({
                        type: 'info',
                        message: this.$t('message.deletionCancelled')
                    })
                })
            }
        }
    }
</script>

<style scoped>
    .cover {
        width: 115px;
        height: 172px;
        margin-bottom: 7px;
        overflow: hidden;
        cursor: pointer;
    }

    img {
        width: 115px;
        height: 172px;
        object-fit: cover;
        /*margin: 0 auto;*/
    }

    .addPet{
        object-fit: none;
    }

    .title {
        font-size: 14px;
        text-align: left;
    }

    .author {
        color: #333;
        width: 140px;
        font-size: 13px;
        margin-bottom: 6px;
        text-align: left;
    }

    .abstract {
        display: block;
        line-height: 17px;
    }

    .petstatus0 {
        margin-left: 5px;
        color: #409EFF;
    }

    .petstatus1 {
        margin-left: 5px;
        color: goldenrod;
    }

    .petstatus2 {
        margin-left: 5px;
        color: brown;
    }

    .petstatus3 {
        margin-left: 5px;
        color: forestgreen;
    }

    a {
        text-decoration: none;
    }

    a:link, a:visited, a:focus {
        color: #3377aa;
    }
</style>