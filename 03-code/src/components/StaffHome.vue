<template>
    <div>
<!--        <nav-menu></nav-menu>-->
        <Header/>
        <router-view/>
    </div>
</template>

<script>
    import Header from '@/components/Header'
    export default {
        name: "StaffHome",
        components: {
            Header: Header
        }
    }
</script>

<style scoped>

</style>