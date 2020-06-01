<template>
  <div>
    <el-card>
      <el-table :data="articleList" border style="width: 100%">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form class="demo-table-expand">
              <el-form-item>
                <el-divider content-position="left">文章编号</el-divider>
                <span>{{ props.row.id }}</span>
              </el-form-item>
              <el-form-item>
                <el-divider content-position="left">作者编号</el-divider>
                <span>{{ props.row.userId }}</span>
              </el-form-item>
              <el-form-item>
                <el-divider content-position="left">分类编号</el-divider>
                <span>{{ props.row.categoryId }}</span>
              </el-form-item>
              <el-form-item>
                <el-divider content-position="left">文章标题</el-divider>
                <span>{{ props.row.title }}</span>
              </el-form-item>
              <el-form-item>
                <el-divider content-position="left">文章内容</el-divider>
                <span>{{ props.row.content }}</span>
              </el-form-item>
              <el-form-item >
                <el-divider content-position="left">发布时间</el-divider>
                <span>{{ props.row.createDate }}</span>
              </el-form-item>
              <el-form-item>
                <el-divider content-position="left">浏览次数</el-divider>
                <span>{{ props.row.viewTimes }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column label="文章编号" prop="id" width="100"></el-table-column>
        <el-table-column label="文章名称" prop="title"></el-table-column>
        <el-table-column
          prop="label"
          label="标签"
          width="300"
          :filters="[{ text: '村上春树', value: '村上春树' }, { text: '夏目漱石', value: '夏目漱石' }]"
          :filter-method="filterTag"
          filter-placement="bottom-end"
        >
          <template slot-scope="scope">
            <el-tag type="success" disable-transitions>{{scope.row.label}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
export default {
  name: "ArticleManage",
  data() {
    return {};
  },
  computed: {
    ...mapGetters(["articleList"])
  },
  async mounted() {
    this.getArticleList();
  },
  methods: {
    ...mapActions(["getArticleList"]),
    handleAdd() {
      console.log("add article");
    },
    handleDelete(index, row) {
      console.log("delete article");
      console.log(row);
    },
    filterTag(value, row) {
      return row.tag === value;
    }
  }
};
</script>
<style scoped>
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>