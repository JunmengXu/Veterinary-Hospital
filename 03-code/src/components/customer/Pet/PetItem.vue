<template>
    <div>
        <el-row style="height: 840px;">
            <!--<search-bar></search-bar>-->
            <el-tooltip effect="dark" placement="right"
                        v-for="item in myPets"
                        :key="item.id">
                <p slot="content" style="font-size: 14px;margin-bottom: 6px;">宠物编号 {{item.id}}</p>
                <p slot="content" style="font-size: 13px;margin-bottom: 6px">
                    <span>宠物名称：{{item.name}}</span> /
                    宠物性别：<span v-if="item.gender==0">male</span><span v-else>female</span> /
                    <span>宠物种类：{{item.type}}</span>
                </p>
                <p slot="content" style="width: 300px" class="abstract">所属主人: {{item.user.username}}</p>
                <el-card style="width: 135px;margin-bottom: 20px;height: 233px;float: left;margin-right: 15px" class="book"
                         bodyStyle="padding:10px" shadow="hover">
                    <div class="cover" @click="dialogFormVisible = true, petId = item.id">
                        <img src="http://n.sinaimg.cn/sinacn20119/654/w1417h837/20181219/8fc7-hqnkypr2320016.jpg" alt="封面">
                    </div>
                    <div class="info">
                        <div class="title">
                            <a>{{item.name}}</a>
                        </div>
                    </div>
                    <div class="author">{{item.type}}</div>
                </el-card>

            </el-tooltip>

            <el-dialog
                    title="创建预约"
                    :visible.sync="dialogFormVisible">
                <el-form :model="formInline2" :rules="rules" ref="formInline2" class="demo-form-inline">
                    <el-form-item label="症状" prop="symptom">
                        <el-input v-model="formInline2.symptom" placeholder="症状"></el-input>
                    </el-form-item>
                    <el-form-item label="紧急度" prop="urgency">
                        <el-select v-model="formInline2.urgency" placeholder="紧急度">
                            <el-option label="normal" value="0"></el-option>
                            <el-option label="urgent" value="1"></el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="需求时间" required>
                        <el-col :span="11">
                            <el-form-item prop="date">
                                <el-date-picker type="date" placeholder="选择日期" v-model="formInline2.date" style="width: 100%;"></el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col class="line" :span="2">-</el-col>
                        <el-col :span="11">
                            <el-form-item prop="time">
                                <el-time-picker type="fixed-time" placeholder="选择时间" v-model="formInline2.time" style="width: 100%;"></el-time-picker>
                            </el-form-item>
                        </el-col>
                    </el-form-item>

                    <el-form-item label="所选医院" prop="hospital">
                        <el-select v-model="formInline2.hospital" placeholder="所选医院">
                            <el-option label="北京" value="0"></el-option>
                            <el-option label="上海" value="1"></el-option>
                            <el-option label="成都" value="2"></el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item>
                        <el-button type="primary" @click="submitForm()">添加</el-button>
                        <el-button @click="resetForm()">重置</el-button>
                    </el-form-item>
                </el-form>

                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
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
        data () {
            return {
                myPets: [],
                formInline: {
                    petName: '',
                    petGender: '',
                    petType: ''
                },
                dialogFormVisible: false,
                petId: 0,
                formInline2: {
                    urgency: '',
                    hospital: '',
                    symptom: '',
                    date: '',
                    time:''
                },
                rules: {
                    symptom: [
                        { required: true, message: '请输入宠物症状', trigger: 'blur' },
                        { min: 1, max: 100, message: '长度在 1 到 100 个字符', trigger: 'blur' }
                    ],
                    urgency: [
                        { required: true, message: '请至少选择一个紧急度', trigger: 'change' }
                    ],
                    hospital: [
                        { required: true, message: '请至少选择一个医院', trigger: 'change' }
                    ],
                    date: [
                        { type: 'date', required: true, message: '请选择日期', trigger: 'change' }
                    ],
                    time: [
                        { type: 'date', required: true, message: '请选择时间', trigger: 'change' }
                    ]
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
                        // alert('submit!');
                        // console.log(this.formInline.petName+" "+this.formInline.petType+" "+this.formInline.petGender)
                        // var username = store.state.user.username
                        // var url = 'http://localhost:8181/api/addPet/' + username
                        // this.$http
                        //     .post(url, {
                        //         name: this.formInline.petName,
                        //         gender: this.formInline.petGender,
                        //         type: this.formInline.petType
                        //     }).then(resp => {
                        //     if (resp && resp.status === 200) {
                        //         this.dialogFormVisible = false
                        //         this.$emit('onSubmit')
                        //     }
                        // })
                        this.$notify({
                            title: '成功',
                            message: '成功添加新的预约',
                            type: 'success'
                        });
                    } else {
                        // console.log('error submit!!');
                        this.$notify.error({
                            title: '失败',
                            message: '添加预约失败'
                        });
                        return false;
                    }
                });
            },
            resetForm() {
                this.$refs.formInline2.resetFields();
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
        width: 102px;
        font-size: 13px;
        margin-bottom: 6px;
        text-align: left;
    }

    .abstract {
        display: block;
        line-height: 17px;
    }

    a {
        text-decoration: none;
    }

    a:link, a:visited, a:focus {
        color: #3377aa;
    }
</style>