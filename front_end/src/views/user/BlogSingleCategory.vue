<template>
    <el-container>
        <el-main>
            <div class="me-month-title">{{tip}}</div>
            <article-item v-for="a in article" :key="a.id" v-bind="a"></article-item>
        </el-main>

    </el-container>
</template>

<script>
import ArticleItem from '@/views/user/ArticleItem'
import { mapGetters, mapActions} from "vuex";
export default {
    name: "BlogSingleCategory",
    created() {
        this.initPage()
    },
    methods: {
        initPage(){
            this.category = this.$route.query.id
            for(let i=0;i<this.userArticleList.length;i++){
                if(this.userArticleList[i].categoryId === this.category){
                    this.article.push(this.userArticleList[i])
                }
            }

            if(this.article.length===0){
                this.tip = "你暂时还有没写过文章，快去写一篇吧！"
            }
            else{
                this.tip = ''
            }
        }
    },
    computed:{
        ...mapGetters(["userArticleList"]),
    },
    data(){
        return{
            tip:String,
            article:[],
            category:Number
        }
    },
    watch: {
        '$route': 'getParams'
    },
    components: {
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
</style>