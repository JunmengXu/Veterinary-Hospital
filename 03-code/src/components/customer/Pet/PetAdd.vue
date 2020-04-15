<template>
    <div>
        <i class="el-icon-circle-plus-outline"  @click="dialogFormVisible = true"></i>
        <el-dialog
                :title="$t('menu.addPet')"
                :visible.sync="dialogFormVisible"
                >

            <el-form :model="formInline" :rules="rules" ref="formInline" class="demo-form-inline">
                <el-form-item :label="$t('column.petName')" prop="petName">
                    <el-input v-model="formInline.petName" :placeholder="$t('placeholder.petName')"></el-input>
                </el-form-item>
                <el-form-item :label="$t('column.petGender')" prop="petGender">
                    <el-select v-model="formInline.petGender" :placeholder="$t('placeholder.petGender')">
                        <el-option :label="$t('choices.male')" value="0"></el-option>
                        <el-option :label="$t('choices.female')" value="1"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item :label="$t('column.petType')" prop="petType">
                    <el-input v-model="formInline.petType" :placeholder="$t('placeholder.petType')"></el-input>
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
                        { required: true, message: this.$t('message.petName'), trigger: 'blur' },
                        { min: 1, max: 20, message: this.$t('message.petNameLength'), trigger: 'blur' }
                    ],
                    petGender: [
                        { required: true, message: this.$t('message.petGender'), trigger: 'change' }
                    ],
                    petType: [
                        { required: true, message: this.$t('message.petType'), trigger: 'blur' }
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
                                    title: this.$t('message.successed'),
                                    message: this.$t('message.successAddPet'),
                                    type: 'success'
                                });
                            }else{
                                this.$notify.error({
                                    title: this.$t('message.failed'),
                                    message: this.$t('message.failingAddPet')
                                });
                            }
                        })

                    } else {
                        // console.log('error submit!!');
                        this.$notify.error({
                            title: this.$t('message.failed'),
                            message: this.$t('message.failingAddPet')
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