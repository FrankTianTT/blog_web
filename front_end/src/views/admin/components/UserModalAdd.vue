<template>
  <div>
    <el-dialog title="用户信息" :visible.sync="addUserModalVisible" :before-close="handleClose">
      <el-form ref="userForm" :model="userForm" :rules="rules">
        <el-form-item prop="email">
          <el-input placeholder="邮箱" v-model="userForm.email"></el-input>
        </el-form-item>
        <el-form-item prop="userName">
          <el-input placeholder="用户名" v-model="userForm.userName"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input placeholder="密码" type="password" v-model="userForm.password"></el-input>
        </el-form-item>
        <el-form-item prop="checkPassword">
          <el-input placeholder="确认密码" type="password" v-model="userForm.checkPassword"></el-input>
        </el-form-item>
        <el-form-item prop="userType">
          <el-select v-model="userForm.userType" placeholder="请选择账户类型">
            <el-option label="blogger" value="0"></el-option>
            <el-option label="admin" value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="qqNumber">
          <el-input placeholder="QQ号,选填" v-model.number="userForm.qqNumber"></el-input>
        </el-form-item>
        <el-form-item prop="profile">
          <el-input placeholder="个人简介，选填" type="textarea" v-model="userForm.profile"></el-input>
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
  name: "UserModalAdd",
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
        email: "",
        password: "",
        checkPassword: "",
        userName: "",
        qqNubmer: "",
        profile: "",
        userType: ""
      },
      rules: {
        email: [
          { required: true, message: "请输入邮箱地址", trigger: "blur" },
          {
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"]
          }
        ],
        userType: [
          { required: true, message: "请选择活动区域", trigger: "change" }
        ],
        userName: [
          { required: true, message: "请输入用户名", trigger: "blur" }
        ],
        password: [{ validator: validatePass, trigger: "blur" }],
        checkPassword: [{ validator: validatePass2, trigger: "blur" }],
        qqNubmer: [{ validator: checkQQ, trigger: "blur" }]
      }
    };
  },
  computed: {
    ...mapGetters(["addUserModalVisible"])
  },
  methods: {
    ...mapActions(["addUser"]),
    ...mapMutations(["set_addUserModalVisible", "set_addUserParams"]),
    handleCancle() {
      this.set_addUserModalVisible(false);
      this.resetUserForm();
    },
    handleOk() {
      this.$refs.userForm.validate(async validate => {
        if (!validate) return false;
        const data = {
          email: this.userForm.email,
          password: this.userForm.password,
          userName: this.userForm.userName,
          qqNubmer: String(this.userForm.qqNubmer),
          userType: this.userForm.userType,
          profile: this.userForm.profile
        };
        console.log(data);
        this.set_addUserParams(data);
        this.resetUserForm();
        await this.addUser();
      });
    },
    handleClose() {
      this.set_addUserModalVisible(false);
    },
    resetUserForm() {
      this.userForm = {
        email: "",
        password: "",
        checkPassword: "",
        userName: "",
        qqNubmer: "",
        profile: "",
        userType: ""
      }
    }
  }
};
</script>
<style scoped>
.el-select {
  width: 100%;
}
</style>