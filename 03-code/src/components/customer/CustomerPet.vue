<template>
    <el-container>
        <el-aside>
            <switch></switch>
            <SideMenuPet @indexSelect="listByPetStatus" ref="sideMenu"></SideMenuPet>
        </el-aside>
        <el-main>
            <PetItem class="pet-area" ref="petsArea"></PetItem>
        </el-main>
    </el-container>
</template>

<script>
    import SideMenuPet from "./Pet/SideMenuPet";
    import PetItem from "./Pet/PetItem";
    import store from "../../store";

    export default {
        name: "CustomerPet",
        components: {SideMenuPet, PetItem},
        methods: {
            handleClick(row) {
                console.log(row);
            },
            listByPetStatus () {
                var username = store.state.user.username
                if(this.$refs.sideMenu.select==0){
                    const _this = this
                    var url = 'http://localhost:8181/api/pets/' + username
                    this.$http.get(url).then(function (resp){
                        console.log(resp)
                        _this.$refs.petsArea.myPets = resp.data
                    })
                }else{
                    const _this = this
                    var t = this.$refs.sideMenu.select-1
                    var url1 = 'http://localhost:8181/api/pets/' + username + '/' + t
                    this.$http.get(url1).then(function (resp){
                        console.log(resp)
                        _this.$refs.petsArea.myPets = resp.data
                    })
                }
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