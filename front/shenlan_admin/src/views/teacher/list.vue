<template>
  <div class="app-container">
    <!-- 表格 -->
    <el-table :data="list" border stripe>
      <el-table-column type="index" width="50"/>
      <el-table-column prop="name" label="名称" width="80"/>
      <el-table-column label="头衔" width="90">
        <template slot-scope="scope">
          <el-tag
            v-if="scope.row.level === 1"
            type="success"
            size="mini"
          >高级讲师
          </el-tag
          >
          <el-tag v-if="scope.row.level === 2" size="mini">首席讲师</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="intro" label="简介"/>
      <el-table-column prop="sort" label="排序" width="60"/>
      <el-table-column prop="joinDate" label="入驻时间" width="160"/>
    </el-table>

    <!--分页组件-->
    <el-pagination
      :total="total"
      :page-size="limit"
      :page-sizes="[5,10,20]"
      layout="prev, pager, next,sizes"/>

  </div>
</template>

<script>
import teacherApi from "@/api/teacher";

export default {
  data() {
    return {
      list: [], // 讲师列表
      total: 0, // 总记录数
      page: 1, // 页码
      limit: 5, // 每页记录数
      searchObj: {} // 查询表单
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    // 调用api模块，加载讲师列表
    fetchData() {
      teacherApi
        .pageList(this.page, this.limit, this.searchObj)
        .then(response => {
          this.list = response.data.rows;
          this.total = response.data.total;
        });
    }
  }
};
</script>
