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
            <span slot="title">{{$t('sideMenu.waitingDistribution')}}
</span>
        </el-menu-item>
        <el-menu-item index="2">
            <i class="el-icon-menu"></i>
            <span slot="title">{{$t('sideMenu.waitingOperation')}}</span>
        </el-menu-item>
        <el-menu-item index="3">
            <i class="el-icon-menu"></i>
            <span slot="title">{{$t('sideMenu.duringOperation')}}</span>
        </el-menu-item>
        <el-menu-item index="4">
            <i class="el-icon-menu"></i>
            <span slot="title">{{$t('sideMenu.waitingRelease')}}</span>
        </el-menu-item>
        <el-menu-item index="5">
            <i class="el-icon-menu"></i>
            <span slot="title">{{$t('sideMenu.noAppointment')}}</span>
        </el-menu-item>
    </el-menu>
</template>

<script>
    export default {
        name: 'SideMenuPet'
    }
</script>

<style scoped>
    .categories {
        position: fixed;
        margin-left: 50%;
        left: -730px;
        top: 100px;
        width: 210px;
    }
</style>