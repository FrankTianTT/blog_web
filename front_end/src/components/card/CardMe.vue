<template>
    <el-card>
        <h1 class="me-author-name">{{userName}}</h1>
        <div class="me-author-description">
            <div>联系我：{{qqNumber}}</div>
            <div>关于我：{{profile}}</div>
        </div>
    </el-card>

</template>

<script>
    import { mapGetters, mapActions, mapMutations } from "vuex";
    export default {
        name: 'CardMe',
        created() {
            this.showUserInfo()
        },
        data() {
            return {
                userName:'',
                qqNumber:'',
                profile:''
            }
        },
        computed:{
            ...mapGetters(['userInfo','userBlogList']),
        },
        methods: {
            showUserInfo() {
                this.userName = this.userInfo.userName
                this.qqNumber = this.userInfo.qqNumber
                this.profile = this.userInfo.profile
            }
        }
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
