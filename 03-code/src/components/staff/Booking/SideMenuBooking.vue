<template>
    <el-menu
            class="categories"
            default-active="0"
            @select="handleSelect"
            active-text-color="blue">
        <el-menu-item index="0">
            <i class="el-icon-menu"></i>
            <span slot="title">全部</span>
        </el-menu-item>
        <el-menu-item index="1">
            <i class="el-icon-menu"></i>
            <span slot="title">北京</span>
        </el-menu-item>
        <el-menu-item index="2">
            <i class="el-icon-menu"></i>
            <span slot="title">成都</span>
        </el-menu-item>
        <el-menu-item index="3">
            <i class="el-icon-menu"></i>
            <span slot="title">上海</span>
        </el-menu-item>
    </el-menu>
</template>

<script>
    export default {
        name: 'SideMenuBooking'
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