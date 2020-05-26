<template>
  <div id="login">
    <div class="me-login-box me-login-box-radius">
      <h1>登录</h1>

      <el-form ref="userForm" :model="userForm" :rules="rules">
        <el-form-item prop="email">
          <el-input placeholder="邮箱" v-model="userForm.email"></el-input>
        </el-form-item>

        <el-form-item prop="password">
          <el-input placeholder="密码" type="password" v-model="userForm.password"></el-input>
        </el-form-item>

        <el-form-item size="small" class="me-login-button" :loading="loading">
          <el-button type="success" @click.native.prevent="handleLogin()">登录</el-button>
        </el-form-item>
      </el-form>
      <el-button
        size="small"
        type="success"
        class="me-register-button"
        @click.native.prevent="jumpToRegister()"
      >注册</el-button>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions, mapMutations } from "vuex";

export default {
  name: "Login",
  data() {
    return {
      loading: false,
      userForm: {
        email: "",
        password: ""
      },
      rules: {
        account: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          {
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"]
          }
        ],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }]
      }
    };
  },
  methods: {
    ...mapActions(['login']),
    handleLogin() {
      this.loading = true;
      this.$refs.userForm.validate(async validate => {
        if (!validate) return false;
        const data = {
          email:this.userForm.email,
          password:this.userForm.password
        }
        console.log(data)
        await this.login(data);
        this.loading = false;
      });
    },
    jumpToRegister() {
      this.$router.push({ name: "Register" });
    }
  }
};
</script>

<style scoped>
.me-login-box {
  position: absolute;
  width: 300px;
  height: 300px;
  background-color: white;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  padding: 30px;
}

.me-login-box-radius {
  border-radius: 10px;
  box-shadow: 0px 0px 1px 1px rgba(161, 159, 159, 0.1);
}

.me-login-box h1 {
  text-align: center;
  font-size: 24px;
  margin-bottom: 20px;
  vertical-align: middle;
}

.me-login-design {
  text-align: center;
  font-family: "Open Sans", sans-serif;
  font-size: 18px;
}

.me-login-design-color {
  color: #5fb878 !important;
}

/* .me-login-button {
  text-align: center;
} */

.me-login-button button {
  width: 100%;
}
.me-register-button {
  width: 100%;
}
</style>