<template>
  <div>
    <el-header>
      <div class="name">博客后台管理系统</div>
      <el-button @click="handleLogout()">退出登陆</el-button>
    </el-header>
    <el-container>
      <el-aside width="200px">
        <el-menu
          default-active="1"
          router
        >
          <el-menu-item index="userManage">
            <i class="el-icon-user"></i>
            <span slot="title">用户管理</span>
          </el-menu-item>
          <el-menu-item index="articleManage">
            <i class="el-icon-document"></i>
            <span slot="title">文章管理</span>
          </el-menu-item>
          <el-menu-item index="commentManage">
            <i class="el-icon-notebook-1"></i>
            <span slot="title">评论管理</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </div>
</template>
<script>
import { mapGetters, mapActions, mapMutations } from "vuex";

export default {
  name: "admin",
  data() {
    return {};
  },
  methods: {
    ...mapActions(["logout"]),
    handleLogout() {
      this.logout();
      this.$router.push({ name: "Home" });
    }
  }
};
</script>
<style scoped>
.el-header {
  background-color: #5fb878;
  display: flex;
  justify-content: space-between;
  align-items: center;
  color: #ffffff;
}
.el-header .name {
  display: flex;
  align-content: center;
  font-size: 20px;
}
.el-aside {
  background-color: #fff;
}
.el-main {
  background-color: #f5f5f5;
}
.el-container{
  height:-webkit-fill-available;
}
</style>