<template>
    <el-card>
        <h1 class="me-author-name">{{this.userInfo.userName}}</h1>
        <div class="me-author-description">
            <div>联系我</div>
            <div>QQ: {{this.qqNumber}}</div>
            <div>关于我</div>
            <div>{{this.profile}}</div>
        </div>
    </el-card>

</template>

<script>
    import { mapGetters, mapActions } from "vuex";
    export default {
        name: 'CardMe',
        data() {
            return {
                qqNumber,
                profile
            };
        },
        computed:{
            ...mapGetters(['userInfo']),
        },
        async mounted() {
            this.getUserInfo();
        },
        created() {
            this.showUserInfo()
        },
        methods: {
            ...mapActions(["getUserInfo"]),
            showUserInfo(){
                if (this.userInfo.qqNumber!=null) this.qqNumber = this.userInfo.qqNumber
                else this.qqNumber = "暂时没有联系方式"
                if (this.userInfo.profile!=null) this.profile = this.userInfo.profile
                else this.profile = "暂时没有介绍"
            }
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
