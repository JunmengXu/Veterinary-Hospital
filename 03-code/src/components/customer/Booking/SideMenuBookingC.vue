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
            <span slot="title">{{$t('sideMenu.unassigned')}}</span>
        </el-menu-item>
        <el-menu-item index="2">
            <i class="el-icon-menu"></i>
            <span slot="title">{{$t('sideMenu.assigned')}}</span>
        </el-menu-item>
    </el-menu>
</template>

<script>
    export default {
        name: 'SideMenuBookingC',
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