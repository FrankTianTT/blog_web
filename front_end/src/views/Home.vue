<template>
  <div>
    <base-header></base-header>
    <el-container class="el-container">
      <el-main>
        <article-item v-for="a in articleList" :key="a.id" v-bind="a"></article-item>
      </el-main>
      <el-aside>
        <div class="me-ad">广告区</div>
        <div class="me-ad">正在招商</div>
        <div class="me-ad">如有意请向站长联系</div>
      </el-aside>
    </el-container>
  </div>
</template>

<script>
import BaseHeader from "../components/BaseHeader";
import ArticleItem from "@/views/user/components/ArticleItem";
import { mapGetters, mapActions } from "vuex";
export default {
  name: "Home",
  data() {
    return {};
  },
  computed: {
    ...mapGetters(["articleList"]),
  },
  async mounted(){
    await this.getArticleList();
  },
  methods: {
    ...mapActions(["getArticleList"])
  },
  components: {
    "base-header": BaseHeader,
    "article-item": ArticleItem
  }
};
</script>


<style scoped>
.el-container {
  margin: 0 auto;
  width: 1200px;
}

.el-aside {
  margin-left: 0px;
  width: 26px;
}

.el-main {
  padding: 10px;
  line-height: 16px;
}

.el-card {
  border-radius: 0;
}

.el-card:not(:first-child) {
  margin-top: 20px;
}

.me-ad {
  font-weight: 600;
  font-size: 40px;
}
</style>

