<template>
  <div id="write">
    <el-main class="me-write-main">
      <div class="me-write-title">
        <el-input
          resize="none"
          type="textarea"
          autosize
          v-model="articleForm.title"
          placeholder="请输入标题"
          class="me-write-input"
        ></el-input>
        <mavon-editor
          v-model="content"
          ref="md"
          @save="save"
          @change="change"
          @imgAdd="$imgAdd"
          style="min-height: 600px"
        ></mavon-editor>
      </div>
      <div id="placeholder" style="visibility: hidden;height: 89px;display: none;"></div>
    </el-main>
    <!-- 
      <el-dialog
        title="摘要 分类 标签"
        :visible.sync="publishVisible"
        :close-on-click-modal="false"
        custom-class="me-dialog"
      >
        <el-form :model="articleForm" ref="articleForm" :rules="rules">
          <el-form-item prop="summary">
            <el-input type="textarea" v-model="articleForm.summary" :rows="6" placeholder="请输入摘要"></el-input>
          </el-form-item>
          <el-form-item label="文章分类" prop="category">
            <el-select v-model="articleForm.category" value-key="id" placeholder="请选择文章分类">
              <el-option v-for="c in categorys" :key="c.id" :label="c.categoryname" :value="c"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="文章标签" prop="tags">
            <el-checkbox-group v-model="articleForm.tags">
              <el-checkbox v-for="t in tags" :key="t.id" :label="t.id" name="tags">{{t.tagname}}</el-checkbox>
            </el-checkbox-group>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="publishVisible = false">取 消</el-button>
          <el-button type="primary" @click="publish('articleForm')">发布</el-button>
        </div>
    </el-dialog>-->
  </div>
</template>
<script>
import BaseHeader from "@/components/BaseHeader";
import { mavonEditor } from "mavon-editor";
require("mavon-editor/dist/css/index.css");
import { Message } from "element-ui";

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
      publishVisible: false,
      articleForm: {
        title: ""
      }
    };
  },
  methods: {
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
    },
    publishShow() {},
    cancel() {}
  }
};
</script>
<style scoped>
.el-header {
  min-width: 100%;
  box-shadow: 0 2px 3px hsla(0, 0%, 7%, 0.1), 0 0 0 1px hsla(0, 0%, 7%, 0.1);
}

.me-write-info {
  line-height: 60px;
  font-size: 18px;
  font-weight: 600;
}

.me-write-btn {
  margin-top: 10px;
}

.me-write-box {
  max-width: 700px;
  margin: 80px auto 0;
}

.me-write-main {
  padding: 0;
}

.me-write-title {
}

.me-write-input textarea {
  font-size: 32px;
  font-weight: 600;
  height: 20px;
  border: none;
}

.me-write-editor {
  min-height: 650px !important;
}

.me-header-left {
  margin-top: 10px;
}

.me-title img {
  max-height: 2.4rem;
  max-width: 100%;
}

.me-write-toolbar-fixed {
  width: 700px !important;
  top: 60px;
}

.v-note-op {
  box-shadow: none !important;
}

.auto-textarea-input,
.auto-textarea-block {
  font-size: 18px !important;
}
</style>