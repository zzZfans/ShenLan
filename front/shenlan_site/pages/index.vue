<template>

  <div>
    <!-- 幻灯片 开始 -->
    <div v-swiper:mySwiper="swiperOption">
      <div class="swiper-wrapper">
        <div
          v-for="topBannerAd in topBannerAdList"
          :key="topBannerAd.id"
          :style="'background: ' + topBannerAd.color"
          class="swiper-slide">
          <a :href="topBannerAd.linkUrl" target="_blank">
            <img
              :src="topBannerAd.imageUrl"
              :alt="topBannerAd.title"
              style="width:auto;height:100%;">
          </a>
        </div>
      </div>
      <div class="swiper-pagination swiper-pagination-white"/>
      <div slot="button-prev" class="swiper-button-prev swiper-button-white"/>
      <div slot="button-next" class="swiper-button-next swiper-button-white"/>
    </div>
    <!-- 幻灯片 结束 -->

    <div id="aCoursesList">
      <!-- 网校课程 开始 -->
      <div>
        <section class="container">
          <header class="comm-title">
            <h2 class="tac">
              <span class="c-333">热门课程</span>
            </h2>
          </header>
          <div>
            <article class="comm-course-list">
              <ul id="bna" class="of">
                <li v-for="course in courseList" :key="course.id">
                  <div class="cc-l-wrap">
                    <section class="course-img">
                      <img
                        :src="course.cover"
                        :alt="course.title"
                        class="img-responsive"
                      >
                      <div class="cc-mask">
                        <a :href="'/course/'+course.id" title="开始学习" class="comm-btn c-btn-1">开始学习</a>
                      </div>
                    </section>
                    <h3 class="hLh30 txtOf mt10">
                      <a
                        :href="'/course/'+course.id"
                        :title="course.title"
                        class="course-title fsize18 c-333">{{ course.title }}</a>
                    </h3>
                    <span v-if="Number(course.price) === 0" class="fr jgTag bg-green">
                      <i class="c-fff fsize12 f-fA">免费</i>
                    </span>
                    <span v-else class="fr jgTag ">
                      <i class="c-orange fsize12 f-fA"> ￥{{ course.price }}</i>
                    </span>
                    <span class="fl jgAttr c-ccc f-fA">
                      <i class="c-999 f-fA">{{ course.viewCount }}人学习</i>
                      |
                      <i class="c-999 f-fA">{{ course.buyCount }}人购买</i>
                    </span>
                  </div>
                </li>
              </ul>
              <div class="clear"/>
            </article>
            <section class="tac pt20">
              <a href="#" title="全部课程" class="comm-btn c-btn-2">全部课程</a>
            </section>
          </div>
        </section>
      </div>
      <!-- /网校课程 结束 -->
      <!-- 网校名师 开始 -->
      <div>
        <section class="container">
          <header class="comm-title">
            <h2 class="tac">
              <span class="c-333">名师大咖</span>
            </h2>
          </header>
          <div>
            <article class="i-teacher-list">
              <ul class="of">
                <li v-for="teacher in teacherList" :key="teacher.id">
                  <section class="i-teach-wrap">
                    <div class="i-teach-pic">
                      <a :href="'/teacher/'+teacher.id" :title="teacher.name">
                        <img :src="teacher.avatar" :alt="teacher.name" height="142">
                      </a>
                    </div>
                    <div class="mt10 hLh30 txtOf tac">
                      <a :href="'/teacher/'+teacher.id" :title="teacher.name" class="fsize18 c-666">{{
                        teacher.name
                      }}</a>
                    </div>
                  </section>
                </li>
              </ul>
              <div class="clear"/>
            </article>
            <section class="tac pt20">
              <a href="teacher" title="全部讲师" class="comm-btn c-btn-2">全部讲师</a>
            </section>
          </div>
        </section>
      </div>
      <!-- /网校名师 结束 -->
    </div>
  </div>
</template>

<script>
import indexApi from '~/api/index'

export default {
  async asyncData() {
    // 获取首页banner数据
    const topBannerAdListResponse = await indexApi.getTopBannerAdList()
    const topBannerAdList = topBannerAdListResponse.data.items
    // 获取名师和热门课程
    const indexDataResponse = await indexApi.getIndexData()
    const courseList = indexDataResponse.data.courseList
    const teacherList = indexDataResponse.data.teacherList
    return {
      topBannerAdList,
      courseList,
      teacherList
    }
  },
  // 幻灯片导航的定义
  data() {
    return {
      swiperOption: {
        // 配置分页
        pagination: {
          el: '.swiper-pagination'// 分页的dom节点
        },
        // 配置导航
        navigation: {
          nextEl: '.swiper-button-next', // 下一页dom节点
          prevEl: '.swiper-button-prev'// 前一页dom节点
        }
      }
    }
  }
}
</script>
