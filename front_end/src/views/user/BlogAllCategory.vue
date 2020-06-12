<template>
        <el-container class="me-allct-container">
            <el-main>
                <li v-for="c in categorys" @click="view(c.categorysId)" :key="c.categorysId" class="me-allct-item">
                    <div class="me-allct-content">
                        <a class="me-allct-info">
                            <img class="me-allct-img" :src="c.img"/>
                            <h4 class="me-allct-name">{{c.categoryname}}</h4>
                        </a>

                        <div class="me-allct-meta">
                            <span>{{c.articles}} 文章</span>
                        </div>
                    </div>
                </li>
            </el-main>
        </el-container>
</template>

<script>
import img1 from '@/assets/img/category/1.jpeg'
import img2 from '@/assets/img/category/2.jpeg'
import img3 from '@/assets/img/category/3.jpeg'
import img4 from '@/assets/img/category/4.jpeg'
import img5 from '@/assets/img/category/5.jpeg'
import img6 from '@/assets/img/category/6.jpeg'
import img7 from '@/assets/img/category/7.jpeg'
import img8 from '@/assets/img/category/8.jpeg'
import {mapActions, mapGetters} from "vuex";

export default {
    name: 'BlogAllCategory',
    created() {
        this.loadCategorys();
    },
    data() {
        return {
            categorys: [],
            tags: []
        }
    },
    computed:{
        ...mapGetters(["userArticleList"]),
    },
    methods: {
        loadCategorys(){
            let c = ['日记', '技术','学习','美食','旅游','生活','时尚','文学']
            let img = [img1,img2,img3,img4,img5,img6,img7,img8]
            for(let i=0;i<c.length;i++){
                this.categorys.push({'categorysId':i+1,'img':img[i],'categoryname':c[i],'articles':0})
            }
            for(let i=0;i<this.userArticleList.length;i++){
                this.categorys[this.userArticleList[i].categoryId-1].articles += 1
            }
        },
        view(categorysId) {
            this.$router.push({path: `category`,query: {categorysId}})
        },
    },
}
</script>

<style>
    .el-main {
    }

    .me-allct-body {
        margin: 60px auto 140px;
    }

    .me-allct-container {
        width: 1250px;
    }

    .me-allct-items {
        padding-top: 2rem;
    }

    .me-allct-item {
        width: 25%;
        display: inline-block;
        margin-bottom: 2.4rem;
        padding: 0 .7rem;
        box-sizing: border-box;
    }

    .me-allct-content {
        display: inline-block;
        width: 100%;
        background-color: #fff;
        border: 1px solid #f1f1f1;
        transition: border-color .3s;
        text-align: center;
        padding: 1.5rem 0;
    }

    .me-allct-info {
        cursor: pointer;
    }

    .me-allct-img {
        margin: -40px 0 10px;
        width: 60px;
        height: 60px;
        vertical-align: middle;

    }

    .me-allct-description {
        min-height: 50px;
        font-size: 13px;
        line-height: 25px;
    }

    .me-allct-meta {
        font-size: 12px;
        color: #969696;
    }
</style>
