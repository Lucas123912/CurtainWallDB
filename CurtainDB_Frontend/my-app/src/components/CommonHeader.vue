<template>
  <div class="header-container">
    <div class="l-content">
      <el-button
        @click="handleMenu"
        icon="el-icon-menu"
        size="mini"
      ></el-button>
      <!-- 面包屑 -->
      <!-- <el-breadcrumb separator="/" style="align:'center'">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item><a href="/">商城</a></el-breadcrumb-item>
        <el-breadcrumb-item>用户</el-breadcrumb-item>
        <el-breadcrumb-item>其他</el-breadcrumb-item>
      </el-breadcrumb> -->
    </div>
    <div class="r-content">
      <el-dropdown @command="handleClick">
        <span class="el-dropdown-link">
          <img class="user" src="../assets/Goose_Pic.jpg" alt="" />
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="cancel">Exit</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>
<script>
import Cookie from "js-cookie";

export default {
  data() {
    return {};
  },
  methods: {
    handleMenu() {
      this.$store.commit("collapseMenu");
    },
    handleClick(command) {
      if (command === "cancel") {
        //清除token
        //console.log("logout");
        Cookie.remove("token");
        //跳转到登录页面
        this.$router.push('/login')
      }
    },
  },
};
</script>

<style lang="less" scoped>
.header-container {
  padding: 0 20px;
  background-color: rgb(157, 141, 118);
  height: 60px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  .r-content {
    .user {
      width: 40px;
      height: 40px;
      border-radius: 50%; //圆角
    }
  }
}
</style>