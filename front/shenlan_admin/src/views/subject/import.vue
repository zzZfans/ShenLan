<template>
  <div class="app-container">
    <el-form label-width="120px">

      <el-form-item label="信息描述">
        <el-tag type="info">excel模版说明</el-tag>
        <el-tag>
          <i class="el-icon-download"/>
          <a :href="defaultExcelTemplate">点击下载模版</a>
        </el-tag>
      </el-form-item>

      <el-form-item label="选择Excel">
        <el-upload
          ref="upload"
          :auto-upload="false"
          :on-exceed="fileUploadExceed"
          :on-success="fileUploadSuccess"
          :on-error="fileUploadError"
          :limit="1"
          action="http://127.0.0.1:9110/admin/edu/subject/import"
          name="file"
          accept="application/vnd.ms-excel">
          <el-button
            slot="trigger"
            size="small"
            type="primary">选取文件
          </el-button>
          <el-button
            :disabled="importBtnDisabled"
            style="margin-left: 10px;"
            size="small"
            type="success"
            @click="submitUpload()">导入
          </el-button>
        </el-upload>
      </el-form-item>

    </el-form>
  </div>
</template>
<script>
export default {
  data() {
    return {
      defaultExcelTemplate: process.env.OSS_PATH + "/excel/课程分类列表模板.xls", // 默认Excel模板
      importBtnDisabled: false // 导入按钮是否禁用
    };
  },
  methods: {
    // 上传多于一个视频时
    fileUploadExceed() {
      this.$message.warning("只能选取一个文件");
    },

    // 上传
    submitUpload() {
      this.importBtnDisabled = true;
      this.$refs.upload.submit(); // 提交上传请求
    },

    // 上传成功的回调
    fileUploadSuccess(response) {
      if (response.success) {
        this.importBtnDisabled = false; // 启用按钮
        this.$message.success(response.message);
        this.$refs.upload.clearFiles(); // 清空文件列表
      } else {
        this.$message.error("上传失败!（非20000）");
      }
    },

    // 上传失败的回调
    fileUploadError() {
      this.importBtnDisabled = false; // 启用按钮
      this.$message.error("上传失败!（http失败）");
      this.$refs.upload.clearFiles(); // 清空文件列表
    }
  }
};
</script>
