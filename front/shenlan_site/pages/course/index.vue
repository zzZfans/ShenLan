<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- 课程列表 开始 -->
    <section class="container">
      <header class="comm-title">
        <h2 class="fl tac">
          <span class="c-333">全部课程</span>
        </h2>
      </header>
      <section class="c-sort-box">
        <section class="c-s-dl">
          <dl>
            <dt>
              <span class="c-999 fsize14">课程类别</span>
            </dt>
            <dd class="c-s-dl-li">
              <!-- 一级类别 开始-->
              <ul class="clearfix">
                <li :class="{current:!$route.query.subjectParentId}">>
                  <a
                    title="全部"
                    href="javascript:void(0);"
                    @click="searchSubjectLevelOne('')">全部</a>
                </li>
                <li
                  v-for="item in subjectNestedList"
                  :key="item.id"
                  :class="{current:$route.query.subjectParentId===item.id}">
                  <a
                    title="后端开发"
                    href="javascript:void(0);"
                    @click="searchSubjectLevelOne(item.id)">{{ item.title }}</a>
                </li>
              </ul>
              <!-- /一级类别 结束-->
            </dd>
          </dl>
          <dl>
            <dt>
              <span class="c-999 fsize14"/>
            </dt>
            <dd class="c-s-dl-li">
              <!-- 二级类别 开始-->
              <ul v-if="$route.query.subjectParentId" class="clearfix">
                <li :class="{current:!$route.query.subjectId}">
                  <a
                    title="全部"
                    href="javascript:void(0);"
                    @click="searchSubjectLevelTwo()">全部</a>
                </li>
                <li
                  v-for="item in subSubjectList"
                  :key="item.id"
                  :class="{current:$route.query.subjectId===item.id}">
                  <a
                    :title="item.title"
                    href="javascript:void(0);"
                    @click="searchSubjectLevelTwo(item.id)">{{ item.title }}</a>
                </li>
              </ul>
              <!-- /二级类别 结束-->
            </dd>
          </dl>
          <div class="clear"/>
        </section>
        <div class="js-wrap">
          <section class="fr">
            <span class="c-ccc">
              <i class="c-master f-fM">1</i>/
              <i class="c-666 f-fM">1</i>
            </span>
          </section>
          <section class="fl">
            <!-- 排序 开始-->
            <ol class="js-tap clearfix">
              <li :class="{'current bg-green': $route.query.buyCountSort}">
                <a title="销量" href="javascript:void(0);" @click="searchBuyCount()">销量
                  <span>↓</span>
                </a>
              </li>
              <li :class="{'current bg-green': $route.query.createTimeSort}">
                <a title="最新" href="javascript:void(0);" @click="searchCreateTime()">最新
                  <span>↓</span>
                </a>
              </li>
              <li :class="{'current bg-green': $route.query.priceSort}">
                <a
                  v-if="!$route.query.type || $route.query.type == 1"
                  title="价格"
                  href="javascript:void(0);"
                  @click="searchPrice(2)">价格
                  <i>{{ $route.query.priceSort ? '↑' : '↓' }}</i>
                </a>
                <a
                  v-if="$route.query.type == 2"
                  title="价格"
                  href="javascript:void(0);"
                  @click="searchPrice(1)">价格
                  <i>↓</i>
                </a>
              </li>
            </ol>
            <!-- /排序 结束-->
          </section>
        </div>
        <div class="mt40">
          <!-- /无数据提示 开始-->
          <section v-if="courseList.length===0" class="no-data-wrap">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
          </section>
          <!-- /无数据提示 结束-->

          <!-- 数据列表 开始-->
          <article v-if="courseList.length>0" class="comm-course-list">
            <ul id="bna" class="of">
              <li v-for="item in courseList" :key="item.id">
                <div class="cc-l-wrap">
                  <section class="course-img">
                    <img :src="item.cover" :alt="item.title" class="img-responsive">
                    <div class="cc-mask">
                      <a :href="'/course/'+item.id" title="开始学习" class="comm-btn c-btn-1">开始学习</a>
                    </div>
                  </section>
                  <h3 class="hLh30 txtOf mt10">
                    <a :href="'/course/'+item.id" :title="item.title" class="course-title fsize18 c-333">{{
                      item.title
                    }}</a>
                  </h3>
                  <section class="mt10 hLh20 of">
                    <span v-if="Number(item.price) === 0" class="fr jgTag bg-green">
                      <i class="c-fff fsize12 f-fA">免费</i>
                    </span>
                    <span v-else class="fr jgTag ">
                      <i class="c-orange fsize12 f-fA"> ￥{{ item.price }}</i>
                    </span>
                    <span class="fl jgAttr c-ccc f-fA">
                      <i class="c-999 f-fA">{{ item.viewCount }}人学习</i>
                      |
                      <i class="c-999 f-fA">{{ item.buyCount }}人购买</i>
                    </span>
                  </section>
                </div>
              </li>
            </ul>
            <div class="clear"/>
          </article>
          <!-- /数据列表 结束-->
        </div>
      </section>
    </section>
    <!-- /课程列表 结束 -->
  </div>
</template>

<script>
import courseApi from '~/api/course'
import queryString from 'querystring' // url参数拼接工具

export default {

  async asyncData(page) {
    // 组装查询参数
    const searchObj = {}
    // 从url地址栏中获取查询参数
    const query = page.route.query
    searchObj.subjectParentId = query.subjectParentId || '' // "A || B"：如果 A 为空则 B
    searchObj.subjectId = query.subjectId || ''
    searchObj.buyCountSort = query.buyCountSort || ''
    searchObj.createTimeSort = query.createTimeSort || ''
    searchObj.priceSort = query.priceSort || ''
    searchObj.type = query.type || '' // 1：正序，2：倒序
    // 课程列表
    const courseListResponse = await courseApi.getList(searchObj)
    const courseList = courseListResponse.data.courseList
    // 获取课程分类嵌套列表
    const subjectNestedListResponse = await courseApi.getSubjectNestedList()
    const subjectNestedList = subjectNestedListResponse.data.items
    // 创建课程分类子列表
    let subSubjectList = []
    // 遍历一级分类
    for (let i = 0; i < subjectNestedList.length; i++) {
      // 如果查询参数中的一级分类id和当前一级分类id一致
      if (subjectNestedList[i].id === searchObj.subjectParentId) {
        // 则获取二级分类列表
        subSubjectList = subjectNestedList[i].children
        break
      }
    }
    return {
      courseList, // 课程列表
      subjectNestedList, // 一级分类列表
      subSubjectList, // 二级分类列表
      searchObj
    }
  },
  methods: {
    // 选择一级分类
    searchSubjectLevelOne(subjectParentId) {
      window.location = 'course?subjectParentId=' + subjectParentId
    },
    // 选择二级分类
    searchSubjectLevelTwo(subjectId) {
      // 自动组装queryString
      const obj = {
        subjectParentId: this.searchObj.subjectParentId,
        subjectId: subjectId
      }
      const querys = queryString.stringify(obj)
      window.location = '/course?' + querys
    },
    // 选择按销量倒序
    searchBuyCount() {
      // 自动组装queryString
      const obj = {
        subjectParentId: this.searchObj.subjectParentId,
        subjectId: this.searchObj.subjectId,
        buyCountSort: 1
      }
      const querys = queryString.stringify(obj)
      window.location = '/course?' + querys
    },
    // 选择按创建时间倒序
    searchCreateTime() {
      // 自动组装queryString
      const obj = {
        subjectParentId: this.searchObj.subjectParentId,
        subjectId: this.searchObj.subjectId,
        createTimeSort: 1
      }
      const querys = queryString.stringify(obj)
      window.location = '/course?' + querys
    },
    // 选择按价格倒序
    searchPrice(type) {
      // 自动组装queryString
      const obj = {
        subjectParentId: this.searchObj.subjectParentId,
        subjectId: this.searchObj.subjectId,
        priceSort: 1,
        type: type
      }
      const querys = queryString.stringify(obj)
      window.location = '/course?' + querys
    }
  }
}
</script>
