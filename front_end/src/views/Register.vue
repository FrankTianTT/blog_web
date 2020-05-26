<template>
  <div id="register">
    <div class="register-box register-box-radius">
      <h1>注册</h1>
      <div>
        <el-form ref="userForm" :model="userForm" :rules="rules">
          <el-form-item prop="email">
            <el-input placeholder="邮箱" v-model="userForm.email"></el-input>
          </el-form-item>
          <el-form-item prop="userName">
            <el-input placeholder="用户名" v-model="userForm.userName"></el-input>
          </el-form-item>
          <el-form-item  prop="password">
            <el-input placeholder="密码" type="password" v-model="userForm.password"></el-input>
          </el-form-item>
          <el-form-item prop="checkPassword">
            <el-input placeholder="确认密码" type="password" v-model="userForm.checkPassword"></el-input>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Register",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.ruleForm.checkPass !== "") {
          this.$refs.ruleForm.validateField("checkPassword");
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
        userType: 0
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
        userName: [
          { required: true, message: "请输入用户名", trigger: "blur" }
        ],
        password: [
          { validator: validatePass, trigger: "blur" }
        ],
        checkPassword: [
          { validator: validatePass2, trigger: "blur" }
        ]
      }
    };
  }
};
</script>

<style scoped>
.register-box {
  position: absolute;
  width: 400px;
  height: 500px;
  background-color: white;
  top:50%;
  left: 50%;
  transform: translate(-50%, -50%);
  padding:30px;
}
.register-box-radius {
  border-radius: 10px;
  box-shadow: 0px 0px 1px 1px rgba(161, 159, 159, 0.1);
}
.register-box h1 {
  text-align: center;
  font-size: 24px;
  margin-bottom: 20px;
  vertical-align: middle;
}
</style>