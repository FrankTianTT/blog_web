<template>
  <el-row class="me-header">
    <el-col :span="13">
      <el-menu
        :router="true"
        menu-trigger="click"
        active-text-color="#5FB878"
        :default-active="activeIndex"
        mode="horizontal"
      >
        <el-menu-item index="/">首页</el-menu-item>
        <el-menu-item index="/user/blogAllCategory">文章分类</el-menu-item>
        <el-menu-item index="/user/archives">文章归档</el-menu-item>
        <el-menu-item index="/user/blogWrite">
          <i class="el-icon-edit"></i>写文章
        </el-menu-item>
      </el-menu>
    </el-col>
    <el-col :span="7">
      <el-input placeholder="请输入" v-model="search">
        <el-button slot="append" icon="el-icon-search" @click="serarchArticle"></el-button>
      </el-input>
    </el-col>

    <!-- <template v-else>
      <slot></slot>
    </template>-->

    <el-col :span="4">
      <el-menu :router="true" menu-trigger="click" mode="horizontal" active-text-color="#5FB878">
        <template v-if="!token">
          <el-menu-item index="/login">
            <el-button type="text">登录</el-button>
          </el-menu-item>
          <el-menu-item index="/register">
            <el-button type="text">注册</el-button>
          </el-menu-item>
        </template>

        <template v-else>
          <el-menu-item index="/user/blogHome">
              <avatar :username="userName" :size="40" style="float: left"></avatar>
            <span>{{userName}}</span>
          </el-menu-item>
          <el-menu-item @click="handleLogout">
            <i class="el-icon-back"></i>退出
          </el-menu-item>
        </template>
      </el-menu>
    </el-col>
  </el-row>
</template>

<script>
import { mapGetters, mapActions, mapMutations } from "vuex";
import Avatar from 'vue-avatar'
export default {
  name: "BaseHeader",
  props: {
    activeIndex: String
    // simple: {
    //   type: Boolean,
    //   default: false
    // }
  },
  components:{
    Avatar
  },
  data() {
    return {
      search: ""
    };
  },
  computed: {
    ...mapGetters(["token", "userName","articleList"])
    // hasToken() {
    //   return this.token === null;
    // }
  },
  methods: {
    ...mapActions(["logout"]),
    ...mapMutations(["set_articleList"]),
    handleLogout() {
      this.logout();
      this.$router.push({ name: "Home" });
    },
    serarchArticle(){
      if(this.search!==""){
        const article = this.articleList.filter(item=>item.title===this.search);
        console.log(article);
        this.set_articleList(article);
      }
    }
  }
};
</script>

<style>
.el-row {
  min-width: 100%;
  background: #fff;
  box-shadow: 0 2px 3px hsla(0, 0%, 7%, 0.1), 0 0 0 1px hsla(0, 0%, 7%, 0.1);
  margin-bottom: 20px;
  display: flex;
  align-items: center;
}
.me-title {
  margin-top: 10px;
  font-size: 24px;
}

.me-header-left {
  margin-top: 10px;
}

.me-title img {
  max-height: 2.4rem;
  max-width: 100%;
}
.vue-avatar--wrapper{
  position: relative;
  top:50%;
  transform: translateY(-50%);
}

</style>
