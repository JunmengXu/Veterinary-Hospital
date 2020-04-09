<template>
    <div>
        <i class="el-icon-circle-plus-outline"  @click="dialogFormVisible = true"></i>
        <el-dialog
                title="创建预约"
                :visible.sync="dialogFormVisible"
        >

            <el-form :model="formInline" :rules="rules" ref="formInline" class="demo-form-inline">
                <el-form-item label="症状" prop="symptom">
                    <el-input v-model="formInline.symptom" placeholder="症状"></el-input>
                </el-form-item>
                <el-form-item label="紧急度" prop="urgency">
                    <el-select v-model="formInline.urgency" placeholder="紧急度">
                        <el-option label="normal" value="0"></el-option>
                        <el-option label="urgent" value="1"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="需求时间" required>
                    <el-col :span="11">
                        <el-form-item prop="date">
                            <el-date-picker type="date" placeholder="选择日期" v-model="formInline.date" style="width: 100%;"></el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col class="line" :span="2">-</el-col>
                    <el-col :span="11">
                        <el-form-item prop="time">
                            <el-time-picker type="fixed-time" placeholder="选择时间" v-model="formInline.time" style="width: 100%;"></el-time-picker>
                        </el-form-item>
                    </el-col>
                </el-form-item>

                <el-form-item label="所选医院" prop="hospital">
                    <el-select v-model="formInline.hospital" placeholder="所选医院">
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
    </div>
</template>

<script>
    import store from "../../../store";

    export default {
        name: 'BookingAddC',
        data () {
            return {
                dialogFormVisible: false,
                formInline: {
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
        methods: {
            onSubmit () {
                this.$http
                    .post('\'http://localhost:8181/api/addPet', {
                        name: this.formInline.petName,
                        gender: this.formInline.petGender,
                        type: this.formInline.petType
                    }).then(resp => {
                    if (resp && resp.status === 200) {
                        this.dialogFormVisible = false
                        this.$emit('onSubmit')
                    }
                })
            },
            submitForm() {
                this.$refs.formInline.validate((valid) => {
                    if (valid) {
                        // alert('submit!');
                        // console.log(this.formInline.petName+" "+this.formInline.petType+" "+this.formInline.petGender)
                        var username = store.state.user.username
                        var url = 'http://localhost:8181/api/addPet/' + username
                        this.$http
                            .post(url, {
                                name: this.formInline.petName,
                                gender: this.formInline.petGender,
                                type: this.formInline.petType
                            }).then(resp => {
                            if (resp && resp.status === 200) {
                                this.dialogFormVisible = false
                                this.$emit('onSubmit')
                            }
                        })
                        this.$notify({
                            title: '成功',
                            message: '成功添加新的宠物',
                            type: 'success'
                        });
                    } else {
                        // console.log('error submit!!');
                        this.$notify.error({
                            title: '失败',
                            message: '添加失败'
                        });
                        return false;
                    }
                });
            },
            resetForm() {
                this.$refs.formInline.resetFields();
            }
        }
    }
</script>

<style scoped>
    .el-icon-circle-plus-outline {
        margin: 50px 0 0 20px;
        font-size: 100px;
        float: left;
        cursor: pointer;
    }
</style>