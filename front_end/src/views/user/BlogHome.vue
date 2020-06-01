
<template>
  <div v-title data-title="BlogHome">
    <el-container>
      <el-main class="me-articles">
        <article-item v-for="a in articles" :key="a.id" v-bind="a"></article-item>
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
import { mapGetters, mapActions} from "vuex";

export default {
    name:'BlogHome',
    data() {
      return {
        articles:[]
      };
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
        for(var i=0;i<this.articleList.length;i++){
          if(this.articleList[i].userId===this.userInfo.id) this.articles.push(this.articleList[i])
        }
      }
    },
      components: {
        'card-me': CardMe,
        'article-item': ArticleItem,
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
