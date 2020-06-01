<template>
    <el-card class="me-area" :body-style="{ padding: '16px' }">
        <div class="me-article-header">
            <a @click="view(id)" class="me-article-title">{{title}}</a>
            <span class="me-pull-right me-article-count">
                {{commentCounts}}评论
            </span>
            <span class="me-pull-right me-article-count">
                {{viewTimes}}查看
            </span>
        </div>

            <div class="me-artile-description">
            {{summary}}
        </div>
        <div class="me-article-footer">
	  	    <span class="me-article-author">
	    	<i class="me-icon-author"></i>&nbsp;作者：{{author}}
	    </span>
            <span class="me-pull-right me-article-count">
	    	<i class="el-icon-time"></i>&nbsp;{{createDate}}
	    </span>
            <span class="me-pull-left me-article-count">
            {{label}}
	    </span>

        </div>
    </el-card>
</template>

<script>
    import { mapGetters, mapActions } from "vuex";
    export default {
        name:"ArticleItem",
        created() {
          this.getAuthor()
        },
        async mounted() {
            this.getUserList();
            this.getUserInfo();
            this.getAuthor();
            this.calCommentCounts();
            this.setSummary()
        },
        computed:{
            ...mapGetters(['userList',"articleList"]),
        },
        props:{
            id:Number,
            userId:Number,
            categoryId:Number,
            title:String,
            content:String,
            label:String,
            viewTimes: Number,
            createDate: String,
            comments:Array
        },
        data() {
            return {
                author:String,
                commentCounts:Number,
                summary:String
            }
        },
        methods: {
            ...mapActions(["getUserList","getUserInfo"]),
            view(id) {
                this.$router.push({path: `/view/${id}`})
            },
            getAuthor(){
                for(let i=0;i<this.userList.length;i++){
                    if(this.userList[i].id===this.userId){
                        this.author=this.userList[i].userName
                        break
                    }
                }
            },
            calCommentCounts(){
                if (this.comments===null)this.commentCounts = 0
                else this.commentCounts = this.comments.length
            },
            setSummary(){
                this.summary = this.content.substring(0,20) + '...'
            }
        }
    }
</script>

<style scoped>

    .me-article-header {
        /*padding: 10px 18px;*/
        padding-bottom: 10px;
    }

    .me-article-title {
        font-weight: 600;
    }

    .me-article-icon {
        padding: 3px 8px;
    }

    .me-article-count {
        color: #a6a6a6;
        padding-left: 14px;
        font-size: 13px;
    }

    .me-pull-right {
        float: right;
    }
    .me-pull-left {
        float: left;
    }

    .me-artile-description {
        font-size: 13px;
        line-height: 24px;
        margin-bottom: 10px;
    }

    .me-article-author {
        color: #a6a6a6;
        padding-right: 18px;
        font-size: 13px;
    }

    .el-tag {
        margin-left: 6px;
    }

</style>
