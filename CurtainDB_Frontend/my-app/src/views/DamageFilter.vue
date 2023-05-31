<template>
    <div class="manage">
        <el-dialog title="Tips" :visible.sync="dialogVisible" width="50%" :before-close="handleClose">
            <!-- 用户的表单信息 -->
            <el-form ref="form" :rules="rules" :inline="true" :model="form" label-width="70px">
                <el-form-item label="id" prop="id">
                    <el-input placeholder="Please Input VibID" v-model="form.id"></el-input>
                </el-form-item>
                <el-form-item label="no" prop="no">
                    <el-input style="width: auto" placeholder="Please Input Vib no" v-model="form.no"></el-input>
                </el-form-item>
                <el-form-item label="time" prop="time">
                    <el-input placeholder="Please Input Time" v-model="form.time"></el-input>
                </el-form-item>
                <el-form-item label="y" prop="y">
                    <el-input style="width: auto" placeholder="Please Input Vib y" v-model="form.y"></el-input>
                </el-form-item>
                <el-form-item label="z" prop="z">
                    <el-input style="width: auto" placeholder="Please Input Vib z" v-model="form.z"></el-input>
                </el-form-item>
                <el-form-item label="x" prop="x">
                    <el-input placeholder="Please Input Vib x" v-model="form.x"></el-input>
                </el-form-item>
            </el-form>

            <span slot="footer" class="dialog-footer">
                <el-button @click="cancel"> Cancel </el-button>
                <el-button type="primary" @click="submit"> Confirm </el-button>
            </span>
        </el-dialog>

        <div class="manage-header">
            <el-button @click="handleAdd()" type="info" icon="el-icon-edit" circle
                style="margin-left: 5px; margin-bottom: 8px"></el-button>

            <!-- 搜索区
        <el-form :model="CurtainVibForm" :inline="true">
          <el-form-item>
            <el-input
              placeholder="Please Input ID"
              v-model="CurtainVibForm.id"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button @click="onSubmit" type="warning">搜索</el-button>
          </el-form-item>
        </el-form> 
   -->
        </div>
        <div class="common-table">
            <el-table stripe height="90%" :data="tableData" style="width: 100%">
                <el-table-column prop="no" label="no"> </el-table-column>
                <el-table-column prop="time" label="time"> </el-table-column>
                <el-table-column>
                    <template slot-scope="scope">
                        <!-- 自定义列模板 -->
                        <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
                        <el-button size="mini" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <!-- <div class="pager">
                <el-pagination layout="prev, pager, next" :total="total" @current-change="handlePage">
                </el-pagination>
            </div> -->
        </div>
        <div>
            <div ref="heatchart" style="height: 400px;width: 1200px;margin:0 auto"></div>
        </div>

    </div>
</template>
  
<script>
import { getVib, getExcVib, getHeat } from "../api";
import * as echarts from 'echarts';


export default {
    data() {
        return {
            dialogVisible: false,
            form: {
                no: "",
                x: "",
                y: "",
                z: "",
                time: "",
            },
            rules: {
                id: [{ required: true, message: "Please Input VibID!" }],
                no: [{ required: true, message: "Please Input no!" }],
                z: [{ required: true, message: "Please Input z!" }],
                x: [{ required: true, message: "Please Input x!" }],
                y: [{ required: true, message: "Please Input y!" }],
                time: [{ required: true, message: "Please Input time!" }],
            },
            tableData: [],
            heatData: [],
            xAxisData: [],
            yAxisData: [],
            heatmapData: [],
            modalType: 0, //0表示新增的弹窗，1表示编辑的弹窗
            total: 0, //当前的总条数
            pageData: {
                page: 1,
                limit: 10,
            },
            CurtainVibForm: {
                id: "",
            },
        };
    },
    methods: {
        //   提交用户表单
        submit() {
            // this.$refs.form.validate((valid) => {
            //   //valid为true
            //   if (valid) {
            //     //后续对表单数据的处理
            //     //console.log(this.$refs.form);
            //     if (this.modalType === 0) {
            //       addVib(this.form).then(() => {
            //         // 重新获取列表的接口，刷新
            //         this.getList();
            //       });
            //     } else {
            //       editVib(this.form).then(() => {
            //         // 重新获取列表的接口，刷新
            //         this.getList();
            //       });
            //     }

            //     //关闭弹窗
            //     this.$refs.form.resetFields(); //重置表单内容
            //     this.dialogVisible = false;
            //   }
            // });
        },
        //弹窗关闭的时候触发
        handleClose() {
            // console.log(this.$refs.form);
            // this.$refs.form.resetFields(); //重置表单内容
            this.dialogVisible = false;
        },
        cancel() {
            this.handleClose();
        },
        handleEdit(row) {
            this.modalType = 1;
            this.dialogVisible = true;
            //注意需要对当前行数据进行深拷贝，否则会出错
            // this.form = JSON.parse(JSON.stringify(row));
        },
        handleDelete(id) {
            // this.$confirm("确认编辑数据?", "Tip", {
            //   confirmButtonText: "Confirm",
            //   cancelButtonText: "Cancel",
            //   type: "warning",
            // })
            //   .then(() => {
            //     delVib(id).then(() => {
            //       //传递对象
            //       //console.log({ id: row.id });
            //       //console.log(typeof (row.id));
            //       //console.log("/Vib/delete/" + row.id);
            //       this.$message({
            //         type: "success",
            //         message: "编辑成功",
            //       });
            //       // 重新获取列表的接口，刷新
            //       this.getList(); //使用了箭头函数才能这么写，涉及到获取的实例
            //     });
            //   })
            //   .catch(() => {
            //     this.$message({
            //       type: "info",
            //       message: "操作取消",
            //     });
            //   });
        },
        handleAdd() {
            this.modalType = 0;
            this.dialogVisible = true;
        },
        getList() {
            // 刷新列表数据，封装下列操作
            getExcVib().then(
                ({ data }) => {
                    this.tableData = data.data;
                    //this.pageData = data.data;
                    this.total = this.tableData.length;
                    //console.log(this.total);
                }
            );
        },
        getHeatData() {
            // 刷新列表数据，封装下列操作
            getHeat().then(
                ({ data }) => {
                    this.heatData = data.data;
                    console.log(this.heatData);
                    //this.pageData = data.data;
                    this.total = this.heatData.length;
                    //console.log(this.total);
                    const chartDom = this.$refs.heatchart;
                    const myChart = echarts.init(chartDom);

                    // 构造横轴和纵轴的数据
                    this.heatData.forEach(item => {
                        if (!this.xAxisData.includes(item.time)) {
                            this.xAxisData.push(item.time);
                        }
                        if (!this.yAxisData.includes(item.no)) {
                            this.yAxisData.push(item.no);
                            // 构造 Heatmap 数据
                        }
                    });
                    console.log(this.xAxisData);
                    console.log(this.yAxisData);

                    // 构造 Heatmap 数据
                    this.heatData.forEach(item => {
                        const xIndex = this.xAxisData.indexOf(item.time);
                        const yIndex = this.yAxisData.indexOf(item.no);
                        let value = 1;
                        if (item.status == "2") {
                            value = 2;
                        }
                        this.heatmapData.push([xIndex, yIndex, value]);
                    });
                    console.log(this.heatmapData);

                    const option = {
                        tooltip: {
                            position: "top"
                        },
                        xAxis: {
                            type: "category",
                            data: this.xAxisData
                        },
                        yAxis: {
                            type: "category",
                            data: this.yAxisData
                        },
                        visualMap: {
                            min: 1,
                            max: 2,
                            calculable: true,
                            orient: "horizontal",
                            left: "center",
                            bottom: "0%"
                        },
                        series: [
                            {
                                name: "故障",
                                type: "heatmap",
                                data: this.heatmapData
                            }
                        ]
                    };
                    myChart.setOption(option);
                }
            );
        },
        //选择页码的回调函数
        handlePage(val) {
            console.log(val, "val");
            this.pageData.page = val;
            this.getList();
        },
        //列表的查询
        onSubmit() {
            // if (this.CurtainVibForm.id === "") {
            //   this.getList();
            //   return;
            // }
            // console.log(this.CurtainVibForm.id);
            // getVib(this.CurtainVibForm.id).then(({ data }) => {
            //   this.$message({
            //     type: "success",
            //     message: "查找完成",
            //   });
            //   //console.log(data);
            //   this.tableData = [];
            //   this.tableData= data.data;
            //   console.log(this.tableData);

            // 重新获取列表的接口，刷新
            //this.getList(); //使用了箭头函数才能这么写，涉及到获取的实例
            // });
        },
    },
    mounted() {
        this.getList();
        this.getHeatData();
    },
};
</script>
    
<style lang="less" scoped>
.el-form-item__label {
    font-size: 12px;
}

.manage {
    height: 90%;

    .common-table {
        position: relative;
        height: calc(40% - 62px);

        .pager {
            position: absolute;
            bottom: 0;
            right: 20px;
        }
    }

    .manage-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-top: 10px;
    }
}
</style>
    