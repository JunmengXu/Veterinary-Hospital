<template>
    <el-container>
        <el-aside>
            <switch></switch>
            <SideMenuPet></SideMenuPet>
        </el-aside>
        <el-main>
            <PetItem class="pet-area"></PetItem>
        </el-main>
    </el-container>
</template>

<script>
    import SideMenuPet from "./Pet/SideMenuPet";
    import PetItem from "./Pet/PetItem";

    export default {
        name: "CustomerPet",
        components: {SideMenuPet, PetItem},
        methods: {
            handleClick(row) {
                console.log(row);
            }
        }
    }
</script>

<style scoped>
    .demo-table-expand label {
        width: 90px;
        color: #99a9bf;
    }
    h2{
        text-align: center;
    }
    .pet-area{
        width: 1180px;
        margin-left: auto;
        margin-right: auto;
    }
</style>