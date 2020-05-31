<template>
    <div>
        <base-header></base-header>
        <div>home</div>
        <el-main class="me-articles">
            <article-scroll-page></article-scroll-page>
        </el-main>
    </div>
</template>
<script>
import BaseHeader from "@/components/BaseHeader"
import ArticleScrollPage from '@/views/common/ArticleScrollPage'
import { mapGetters, mapActions, mapMutations } from "vuex";
export default {
    name:"Home",
    created() {
        this.lodeHomePage()
    },
    computed:{
        ...mapGetters(['articleList']),
    },
    components:{
        BaseHeader,
        ArticleScrollPage
    },
    methods:{
        lodeHomePage(){
            console.log(this.articleList)
            console.log('homepadeloding')
        }
    },
    data(){
        return {}
    }
}
</script>


