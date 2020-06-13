<template>
    <el-container class="el-container">
      <el-main>
          <div class="me-month-title">{{tip}}</div>
        <article-item v-for="a in userArticleList" :key="a.id" v-bind="a"></article-item>
      </el-main>
      <el-aside>
        <card-me class="me-area"></card-me>
      </el-aside>

    </el-container>
</template>

<script>
import CardMe from '@/views/user/CardMe'
import ArticleItem from '@/views/user/components/ArticleItem'
import { mapGetters, mapActions} from "vuex";

export default {
    name:'BlogHome',
    data() {
      return {
          tip:String
      };
    },
    mounted() {
        this.loadTip()
    },
    computed:{
      ...mapGetters(["userArticleList"]),
    },
    methods: {
        loadTip(){
            if(this.userArticleList.length===0){
                this.tip = "你暂时还有没写过文章，快去写一篇吧！"
            }
            else{
                this.tip = ''
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
  .me-area{
    background-color: #fff;
  }
</style>
