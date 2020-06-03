<template>
    <el-container>
        <el-main>
            <div class="me-month-title">{{currentArchive}}</div>
            <article-item v-for="a in article" :key="a.id" v-bind="a"></article-item>
        </el-main>
        <el-aside>
            <ul class="me-month-list">
                <li v-for="a in archives" :key="a.year + a.month" class="me-month-item">
                    <el-badge :value="a.count">
                        <el-button @click="changeArchive(a.year, a.month)" size="small">{{a.year +'年' + a.month + '月'}}
                        </el-button>
                    </el-badge>
                </li>
            </ul>

        </el-aside>
    </el-container>
</template>

<script>
import ArticleItem from '@/views/user/ArticleItem'
import { mapGetters, mapActions} from "vuex";
export default {
    name: "BlogArchive",
    computed:{
        ...mapGetters(["userArticleList"]),
    },
    async mounted() {
        this.getUserArticle();
    },
    components: {
        'article-item': ArticleItem
    },
    created() {
        this.initPage();
        this.calArchivesList()
    },
    data() {
        return {
            currentArchive:String,
            article:[],
            archives: []
        }
    },
    methods: {
        ...mapActions(["getUserArticle"]),
        initPage(){
            if(this.userArticleList.length===0){
                this.currentArchive = "你暂时还有没写过文章，快去写一篇吧！"
            }
            else {
                this.article = this.userArticleList
                this.currentArchive = "全部"
            }
        },
        insertArchive(year,month) {
            if(this.archives.length===0){
                this.archives.push({'year':year,'month':month,'count':1})
                return
            }
            else if(year+month*12<this.archives[0].year + this.archives[0].month*12){
                this.archives.unshift({'year':year,'month':month,'count':1})
                return
            }
            else if(year+month*12>this.archives[this.archives.length-1].year + this.archives[this.archives.length-1].month*12){
                this.archives.push({'year':year,'month':month,'count':1})
                return
            }
            for(let i=0;i<this.archives.length;i++){
                if(year+month*12===this.archives[i].year + this.archives[i].month*12){
                    this.archives[i].count += 1
                    return
                }
                else if(year+month*12<this.archives[i].year + this.archives[i].month*12){
                    this.archives.splice(i,0,{'year':year,'month':month,'count':1})
                    return
                }
            }
        },
        calArchivesList(){
            for(let i=0;i<this.userArticleList.length;i++){
                this.insertArchive(Number(this.userArticleList[i].createDate.split('-')[0]),Number(this.userArticleList[i].createDate.split('-')[1]))
            }
            console.log("archives",this.archives)
        },
        changeArchive(year,month) {
            this.currentArchive = String(year) + "年" + String(month) + "月"
            this.article = []
            for(let i=0;i<this.userArticleList.length;i++){
                if(Number(this.userArticleList[i].createDate.split('-')[0])===year && Number(this.userArticleList[i].createDate.split('-')[1])===month){
                    this.article.push(this.userArticleList[i])
                }
            }
        }
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

.me-area{
    background-color: #fff;
}

.me-month-list {
    margin-top: 10px;
    margin-bottom: 10px;
    text-align: center;
    list-style-type: none;
}

.me-month-item {
    margin-top: 18px;
    padding: 4px;
    font-size: 18px;
    color: #5FB878;
}

.me-order-list {
    float: right;
}

.me-month-title {
    margin-left: 4px;
    margin-bottom: 12px;
}
</style>