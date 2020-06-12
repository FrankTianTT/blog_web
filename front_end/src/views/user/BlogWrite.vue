<template>
  <div id="write">
    <el-main>
      <div class="write-title">
        <el-input
          type="textarea"
          autosize
          v-model="articleForm.title"
          placeholder="请输入标题"
          class="me-write-input"
        ></el-input>
      </div>
      <mavon-editor
        v-model="content"
        ref="md"
        @save="save"
        @change="change"
        @imgAdd="$imgAdd"
        style="min-height: 600px"
      ></mavon-editor>
      <div class="select-container">
        <el-select v-model="articleForm.categoryId" placeholder="请选择文章分类">
            <el-option
              v-for="item in categorys"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
          <el-select
            v-model="articleForm.labels"
            multiple
            filterable
            allow-create
            default-first-option
            placeholder="请选择文章标签"
          >
            <el-option
              v-for="item in tags"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
      </div>
          
      <div class="button-container">
        <el-popconfirm
          confirmButtonText="确定"
          cancelButtonText="取消"
          icon="el-icon-info"
          iconColor="red"
          title="请确认保存后发布"
          @onConfirm="handleConfirm"
        >
          <el-button slot="reference">发布文章</el-button>
        </el-popconfirm>
      </div>
    </el-main>
  </div>
</template>
<script>
import BaseHeader from "@/components/BaseHeader";
import { mavonEditor } from "mavon-editor";
require("mavon-editor/dist/css/index.css");
import { Message } from "element-ui";
import { mapGetters, mapActions, mapMutations } from "vuex";

export default {
  name: "BlogWrite",
  components: {
    "mavon-editor": mavonEditor,
    "base-header": BaseHeader
  },
  data() {
    return {
      content: "", // 输入的markdown
      html: "", // 转成的html
      articleForm: {
        title: "",
        content: "",
        categoryId: "",
        labels: [],
        createDate: "",
      },
      categorys: [
        { value: 1, label: "日记" },
        { value: 2, label: "技术" },
        { value: 3, label: "学习" },
        { value: 4, label: "美食" },
        { value: 5, label: "旅游" },
        { value: 6, label: "生活" },
        { value: 7, label: "时尚" },
        { value: 8, label: "文学" },
      ],
      tags: [
        { value: "标签1", label: "标签1" },
        { value: "标签2", label: "标签2" }
      ]
    };
  },
  computed:{
    ...mapGetters(['userId',"userName"])
  },
  methods: {
    ...mapActions(['addArticle']),
    change(value, render) {
      //实时获取转成html的数据
      this.html = render;
      console.log(this.html);
    },
    // 将图片上传到服务器，返回地址替换到md中
    $imgAdd(pos, $file) {
      Message.error("暂时无法上传图片");
    },
    save(value, render) {
      console.log("以下是value");
      console.log(value);
      console.log("以下是render");
      console.log(render);
      this.articleForm.content = this.content
    },
    handleConfirm() {
      console.log(this.articleForm);
      if (!this.articleForm.title) {
        Message.error("文章标题不能为空");
        return;
      }
      if (!this.articleForm.content) {
        Message.error("文章内容不能为空，请确认保存");
        return;
      }
      if (!this.articleForm.categoryId) {
        Message.error("请添加文章分类");
        return;
      }
      // const create_date = new Date().toLocaleDateString().replace(/\//g,"-");
      const data = {
        userId:this.userId,
        categoryId:this.articleForm.categoryId,
        title:this.articleForm.title,
        content:this.articleForm.content,
        label:this.articleForm.labels.join(" "),
        userName:this.userName,
        categoryName:this.categorys[this.articleForm.categoryId-1].label
      };
      console.log(data);
      this.addArticle(data);
    }
  }
};
</script>
<style scoped>
.write-title {
  margin-bottom: 30px;
}
.button-container {
  margin-top: 15px;
}
.select-container{
  margin:10px;
}
</style>