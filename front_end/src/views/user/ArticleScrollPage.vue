<template>
    <scroll-page :loading="loading" :offset="offset" :no-data="noData" v-on:load="load">
        <article-item v-for="a in articles" :key="a.id" v-bind="a"></article-item>
    </scroll-page>
</template>

<script>
    import ArticleItem from '@/views/user/ArticleItem'
    import ScrollPage from '@/views/components/scrollpage'
    import {mapActions, mapGetters} from "vuex";

    export default {
        name: "ArticleScrollPage",
        computed:{
            ...mapGetters(['userInfo',"articleList"]),
        },
        async mounted() {
            this.getArticleList();
            this.getUserInfo();
            this.loadBlog();
        },
        props: {
            offset: {
                type: Number,
                default: 100
            },
            page: {
                type: Object,
                default() {
                    return {}
                }
            },
            query: {
                type: Object,
                default() {
                    return {}
                }
            },
            personal:Boolean,
        },
        data() {
            return {
                loading: false,
                noData: false,
                innerPage: {
                    pageSize: 5,
                    pageNumber: 1,
                    name: 'a.createDate',
                    sort: 'desc'
                },
                articles: []
            }
        },
        methods: {
            ...mapActions(["getArticleList","getUserInfo"]),
            loadBlog(){
                if(this.personal){
                    console.log('user article loading')
                    for(var i=0;i<this.articleList.length;i++){
                        if(this.articleList[i].userId===this.userInfo.id) this.articles.push(this.articleList[i])
                    }
                }
                else{
                    this.articles = this.articleList
                    console.log('all article loading')
                }

                if(this.articles.length === 0) this.noData =true
            }
        },
        components: {
            'article-item': ArticleItem,
            'scroll-page': ScrollPage
        }

    }
</script>

<style scoped>
    .el-card {
        border-radius: 0;
    }

    .el-card:not(:first-child) {
        margin-top: 10px;

    }
</style>
