<template>
    <div>
        <i class="el-icon-circle-plus-outline"  @click="dialogFormVisible = true"></i>
        <el-dialog
                title="添加宠物"
                :visible.sync="dialogFormVisible"
                >

            <el-form :model="formInline" :rules="rules" ref="formInline" class="demo-form-inline">
                <el-form-item label="宠物名称" prop="petName">
                    <el-input v-model="formInline.petName" placeholder="宠物名称"></el-input>
                </el-form-item>
                <el-form-item label="宠物性别" prop="petGender">
                    <el-select v-model="formInline.petGender" placeholder="宠物性别">
                        <el-option label="male" value="0"></el-option>
                        <el-option label="female" value="1"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="宠物种类" prop="petType">
                    <el-input v-model="formInline.petType" placeholder="宠物种类"></el-input>
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
        name: 'PetAdd',
        data () {
            return {
                dialogFormVisible: false,
                formInline: {
                    petName: '',
                    petGender: '',
                    petType: ''
                },
                rules: {
                    petName: [
                        { required: true, message: '请输入宠物名称', trigger: 'blur' },
                        { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
                    ],
                    petGender: [
                        { required: true, message: '请至少选择一个宠物性别', trigger: 'change' }
                    ],
                    petType: [
                        { required: true, message: '请填写宠物种类', trigger: 'blur' }
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
                                this.$notify({
                                    title: '成功',
                                    message: '成功添加新的宠物',
                                    type: 'success'
                                });
                            }else{
                                this.$notify.error({
                                    title: '失败',
                                    message: '添加失败'
                                });
                            }
                        })

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