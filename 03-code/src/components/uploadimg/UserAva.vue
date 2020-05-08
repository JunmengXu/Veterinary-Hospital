<template>
    <el-upload
            class="avatar-uploader"
            ref="upload"
            action="http://localhost:8181/api/changeAva/"
            :limit="1"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :before-remove="beforeRemove"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeUpload"
            :on-exceed="handleExceed"
    >
        <img v-if="url" :src="url" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        <div slot="tip" class="el-upload__tip">{{$t('message.imageRule')}}</div>
    </el-upload>
</template>

<script>

    export default {
        name: 'UserAva',
        data () {
            return {
                url: '',
                imageUrl:''
            }
        },
        methods: {
            handleRemove (file, fileList) {
                console.log(file, fileList)
                this.$emit('removeUpload')
                this.imageUrl = ''
                this.url = ''
            },
            handlePreview (file) {
                console.log(file);
            },
            handleExceed (files, fileList) {
                // this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`)
                console.log(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`)
                this.$message.warning(this.$t('message.imageAmount'))
            },
            beforeRemove (file, fileList) {
                console.log(fileList)
                return this.$confirm(this.$t('message.confirmRemove')+`${file.name}?`)
            },
            handleSuccess (response) {
                this.url = response
                this.$emit('onUpload')
                this.$message.warning(this.$t('message.successUpload'))
            },
            clear () {
                this.$refs.upload.clearFiles()
            },
            beforeUpload(file) {
                const isIMG =
                    file.type === 'image/jpg' ||
                    file.type === 'image/jpeg' ||
                    file.type === 'image/png'
                const isLt2M = file.size / 1024 / 1024 < 2;

                if (!isIMG) {
                    this.$message.error(this.$t('message.imageFormat'));
                }
                if (!isLt2M) {
                    this.$message.error(this.$t('message.imageSize'));
                }
                return isIMG && isLt2M;
            },
            handleAvatarSuccess(res, file, response) {
                this.imageUrl = response[0].response
                this.url = URL.createObjectURL(file.raw);
                this.$emit('onUpload')
                this.$message.success(this.$t('message.successUpload'))
            }
        }
    }
</script>

<style>
    .avatar-uploader .el-upload {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }
    .avatar-uploader .el-upload:hover {
        border-color: #409EFF;
    }
    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 178px;
        height: 178px;
        line-height: 178px;
        text-align: center;
    }
    .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }
</style>