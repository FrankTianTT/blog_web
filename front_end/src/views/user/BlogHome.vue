<template>
  <div id="bloghome">
    <el-container>

      <el-main class="me-articles">
        <article-scroll-page></article-scroll-page>
      </el-main>
      <el-aside>
        <card-me class="me-area"></card-me>
      </el-aside>
    </el-container>
  </div>
</template>
<script>
import ArticleScrollPage from '@/views/common/ArticleScrollPage'
import CardMe from '@/components/card/CardMe'
export default {
    name:'BlogHome',
    components: {
      'card-me': CardMe,
      ArticleScrollPage
    }
}
</script>



<style scoped>

  .el-container {
    width: 960px;
  }

  .el-aside {
    margin-left: 20px;
    width: 260px;
  }

  .el-main {
    padding: 0px;
    line-height: 16px;
  }

  .el-card {
    border-radius: 0;
  }

  .el-card:not(:first-child) {
    margin-top: 20px;
  }
</style>
