<template>
  <div>
    <el-form
      ref="form"
      label-width="70px"
      :inline="true"
      class="login-container"
      :model="form"
      :rules="rules"
    >
      <h3 class="login_title">System Login</h3>
      <el-form-item label="UserID" prop="id">
        <el-input
          v-model="form.id"
          placeholder="Please Input your userID"
        ></el-input>
      </el-form-item>
      <el-form-item label="Password" prop="password">
        <el-input
          type="password"
          v-model="form.password"
          placeholder="Please Input your password"
        ></el-input>
      </el-form-item>

      <el-form-item>
        <el-checkbox v-model="form.remember">Remember Me</el-checkbox>
      </el-form-item>

      <el-form-item>
        <el-button @click="handleRegister" type="success">Register</el-button>
        <el-button @click="submit" type="primary">Login</el-button>
      </el-form-item>
    </el-form>

    <div class="register">
      <el-dialog
        title="Info"
        :visible.sync="dialogVisible"
        width="50%"
        :before-close="handleClose"
        :model="registerForm"
        :rules="registerRules"
      >
        <!-- 用户的表单信息 -->
        <el-form
          ref="form"
          :rules="rules"
          :inline="true"
          :model="registerForm"
          label-width="70px"
        >
          <el-form-item label="ID" prop="id">
            <el-input
              placeholder="Please Input UserID"
              v-model="registerForm.id"
            ></el-input>
          </el-form-item>

          <el-form-item label="Password" prop="password">
            <el-input
              placeholder="Please Input Password"
              v-model="registerForm.password"
            ></el-input>
          </el-form-item>

          <el-form-item label="Name" prop="username">
            <el-input
              style="width: auto"
              placeholder="Please Input username"
              v-model="registerForm.username"
            ></el-input>
          </el-form-item>
          <el-form-item label="Gender" prop="gender">
            <el-input
              style="width: auto"
              placeholder="Please Input gender"
              v-model="registerForm.gender"
            ></el-input>
          </el-form-item>

          <el-form-item label="Age" prop="age">
            <el-input
              style="width: auto"
              placeholder="Please Input Age"
              v-model="registerForm.age"
            ></el-input>
          </el-form-item>

          <el-form-item label="Email" prop="email">
            <el-input
              style="width: auto"
              placeholder="Please Input email"
              v-model="registerForm.email"
            ></el-input>
          </el-form-item>

          <el-form-item label="Address" prop="address">
            <el-input
              style="width: auto"
              placeholder="Please Input address"
              v-model="registerForm.address"
            ></el-input>
          </el-form-item>
          <el-form-item label="Phone" prop="phone">
            <el-input
              placeholder="Please Input phone"
              v-model="registerForm.phone"
            ></el-input>
          </el-form-item>
        </el-form>

        <span slot="footer" class="dialog-footer">
          <el-button @click="cancel"> Cancel </el-button>
          <el-button type="primary" @click="registerSubmit">
            Confirm
          </el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>
<script>
import Mock from "mockjs";
import Cookie from "js-cookie";
import { getMenu, addRegister } from "../api";

export default {
  data() {
    return {
      dialogVisible: false,
      form: {
        id: "",
        password: "",
        remember: "0",
      },
      registerForm: {
        username: "",
        id: "",
        password: "",
        gender: "",
        type: "1",
        phone: "",
        email: "",
        age: "",
        address: "",
      },
      rules: {
        id: [
          {
            required: true,
            trigger: "blur",
            message: "Please input your userid/name",
          }, //失焦触发
        ],
        password: [
          {
            required: true,
            trigger: "blur",
            message: "Please input your password",
          },
        ],
      },
      registerRules: {
        username: [{ required: true, message: "Please Input username!" }],
        id: [{ required: true, message: "Please Input id!" }],
        age: [{ required: true, message: "Please Input age!" }],
        password: [{ required: true, message: "Please Input password!" }],
        address: [{ required: true, message: "Please Input address!" }],
        email: [{ required: true, message: "Please Input email!" }],
        gender: [{ required: true, message: "Please Input gender!" }],
        phone: [{ required: true, message: "Please Input phone!" }],
      },
    };
  },
  created() {
    // 在页面加载时从cookie获取登录信息
    let id = this.getCookie("id");
    let password = this.getCookie("password");
    let remember = this.getCookie("remember");
    //let password = Base64.decode(this.getCookie("password"));
    // 如果存在赋值给表单，并且将记住密码勾选
    if (remember) {
      this.form.id = id;
      this.form.password = password;
      this.form.remember = true;
    }
  },

  methods: {
    //login
    submit() {
      //   //token信息
      //   const token = Mock.Random.guid();
      //   //将token信息存入cookie用于不同页面间的通信
      //校验通过
      this.$refs.form.validate((valid) => {
        if (valid) {
          getMenu(this.form).then(({ data }) => {
            console.log(data);
            if (data.code === 1) {
              //console.log(data);
              console.log(data.data.type);
              if (data.data.type === 1) {
                this.$router.push({
                  path: "/home",
                  query: { id: data.data.id },
                });
              } else if (data.data.type === 2) {
                this.$router.push("/homedoctor");
              } else {
                this.$router.push("/homeadmin");
              }
              this.setUserInfo();
            } else {
              this.$message.error("Login Error");
            }
          });
        }
      });
    },

    setUserInfo: function () {
      // 判断用户是否勾选记住密码，如果勾选，向cookie中储存登录信息，
      // 如果没有勾选，储存的信息为空
      if (this.form.remember) {
        this.setCookie("id", this.form.id);
        this.setCookie("password", this.form.password);
        this.setCookie("remember", this.form.remember);
      } else {
        this.setCookie("id", "");
        this.setCookie("password", "");
        this.setCookie("remember", false);
        this.form.remember = false;
      }
    },
    // 获取cookie
    getCookie: function (key) {
      if (document.cookie.length > 0) {
        var start = document.cookie.indexOf(key + "=");
        if (start !== -1) {
          start = start + key.length + 1;
          var end = document.cookie.indexOf(";", start);
          if (end === -1) end = document.cookie.length;
          return unescape(document.cookie.substring(start, end));
        }
      }
      return "";
    },
    // 保存cookie
    setCookie: function (cName, value, expiredays) {
      var exdate = new Date();
      exdate.setDate(exdate.getDate() + expiredays);
      document.cookie =
        cName +
        "=" +
        decodeURIComponent(value) +
        (expiredays == null ? "" : ";expires=" + exdate.toGMTString());
    },
    handleRegister() {
      this.dialogVisible = true;
    },
    cancel() {
      this.handleClose();
    },
    handleClose() {
      console.log(this.$refs.form);
      this.$refs.form.resetFields(); //重置表单内容
      this.dialogVisible = false;
    },
    registerSubmit() {
      addRegister(this.registerForm).then(() => {
        this.$message({
          type: "success",
          message: "Register successfully!",
        });
      });
      //关闭弹窗
      this.$refs.form.resetFields(); //重置表单内容
      this.dialogVisible = false;
    },
  },
};
</script>

<style lang="less" scoped>
.login-container {
  width: 400px;
  border: 1px solid #eaeaea;
  margin: 190px auto;
  padding: 35px 35px 15px 35px;
  background-color: #fff;
  border-radius: 15px;
  box-shadow: 0 0 25px #cac6c6;
  box-sizing: border-box;
  .el-input {
    width: 215px;
    margin-left: 30px;
  }
  .login_title {
    text-align: center;
    margin-bottom: 40px;
    color: #505458;
  }

  .el-checkbox {
    margin-left: 10px;
  }
  .el-button {
    margin-left: 130px;
    margin-top: 10px;
  }
}
</style>