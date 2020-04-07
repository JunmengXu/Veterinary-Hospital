<template>
    <div>
        <CustomerHeader/>
        <router-view/>
    </div>

</template>

<script>
    import CustomerHeader from "./CustomerHeader";

    export default {
        name: "CustomerHome",
        components: {
            CustomerHeader: CustomerHeader
        }
    }
</script>

<style scoped>

</style>