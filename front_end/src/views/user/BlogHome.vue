
<template>
  <div v-title data-title="BlogHome">
    <el-container>

      <el-main class="me-articles">
        <article-scroll-page v-bind:personal="true"></article-scroll-page>
      </el-main>
      <el-aside>
        <card-me class="me-area"></card-me>
      </el-aside>

    </el-container>
  </div>
</template>

<script>
import CardMe from '@/views/user/CardMe'
import ArticleItem from '@/views/user/ArticleItem'
import ArticleScrollPage from '@/views/user/ArticleScrollPage'
import { mapGetters, mapActions} from "vuex";

export default {
    name:'BlogHome',
    data() {
      return {};
    },
    computed:{
      ...mapGetters(['userInfo',"articleList"]),
    },
    async mounted() {
      this.getArticleList();
      this.getUserInfo();
      this.loadUserBlog();
    },
    methods: {
      ...mapActions(["getArticleList","getUserInfo"]),
      loadUserBlog() {
        console.log('bloghome load')
      }
    },
      components: {
        'card-me': CardMe,
        'article-item': ArticleItem,
        'article-scroll-page':ArticleScrollPage,
      }
}
</script>



<style scoped>

  .el-container {
    margin-left: 100px;
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
  .me-area{
    background-color: #fff;
  }
</style>
