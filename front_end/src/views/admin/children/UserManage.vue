<template>
  <div>
    <el-card>
      <el-row>
        <!-- <el-col :span="7">
          <el-input placeholder="请输入内容" v-model="search">
            <el-button slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>-->
        <el-col :span="4" :offset="20">
          <el-button type="success" @click="handleAdd()">添加用户</el-button>
        </el-col>
      </el-row>
      <el-table :data="userList" border style="width: 100%">
        <el-table-column prop="id" label="id" width="60px"></el-table-column>
        <el-table-column label="邮箱" width="200px">
          <template slot-scope="scope">
            <el-popover trigger="hover" placement="top">
              <p>用户名: {{ scope.row.userName }}</p>
              <p>QQ: {{ scope.row.qqNumber?scope.row.qqNumber:"无" }}</p>
              <div slot="reference" class="name-wrapper">
                <el-tag size="medium">{{ scope.row.email }}</el-tag>
              </div>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column prop="userType" label="用户类型"></el-table-column>
        <el-table-column prop="password" label="密码"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <UserModalAdd />
    <UserModalEdit />
  </div>
</template>
<script>
import { mapGetters, mapActions, mapMutations } from "vuex";
import UserModalAdd from "../components/UserModalAdd";
import UserModalEdit from "../components/UserModalEdit"

export default {
  name: "UserManage",
  components: {
    UserModalAdd,
    UserModalEdit
  },
  data() {
    return {
    };
  },
  computed: {
    ...mapGetters(["userList"])
  },
  async mounted() {
    await this.getUserList();
  },
  methods: {
    ...mapActions(["getUserList","deleteUser"]),
    ...mapMutations(["set_addUserModalVisible","set_currentUserInfo","set_editUserModalVisible"]),
    handleAdd() {
      this.set_addUserModalVisible(true);
    },
    handleEdit(index, row) {
      console.log(row)
      this.set_currentUserInfo(row)
      this.set_editUserModalVisible(true)
    },
    handleDelete(index, row) {
      console.log(index);
      console.log(row);
      this.deleteUser({id:row.id})
    }
  }
};
</script>

<style scoped>
.el-row {
  padding:10px;
}
</style>