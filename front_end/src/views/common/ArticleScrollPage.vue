<template>
    <scroll-page :loading="loading" :offset="offset" :no-data="noData" v-on:load="load">
        <article-item v-for="a in articles" :key="a.id" v-bind="a"></article-item>
    </scroll-page>
</template>

<script>
</script>

<style scoped>
    .el-card {
        border-radius: 0;
    }

    .el-card:not(:first-child) {
        margin-top: 10px;

    }
</style>
