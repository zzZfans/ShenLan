<template>
  <div>
    <div v-for="(teacher,index) in items" :key="index">
      <a :href="'teacher/' +teacher.id">{{ teacher.name }}</a>
    </div>
  </div>
</template>

<script>
import teacherApi from '~/api/teacher'

export default {
  // 异步数据获取：在前端服务器执行
  asyncData() {
    return teacherApi.getList().then(response => {
      return {
        items: response.data.items
      }
    })
  },

  data() {
    return {
      test: 'test' // 这里的数据最后会和asyncData中的数据定义合并
    }
  }
}
</script>
