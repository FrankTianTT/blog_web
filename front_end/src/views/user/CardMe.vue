<template>
    <el-card>
        <h1 class="me-author-name">{{this.userInfo.userName}}</h1>
        <div class="me-author-description">
            <div>联系我</div>
            <div>QQ: {{userInfo.qqNumber==null?"暂时没有联系方式":userInfo.qqNumber}}</div>
            <div>关于我</div>
            <div>{{userInfo.profile==null?"暂时没有介绍":userInfo.profile}}</div>
            <button @click="updateMyInfo()">更改个人信息</button>
        </div>
        <update-user-info-modal></update-user-info-modal>
    </el-card>
</template>

<script>
    import updateUserInfoModal from './components/updateUserInfoModal'
    import {mapGetters, mapActions, mapMutations} from "vuex";
    import UpdateUserInfoModal from "./components/updateUserInfoModal";
    export default {
        name: 'CardMe',
        components: {UpdateUserInfoModal},
        data() {
            return {
            };
        },
        computed:{
            ...mapGetters(['userInfo','updateUserInfoModalVisible']),
        },
        methods:{
            ...mapMutations([
                'set_updateUserInfoModalVisible'
            ]),
            updateMyInfo(){
                this.set_updateUserInfoModalVisible(true)
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
