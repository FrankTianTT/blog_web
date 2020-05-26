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
          <el-form-item prop="password">
            <el-input placeholder="密码" type="password" v-model="userForm.password"></el-input>
          </el-form-item>
          <el-form-item prop="checkPassword">
            <el-input placeholder="确认密码" type="password" v-model="userForm.checkPassword"></el-input>
          </el-form-item>
          <el-form-item prop="qqNumber">
            <el-input placeholder="QQ号,选填" v-model.number="userForm.qqNumber"></el-input>
          </el-form-item>
          <el-form-item prop="profile">
            <el-input placeholder="个人简介，选填" type="textarea" v-model="userForm.profile"></el-input>
          </el-form-item>
          <el-form-item size="small" class="register-button" :loading="loading">
            <el-button type="success" @click.native.prevent="handleRegister()">注册</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions, mapMutations } from "vuex";
import { message } from "element-ui";

export default {
  name: "Register",
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
        if(value.length<=6){
          callback(new Error("输入密码长度必须大于6"))
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
      loading: false,
      userForm: {
        email: "",
        password: "",
        checkPassword: "",
        userName: "",
        qqNubmer: "",
        profile: ""
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
        password: [{ validator: validatePass, trigger: "blur" }],
        checkPassword: [{ validator: validatePass2, trigger: "blur" }],
        qqNubmer: [{ validator: checkQQ, trigger: "blur" }]
      }
    };
  },
  methods: {
    ...mapActions(["register"]),
    handleRegister() {
      this.loading = true;
      this.$refs.userForm.validate(async validate => {
        if (!validate) return false;
        const data = {
          email: this.userForm.email,
          password: this.userForm.password,
          userName: this.userForm.userName,
          qqNubmer: String(this.userForm.qqNubmer),
          userType: 0,
          profile: this.userForm.profile
        };
        console.log(data);
        await this.register(data)
          .then(() => {
            this.$router.push({ name: "Login" });
          })
          .catch(() => {
            console.log("注册失败");
          });
        this.loading = false;
      });
    }
  }
};
</script>

<style scoped>
.register-box {
  position: absolute;
  width: 400px;
  height: 500px;
  background-color: white;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  padding: 30px;
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
.register-button {
  width: 100%;
}
</style>