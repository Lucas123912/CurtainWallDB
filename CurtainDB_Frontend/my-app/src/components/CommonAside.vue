<template>
  <el-menu
    default-active="1-4-1"
    class="el-menu-vertical-demo"
    @open="handleOpen"
    @close="handleClose"
    text-color="rgb(43, 23, 23)"
    :collapse="isCollapse"
    active-text-color="rgb(71,52,26)"
  >
    <h3>{{ isCollapse ? "DB" : "Curtain DB" }}</h3>
    <el-menu-item
      @click="clickMenu(item)"
      v-for="item in noChildren"
      :key="item.name"
      :index="item.name"
    >
      <i :class="`el-icon-${item.icon}`"></i>
      <span slot="title">{{ item.label }}</span>
    </el-menu-item>

    <el-submenu
      v-for="item in hasChildren"
      :key="item.label"
      :index="item.label"
    >
      <template slot="title">
        <i :class="`el-icon-${item.icon}`"></i>
        <span slot="title">{{ item.label }}</span>
      </template>

      <el-menu-item-group v-for="subItem in item.children" :key="subItem.path">
        <el-menu-item @click="clickMenu(subItem)" :index="subItem.path">{{
          subItem.label
        }}</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
  </el-menu>
</template>

<style lang="less" scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
  background-color: rgb(193, 212, 172);
}
.el-menu--collapse{
  min-height: 400px;
  background-color: rgb(193, 212, 172);
}
.el-menu {
  height: 120vh;
  font-family:微软雅黑;
  border-right: none;
  h3 {
    margin-top: 2px;
    color: rgb(43, 23, 23);
    text-align: center;
    line-height: 48px;
    font-size: 22px;
    font-weight: 600;
    font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
  }
}
</style>

<script>
export default {
  data() {
    return {
      //isCollapse: false, store时去除
      menuData: [
        {
          path: "/",
          name: "home",
          label: "主页",
          icon: "s-home",
          url: "Home/Home",
        },
        // {
        //   path: "/community",
        //   name: "community",
        //   label: "2",
        //   icon: "goods",
        //   url: "Community/Community",
        // },
        {
          label: "经管楼数据",
          icon: "s-marketing",
          children: [
            {
              path: "/curtainvib",
              name: "curtainvib",
              label: "数据查看",
              icon: "view",
              url: "CurtainData/CurtainVib",
            },
            {
              path: "/curtainvibcharts",
              name: "curtainvib",
              label: "文件上传与图表查看",
              icon: "view",
              url: "CurtainData/CurtainVibCharts",
            },
            {
              path: "/damagefilter",
              name: "damagefilter",
              label: "损伤筛选",
              icon: "data-line",
              url: "CurtainData/DamageFilter",
            },
          ],
        },
      ],
    };
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    //点击菜单
    clickMenu(item) {
      //console.log(item)
      if (
        this.$route.path !== item.path &&
        !(this.$route.path === "/home" && item.path === "/")
      ) {
        this.$router.push(item.path);
      }
    },
  },
  computed: {
    //没有子菜单
    noChildren() {
      return this.menuData.filter((item) => !item.children);
      //filter方法传入函数，不存在children则获取数据
    },
    //有子菜单
    hasChildren() {
      return this.menuData.filter((item) => item.children);
      //filter方法传入函数，不存在children则获取数据
    },
    isCollapse() {
      return this.$store.state.tab.isCollapse;
    },
  },
};
</script>

