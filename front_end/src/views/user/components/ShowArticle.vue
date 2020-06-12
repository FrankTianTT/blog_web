<template>
    <div class="me-view-body">
        <el-container class="me-view-container">
            <el-main>
                <div>
                    <h1 class="me-view-title">{{title}}</h1>
                    <div class="me-view-author">
                        <a class="">
                            <img class="me-view-picture" :src = "authorImgSrc"></img>
                        </a>
                        <div class="me-view-info">
                            <span>{{authorName}}</span>
                            <div class="me-view-meta">
                                时间{{createDate}} 评论{{this.comments===null?0:comments.length}}
                            </div>

                        </div>
                        <el-button
                                v-if="String(this.authorId) === String(this.userId)"
                                @click="editArticle()"
                                size="mini"
                                round
                                icon="el-icon-edit">编辑</el-button>
                    </div>
                    <div class="me-view-content">
                        <markdown-editor :editor=editor></markdown-editor>
                    </div>

                    <div class="me-view-end">
                        <el-alert
                                title="文章End..."
                                type="success"
                                center
                                :closable="false">
                        </el-alert>
                    </div>

                </div>
            </el-main>
        </el-container>
    </div>
</template>

<script>
import { mapGetters, mapActions} from "vuex";
import MarkdownEditor from '@/views/user/components/MarkdownEditor'
import authorImg from '@/assets/img/author_img.jpeg'
import defaultAvatar from '@/assets/img/default_avatar.png'
export default {
    name: "ShowArticle",
    created() {
        this.initPage()
    },
    data(){
        return{
            title:"",
            authorName:"",
            authorId:0,
            label:"",
            comments:[],
            authorImgSrc:authorImg,
            createDate:"",
            editor: {
                value: '',
                toolbarsFlag: false,
                subfield: false,
                defaultOpen: 'preview'
            },
            avatar:"default_avatar"
        };
    },
    computed:{
        ...mapGetters(["articleList","userId"]),
    },
    methods: {
        initPage(){
            this.pageId = this.$route.query.id
            for(let i=0;i<this.articleList.length;i++){
                if (String(this.articleList[i].id)===String(this.pageId)){
                    this.editor.value = this.articleList[i].content
                    this.title = this.articleList[i].title
                    this.authorName = this.articleList[i].userName
                    this.label = this.articleList[i].label
                    this.comments = this.articleList[i].comments
                    this.createDate = this.articleList[i].createDate
                    this.authorId = this.articleList[i].userId
                    console.log("get article success")
                    break
                }
            }
        },
        editArticle() {

        },
    },
    components:{
        'markdown-editor': MarkdownEditor,
    }

}
</script>

<style>
    .me-view-body {
        margin: 50px auto 140px;
    }

    .me-view-container {
        margin: 0 auto;
    }

    .el-main {
        overflow: hidden;
    }

    .me-view-title {
        font-size: 34px;
        font-weight: 700;
        line-height: 1.3;
    }

    .me-view-author {
        /*margin: 30px 0;*/
        margin-top: 30px;
        vertical-align: middle;
    }

    .me-view-picture {
        width: 40px;
        height: 40px;
        border: 1px solid #ddd;
        border-radius: 50%;
        vertical-align: middle;
        background-color: #5fb878;
    }

    .me-view-info {
        display: inline-block;
        vertical-align: middle;
        margin-left: 8px;
    }

    .me-view-meta {
        font-size: 12px;
        color: #969696;
    }

    .me-view-end {
        margin-top: 20px;
    }

    me-view-content{
        vertical-align: middle;
    }
    .me-view-tag {
        margin-top: 20px;
        padding-left: 6px;
        border-left: 4px solid #c5cac3;
    }

    .me-view-tag-item {
        margin: 0 4px;
    }

    .me-view-comment {
        margin-top: 60px;
    }

    .me-view-comment-title {
        font-weight: 600;
        border-bottom: 1px solid #f0f0f0;
        padding-bottom: 20px;
    }

    .me-view-comment-write {
        margin-top: 20px;
    }

    .me-view-comment-text {
        font-size: 16px;
    }

    .v-show-content {
        padding: 8px 25px 15px 0px !important;
    }

    .v-note-wrapper .v-note-panel {
        box-shadow: none !important;
    }

    .v-note-wrapper .v-note-panel .v-note-show .v-show-content, .v-note-wrapper .v-note-panel .v-note-show .v-show-content-html {
        background: #fff !important;
    }


</style>