<template >
  <div class="me-view-body">
    <el-container class="me-view-container">
      <el-main>
        <div>
          <h1 class="me-view-title">{{title}}</h1>
          <div class="me-view-author">
            <a class>
              <img class="me-view-picture" :src="authorImgSrc" />
            </a>
            <div class="me-view-info">
              <span>{{authorName}}</span>
              <div
                class="me-view-meta"
              >时间{{createDate}} 评论{{this.comments===null?0:comments.length}}</div>
            </div>
            <el-button
              v-if="String(this.authorId) === String(this.userId)"
              @click="editArticle()"
              size="mini"
              round
              icon="el-icon-edit"
            >编辑</el-button>
          </div>
          <div class="me-view-content">
            <markdown-editor :editor="editor"></markdown-editor>
          </div>

          <div class="me-view-end">
            <el-alert title="文章结束" type="success" center :closable="false"></el-alert>
          </div>

          <div class="me-view-tag">
            标签：
            <el-button
              @click="goLabel()"
              size="mini"
              type="primary"
              v-for="t in label"
              :key="t.id"
              round
              plain
            >{{t.labelname}}</el-button>
          </div>
          <div class="me-view-tag">
            文章分类：
            <el-button
              @click="goCategory(categoryId)"
              size="mini"
              type="primary"
              round
              plain
            >{{categoryName}}</el-button>
          </div>

          <div class="me-view-comment">
            <div class="me-view-comment-write">
              <el-row :gutter="20">
                <el-col :span="2">
                  <a class>
                    <img class="me-view-picture" :src="avatar" />
                  </a>
                </el-col>
                <el-col :span="22">
                  <el-input
                    type="textarea"
                    :autosize="{ minRows: 2}"
                    placeholder="你的评论..."
                    class="me-view-comment-text"
                    v-model="comment.content"
                    resize="none"
                  ></el-input>
                </el-col>
              </el-row>
              <el-button type="text" @click="publishComment()">提交</el-button>
            </div>

            <div class="me-view-comment-title">
              <span style="float: left;clear: left">{{this.comments===null?0:comments.length}} 条评论</span>
            </div>
            <div>
              <comment-item v-for="c in comments" :key="c.id" v-bind="c"></comment-item>
            </div>
          </div>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import { mapGetters, mapActions, mapMutations } from "vuex";
import MarkdownEditor from "@/views/user/components/MarkdownEditor";
import authorImg from "@/assets/img/author_img.jpeg";
import defaultAvatar from "@/assets/img/default_avatar.png";
import CommentItem from "./CommentItem";
import { Message } from "element-ui";
import moment from "moment";
export default {
  name: "ShowArticle",
  created() {
    this.initPage();
  },
  data() {
    return {
      title: "",
      authorName: "",
      authorId: 0,
      label: [],
      comments: [],
      categoryName: "",
      authorImgSrc: authorImg,
      createDate: "",
      categoryId: Number,
      editor: {
        value: "",
        toolbarsFlag: false,
        subfield: false,
        defaultOpen: "preview"
      },
      avatar: defaultAvatar,
      comment: {
        id: Number,
        content: ""
      }
    };
  },
  computed: {
    ...mapGetters(["articleList", "userId", "userInfo"])
  },
  methods: {
    ...mapMutations(["set_articleNeedModify", "set_articleFormNeedModify"]),
    ...mapActions(["addComment"]),
    initPage() {
      this.blogId = this.$route.query.id;
      for (let i = 0; i < this.articleList.length; i++) {
        if (String(this.articleList[i].id) === String(this.blogId)) {
          this.editor.value = this.articleList[i].content;
          this.title = this.articleList[i].title;
          this.authorName = this.articleList[i].userName;
          let labels = this.articleList[i].label.trim().split(" ");
          for (let i = 0; i < labels.length; i++) {
            this.label.push({ id: i, labelname: labels[i] });
          }
          let comments = this.articleList[i].comments;
          for (let i = 0; i < comments.length; i++) {
            this.comments.push({
              id: i + 1,
              authorName: comments[i].authorName,
              content: comments[i].content,
              datetime: comments[i].datetime
            });
          }
          this.createDate = this.articleList[i].createDate;
          this.authorId = this.articleList[i].userId;
          this.categoryName = this.articleList[i].categoryName;
          this.categoryId = this.articleList[i].categoryId;
          console.log("get article success");
          break;
        }
      }
      console.log(this.label);
      console.log(this.comments);
    },
    editArticle() {
      this.set_articleNeedModify(true);
      let tempLabel = "";
      for(let l of this.label){
        //   console.log(l);
        //   console.log(l["labelname"]);
        tempLabel+=(" "+l["labelname"]);
      }
      tempLabel = tempLabel.trim();
      const data ={
        id:this.blogId,
        userId: this.userId,
        categoryId: this.categoryId,
        title: this.title,
        content: this.editor.value,
        userName: this.authorName,
        categoryName: this.categoryName,
        label:tempLabel
      };
      console.log(data);
      this.set_articleFormNeedModify(data);
      this.$router.push({name:"BlogWrite"});
    },
    goCategory(categorysId) {
      this.$router.push({ path: `/user/category`, query: { categorysId } });
    },
    goLabel() {},
    publishComment() {
      console.log(this.comment);
      if (this.comment.content === "") {
        Message.error("评论不能为空");
      } else {
        const nowDate = new Date().getTime();
        const data = {
          blogId: this.blogId,
          authorName: this.userInfo.userName,
          content: this.comment.content,
          datetime: moment(nowDate).format("YYYY-MM-DD HH:mm:ss")
        };
        this.addComment(data);
        location.reload();
      }
    }
  },
  components: {
    "markdown-editor": MarkdownEditor,
    "comment-item": CommentItem
  }
};
</script>

<style>
.me-view-body {
  margin: 0px auto 140px;
  background-color: white;
}

.me-view-container {
  margin: 0 auto;
  width: 800px;
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
  margin: 10px auto;
}

.me-view-content {
  margin: 10px auto;
}
.me-view-tag {
  margin-top: 20px;
  float: left;
  border-left: 4px solid #c5cac3;
  clear: left;
}

.me-view-tag-item {
  margin: 0 4px;
}

.me-view-comment {
  margin-top: 150px;
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

.v-note-wrapper .v-note-panel .v-note-show .v-show-content,
.v-note-wrapper .v-note-panel .v-note-show .v-show-content-html {
  background: #fff !important;
}
</style>