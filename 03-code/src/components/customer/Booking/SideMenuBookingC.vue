<template>
    <el-menu
            class="categories"
            default-active="0"

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
        name: 'SideMenuBookingC'
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