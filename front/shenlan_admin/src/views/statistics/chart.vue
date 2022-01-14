<template>
  <div class="app-container">
    <!--表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-date-picker
          v-model="searchObj.begin"
          type="date"
          placeholder="选择开始日期"
          value-format="yyyy-MM-dd"/>
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="searchObj.end"
          type="date"
          placeholder="选择截止日期"
          value-format="yyyy-MM-dd"/>
      </el-form-item>
      <el-button
        :disabled="btnDisabled"
        type="primary"
        icon="el-icon-search"
        @click="showChart()">查询
      </el-button>
    </el-form>

    <el-row>
      <el-col :span="12">
        <div id="register_num" class="chart" style="height:500px;"/>
      </el-col>
      <el-col :span="12">
        <div id="login_num" class="chart" style="height:500px;"/>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <div id="video_view_num" class="chart" style="height:500px;"/>
      </el-col>
      <el-col :span="12">
        <div id="course_num" class="chart" style="height:500px;"/>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import echarts from "echarts";
import statisticsApi from "@/api/statistics";

export default {
  data() {
    return {
      searchObj: {
        begin: "",
        end: ""
      },
      btnDisabled: false,
      chartData: {}
    };
  },

  methods: {

    showChart() {
      statisticsApi.showChart(this.searchObj)
        .then(response => {
          this.chartData = response.data.chartData;
          this.showChartByType("register_num", "学员登录数统计", this.chartData.registerNum);
          this.showChartByType("login_num", "学员注册数统计", this.chartData.loginNum);
          this.showChartByType("video_view_num", "课程播放数统计", this.chartData.videoViewNum);
          this.showChartByType("course_num", "每日课程数统计", this.chartData.courseNum);
        });
    },

    showChartByType(type, title, data) {
      // 基于准备好的 dom，初始化 echarts 实例
      var myChart = echarts.init(document.getElementById(type));
      // 指定图表的配置项和数据
      var option = {
        title: {
          text: title
        },
        xAxis: {
          data: data.xData
        },
        yAxis: {},
        series: [{
          data: data.yData,
          type: "line"
        }]
      };

      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
    }
  }
};
</script>
