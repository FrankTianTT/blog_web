<template>
  <div>
    <el-dialog title="用户信息" :visible.sync="editUserModalVisible" :before-close="handleClose">
      <el-form ref="userForm" :model="userForm" :rules="rules">
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="userForm.userName"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="userForm.password"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPassword">
          <el-input type="password" v-model="userForm.checkPassword"></el-input>
        </el-form-item>
        <el-form-item label="QQ号" prop="qqNumber">
          <el-input v-model.number="userForm.qqNumber"></el-input>
        </el-form-item>
        <el-form-item label="简介" prop="profile">
          <el-input type="textarea" v-model="userForm.profile"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleCancle()">取 消</el-button>
        <el-button type="primary" @click="handleOk()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { mapGetters, mapActions, mapMutations } from "vuex";

export default {
  name: "UserModalEdit",
  data() {
    var checkQQ = (rule, value, callback) => {
      if (!value && !Number.isInteger(value)) {
        callback(new Error("请输入数字值"));
      }
    };
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (value.length <= 6) {
          callback(new Error("输入密码长度必须大于6"));
        }
        if (this.userForm.checkPassword !== "") {
          this.$refs.userForm.validateField("checkPassword");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.userForm.password) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      userForm: {
        password: "",
        checkPassword: "",
        userName: "",
        qqNubmer: "",
        profile: ""
      },
      rules: {
        userName: [
          { required: true, message: "请输入用户名", trigger: "blur" }
        ],
        password: [
          {required: true, message: "请输入密码", trigger: "blur"},
          { validator: validatePass, trigger: "blur" }
        ],
        checkPassword: [
          {required: true, message: "请输入密码", trigger: "blur"},
          { validator: validatePass2, trigger: "blur" }
        ],
        qqNubmer: [{ validator: checkQQ, trigger: "blur" }]
      }
    };
  },
  computed: {
    ...mapGetters(["currentUserInfo", "editUserModalVisible"])
  },
  methods: {
    ...mapActions(["editUser"]),
    ...mapMutations(["set_editUserModalVisible", "set_currentUserInfo"]),
    handleCancle() {
      this.set_editUserModalVisible(false);
      this.resetForm();
    },
    handleOk() {
      this.$refs.userForm.validate(async validate => {
        if (!validate) return false;
        const data = {
          id: this.currentUserInfo.id,
          userName: this.userForm.userName,
          password: this.userForm.password,
          qqNumber: this.userForm.qqNumber,
          profile: this.userForm.profile
        };
        console.log(data);
        this.resetForm();
        await this.editUser(data);
      });
    },
    handleClose(done) {
      this.set_editUserModalVisible(false);
    },
    resetForm(){
      this.userForm = {
        password: "",
        checkPassword: "",
        userName: "",
        qqNubmer: "",
        profile: ""
      };
    }
  }
};
</script>
<style scoped>
</style>