<template>
    <el-card>
        <h1 class="me-author-name">{{this.userInfo.userName}}</h1>
        <div class="me-author-description">
            <div>联系我：{{this.userInfo.qqNumber}}</div>
            <div>关于我：{{this.userInfo.profile}}</div>
        </div>
    </el-card>

</template>

<script>
    import { mapGetters, mapActions, mapMutations } from "vuex";
    export default {
        name: 'CardMe',
        computed:{
            ...mapGetters(['userInfo']),
        },
    }
</script>


<style scoped>
    .me-author-name {
        text-align: center;
        font-size: 30px;
        border-bottom: 1px solid #5FB878;
    }

    .me-author-description {
        padding: 8px 0;
    }

    .me-icon-job {
        padding-left: 16px;
    }

    .me-author-tool {
        text-align: center;
        padding-top: 10px;
    }

    .me-author-tool i {
        cursor: pointer;
        padding: 4px 10px;
        font-size: 30px;
    }
</style>
