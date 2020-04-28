<template>
    <el-menu
            class="categories"
            default-active="0"
            @select="handleSelect"
            active-text-color="blue">
<!--        @select="handleSelect"-->
        <el-menu-item index="0">
            <i class="el-icon-menu"></i>
            <span slot="title">{{$t('sideMenu.all')}}</span>
        </el-menu-item>
        <el-menu-item index="1">
            <i class="el-icon-menu"></i>
            <span slot="title">{{$t('choices.Beijing')}}</span>
        </el-menu-item>
        <el-menu-item index="2">
            <i class="el-icon-menu"></i>
            <span slot="title">{{$t('choices.Shanghai')}}</span>
        </el-menu-item>
        <el-menu-item index="3">
            <i class="el-icon-menu"></i>
            <span slot="title">{{$t('choices.Chengdu')}}</span>
        </el-menu-item>
    </el-menu>
</template>

<script>
    export default {
        name: 'SideMenuBooking',
        data (){
            return {
                select: 0
            }
        },
        methods:{
            handleSelect (key){
                this.select = key
                this.$emit('indexSelect')
            }
        }
    }
</script>

<style scoped>
    .categories {
        position: fixed;
        margin-left: 50%;
        left: -700px;
        top: 100px;
        width: 150px;
    }
</style>