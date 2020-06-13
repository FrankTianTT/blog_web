<template>
  <div>
    <el-card>
      <el-table :data="commentsList" border style="width: 100%">
        <el-table-column prop="id" label="id" width="60px"></el-table-column>
        <el-table-column prop="blogId" label="博客Id"></el-table-column>
        <el-table-column prop="userId" label="用户Id"></el-table-column>
        <el-table-column prop="datetime" label="日期"></el-table-column>
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form class="demo-table-expand">
              <el-form-item>
                <el-divider content-position="left">评论者名</el-divider>
                <span>{{ props.row.authorName }}</span>
              </el-form-item>
              <el-form-item>
                <el-divider content-position="left">内容</el-divider>
                <span>{{ props.row.content }}</span>
              </el-form-item>
            </el-form>
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
import { mapGetters, mapActions, mapMutations } from "vuex";

export default {
  name: "CommentManage",
  data() {
    return {};
  },
  computed: {
    ...mapGetters(["commentsList"])
  },
  async mounted() {
    await this.getAllComments();
  },
  methods:{
    ...mapActions(["getAllComments","deleteComments"]),
    handleDelete(index,row){
      console.log({id:row.id});
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