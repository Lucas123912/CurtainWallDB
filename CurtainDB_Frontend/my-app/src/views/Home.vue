<template>
  <el-row>
    <el-col :span="8" style="padding-right: 10px">
      <el-card class="box-card">
        <div class="user">
          <img src="../assets/Goose_Pic.jpg" alt="go" />
          <div class="userinfo">
            <p class="name">User</p>
            <p class="access">User</p>
          </div>
        </div>
        <div class="login-info">
          <p>上次登录时间：<span>2022-12-2</span></p>
          <p>上次登录地点：<span>上海</span></p>
        </div>
      </el-card>

      <el-card style="margin-top: 20px; height: 760px">
        <el-table :data="tableData" style="width: 100%; height: 400px">
          <el-table-column
            v-for="(val, key) in tableLabel"
            :prop="key"
            :label="val"
          />
        </el-table>
      </el-card>
    </el-col>

    <el-col :span="16" style="padding-left: 10px">
      <div class="num">
        <el-card>
          <el-descriptions title="User information" :column="7" border>
            <el-descriptions-item
              label="User name"
              label-class-name="my-label"
              content-class-name="my-content"
              >{{infoLabel.username}}</el-descriptions-item
            >
            <el-descriptions-item label="ID">
              <el-tag size="small">{{infoLabel.id}}</el-tag>
             </el-descriptions-item>


            <el-descriptions-item label="phone"
              >{{infoLabel.phone}}</el-descriptions-item
            >
            
            <el-descriptions-item label="Age">
              <el-tag size="small">{{infoLabel.age}}</el-tag>
            </el-descriptions-item>
            <el-descriptions-item
              label="Gender"
              :contentStyle="{ 'text-align': 'right' }"
              >{{infoLabel.gender}}</el-descriptions-item
            >
            <el-descriptions-item label="Email">
              <el-tag size="small">{{infoLabel.email}}</el-tag>
             </el-descriptions-item>
          </el-descriptions>
        </el-card>
      </div>
      <el-card style="height: 600px">
        <!-- 折线图 -->
        <div ref="echarts1" style="height: 560px">
          <el-calendar v-model="value"> </el-calendar>
        </div>
      </el-card>
      <div class="graph">
        <el-card style="height: 260px">
          <div class="block">
            <img src="../assets/Garfew2.jpg" alt="g2" />
            <img src="../assets/Margis.jpg" alt="g2" />
          </div>
        </el-card>
        <el-card style="height: 260px">
          <img src="../assets/Garfew.jpg" alt="g1" />
          <img src="../assets/Labulado.jpg" alt="g1" />
        </el-card>
      </div>
    </el-col>
  </el-row>
</template>


<style>
  .my-label {
    background: #E1F3D8;
  }

  .my-content {
    background: #FDE2E2;
  }
</style>

<script>
import { searchUser } from "@/api";
import * as echarts from "echarts";

export default {
  data() {
    return {
      showAdmin: false,
      showDoctor: true,
      showUser: false,
      tableData: [
        {
          name: "PinkDog",
          todayHP: 600,
          monthHP: 300,
          totalHP: 800,
        },
        {
          name: "BlueCat",
          todayHP: 100,
          monthHP: 300,
          totalHP: 800,
        },
        {
          name: "GreenBird",
          todayHP: 100,
          monthHP: 300,
          totalHP: 800,
        },
        {
          name: "BlackEagle",
          todayHP: 100,
          monthHP: 300,
          totalHP: 800,
        },
        {
          name: "YellowBee",
          todayHP: 100,
          monthHP: 300,
          totalHP: 800,
        },
        {
          name: "BrownBear",
          todayHP: 100,
          monthHP: 300,
          totalHP: 800,
        },
      ],
      tableLabel: {
        //prop: 'label'
        name: "Animal",
        todayHP: "Today",
        monthHP: "Month",
        totalHP: "Total",
      },
      userType: 0,
      value: new Date(),
      idInfo: 1,
      infoLabel: [],
    };
  },
  created() {
    this.idInfo = this.$route.query.id;
    console.log(this.idInfo);
    searchUser(this.idInfo).then(({ data }) => {
      console.log(data);
      this.infoLabel = data.data;
      console.log(this.infoLabel);
    });
  },
  mounted() {
    // getData().then(({ data }) => {
    //   const { tableData } = data.data;
    //   this.tableData = tableData; //给予tableData动态的数据
    //   //基于准备好的dom，初始化echarts实例
    //   const echarts1 = echarts.init(this.$refs.echarts1);
    //   //指定图表的配置项和数据(orderData)
    //   var echarts1Option = {};
    //   //处理数据xAxis
    //   const { orderData } = data.data;
    //   console.log(data);
    //   const xAxis = Object.keys(orderData.data[0]);
    //   const xAxisData = {
    //     data: xAxis,
    //   };
    //   echarts1Option.xAxis = xAxisData;
    //   echarts1Option.yAxis = {};
    //   echarts1Option.legend = xAxisData;
    //   echarts1Option.series = [];
    //   xAxis.forEach((key) => {
    //     echarts1Option.series.push({
    //       name: key,
    //       data: orderData.data.map((item) => item[key]),
    //       type: "line",
    //     });
    //   });
    //   //使用刚指定的配置项和数据显示图表
    //   echarts1.setOption(echarts1Option);
    // });
  },
};
</script>

<style lang="less" scoped>
.user {
  padding-bottom: 20px;
  margin-bottom: 20px;
  border-bottom: 1px solid #ccc; //下边框
  display: flex;
  align-items: center;

  img {
    margin-right: 40px;
    width: 150px;
    height: 150px;
    border-radius: 50%;
  }

  .userinfo {
    .name {
      font-size: 32px;
      margin-bottom: 10px;
    }

    .access {
      color: #999999;
    }
  }
}

.num {
  display: flex;
  //flex强制换行布局
  justify-content: space-between; //间隔
  .el-card {
    margin-bottom: 20px;
  }
}

.graph {
  margin-top: 20px;
  display: flex;
  justify-content: space-between;
  .el-card {
    width: 48%;
  }
  img {
    margin-right: 12px;
    width: 250px;
    height: 225px;
    border-radius: 10%;
  }
}
.login-info {
  p {
    line-height: 28px;
    font-size: 14px;
    color: #999999;

    span {
      color: #666666;
      margin-left: 60px;
    }
  }
}
</style>