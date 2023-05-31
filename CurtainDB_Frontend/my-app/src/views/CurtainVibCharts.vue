<template>
    <div class="manage">

        <div class="manage-header">
            <div>
                <input type="file" name="file" accept=".csv" ref="refFile" v-on:change="importCsv">
                <button type="submit" @click="uploadCsv">上传</button>
            </div>

            <!-- 搜索区 -->
            <el-form :model="CurtainVibForm" :inline="true">
                <el-form-item>
                    <el-input placeholder="29FA1867" v-model="CurtainVibForm.id"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button @click="onSubmit" type="NORMAL">搜索</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div ref="chart" style="width:100%;height:600px;"></div>

    </div>
</template>
  
<script>
import { getVib, getExcVib, getVibCSV } from "../api";
import * as echarts from 'echarts';

export default {
    data() {
        return {
            dialogVisible: false,
            form: {
                id: "",
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
            modalType: 0, //0表示新增的弹窗，1表示编辑的弹窗
            total: 0, //当前的总条数
            pageData: {
                page: 1,
                limit: 10,
            },
            CurtainVibForm: { id: '29FA1867' },
            CSVFile: {}
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
        getList() {
            // 刷新列表数据，封装下列操作
            // getVib({ params: { ...this.CurtainVibForm, ...this.pageData } }).then(
            //   ({ data }) => {
            //     console.log(data);
            //     console.log(this.CurtainVibForm.id);
            //     this.tableData = data.data;
            //     //this.pageData = data.data;
            //     this.total = this.tableData.length;
            //     //console.log(this.total);
            //   }
            // );
        },
        //选择页码的回调函数
        handlePage(val) {
            console.log(val, "val");
            this.pageData.page = val;
            this.getList();
        },
        //列表的查询
        onSubmit() {
            if (this.CurtainVibForm.id === "") {
                this.getList();
                return;
            }
            console.log(this.CurtainVibForm.id);
            getVib(this.CurtainVibForm.id).then(({ data }) => {
                this.$message({
                    type: "success",
                    message: "查找完成",
                });
                //console.log(data);
                this.tableData = [];
                this.tableData = data.data;
                console.log(this.tableData);

                // var chartDom = document.getElementById('chart');
                var myChart = echarts.init(this.$refs.chart);
                var option;
                option = {
                    title: {
                        text: 'Curtain Vib'
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ['x', 'y', 'z']
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    toolbox: {
                        feature: {
                            saveAsImage: {}
                        }
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: []
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [
                        {
                            // 29FA1867
                            name: 'x',
                            type: 'line',
                            stack: 'Total',
                            data: this.tableData.map((item) => item.x),
                            // visualMap: {
                            //     show: false,
                            //     dimension: 1,
                            //     pieces: [{ gte: 0.2, lte: 1.0, color: 'red' }],
                            //     outOfRange: {
                            //         color: 'black'
                            //     }
                            // },
                            markLine: {
                                data: [{ type: 'none', name: 'Max', yAxis: 0.2 }],
                                itemStyle: {
                                    normal: {
                                        lineStyle: {
                                            color: '#ff0000',
                                        }
                                    }
                                },
                                label: {
                                    position: 'end', // 表现内容展示的位置
                                    formatter: '异常上限',  // 标线展示的内容
                                    color: '#ff0000'  // 展示内容颜色
                                },
                            },
                            markArea: {
                                data: [[{
                                    yAxis: 0.2, itemStyle: {
                                        color: 'rgba(255, 0, 0, 0.1)' // 设置区域的颜色
                                    }
                                }, { yAxis: 0.3 }]]
                            },
                        },
                        {
                            name: 'y',
                            type: 'line',
                            stack: 'Total',
                            data: this.tableData.map((item) => item.y),
                            markLine: {
                                data: [{ type: 'none', name: 'Max', yAxis: -0.2 }],
                                itemStyle: {
                                    normal: {
                                        lineStyle: {
                                            color: '#ff0000',
                                        }
                                    }
                                },
                                label: {
                                    position: 'end', // 表现内容展示的位置
                                    formatter: '异常下限',  // 标线展示的内容
                                    color: '#ff0000'  // 展示内容颜色
                                },
                            },
                            markArea: {
                                data: [[{
                                    yAxis: -0.3, itemStyle: {
                                        color: 'rgba(255, 0, 0, 0.1)' // 设置区域的颜色
                                    }
                                }, { yAxis: -0.2 }]]
                            }
                        },
                        {
                            name: 'z',
                            type: 'line',
                            stack: 'Total',
                            data: this.tableData.map((item) => item.z),
                        },
                    ]
                };
                const { orderData } = data.data;
                console.log(data.data);
                console.log(orderData);
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
                myChart.setOption(option);
            });
        },
        importCsv() {
            this.CSVFile = {};
            this.CSVFile = this.$refs.refFile.files[0];
        },
        uploadCsv() {
            if (!this.CSVFile) {
                this.$message.error("请先选择一个CSV文件");
                return;
            }

            const formData = new FormData();
            formData.append("file", this.CSVFile);

            getVibCSV(formData).then(({ data }) => {
                console.log("*******");
                this.tableData = data.data;
                this.$message({
                    type: "success",
                    message: "上传完成",
                });
                console.log(this.tableData);
                // var chartDom = document.getElementById('chart');
                var myChart = echarts.init(this.$refs.chart);
                var option;
                option = {
                    title: {
                        text: 'Curtain Vib'
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ['x', 'y', 'z']
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    toolbox: {
                        feature: {
                            saveAsImage: {}
                        }
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: []
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [
                        {
                            // 29FA1867
                            name: 'x',
                            type: 'line',
                            stack: 'Total',
                            data: this.tableData.map((item) => item.x),
                            // visualMap: {
                            //     show: false,
                            //     dimension: 1,
                            //     pieces: [{ gte: 0.2, lte: 1.0, color: 'red' }],
                            //     outOfRange: {
                            //         color: 'black'
                            //     }
                            // },
                            markLine: {
                                data: [{ type: 'none', name: 'Max', yAxis: 0.2 }],
                                itemStyle: {
                                    normal: {
                                        lineStyle: {
                                            color: '#ff0000',
                                        }
                                    }
                                },
                                label: {
                                    position: 'end', // 表现内容展示的位置
                                    formatter: '异常上限',  // 标线展示的内容
                                    color: '#ff0000'  // 展示内容颜色
                                },
                            },
                            markArea: {
                                data: [[{
                                    yAxis: 0.2, itemStyle: {
                                        color: 'rgba(255, 0, 0, 0.1)' // 设置区域的颜色
                                    }
                                }, { yAxis: 0.3 }]]
                            },
                        },
                        {
                            name: 'y',
                            type: 'line',
                            stack: 'Total',
                            data: this.tableData.map((item) => item.y),
                            markLine: {
                                data: [{ type: 'none', name: 'Max', yAxis: -0.2 }],
                                itemStyle: {
                                    normal: {
                                        lineStyle: {
                                            color: '#ff0000',
                                        }
                                    }
                                },
                                label: {
                                    position: 'end', // 表现内容展示的位置
                                    formatter: '异常下限',  // 标线展示的内容
                                    color: '#ff0000'  // 展示内容颜色
                                },
                            },
                            markArea: {
                                data: [[{
                                    yAxis: -0.3, itemStyle: {
                                        color: 'rgba(255, 0, 0, 0.1)' // 设置区域的颜色
                                    }
                                }, { yAxis: -0.2 }]]
                            }
                        },
                        {
                            name: 'z',
                            type: 'line',
                            stack: 'Total',
                            data: this.tableData.map((item) => item.z),
                        },
                    ]
                };
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
                myChart.setOption(option);
            });
        }
    },
    mounted() {
        this.getList();
        // this.drawChart();
    },
};

</script>
    
<style lang="less" scoped>
.el-form-item__label {
    font-size: 12px;
}

.manage {
    height: 90%;

    .manage-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-top: 10px;
    }
}
</style>
    