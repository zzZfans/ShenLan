<template>
  <!-- 公共头 -->
  <header id="header">
    <section class="container">
      <h1 id="logo">
        <a href="#" title="深蓝学院">
          <img src="~/assets/img/logo.png" style="width: 195px;height: 74px" alt="深蓝学院">
        </a>
      </h1>
      <div class="h-r-nsl">
        <ul class="nav">
          <router-link to="/" tag="li" active-class="current" exact>
            <a>首页</a>
          </router-link>
          <router-link to="/course" tag="li" active-class="current">
            <a>课程</a>
          </router-link>
          <router-link to="/teacher" tag="li" active-class="current">
            <a>名师</a>
          </router-link>
          <router-link to="/article" tag="li" active-class="current">
            <a>文章</a>
          </router-link>
          <router-link to="/question" tag="li" active-class="current">
            <a>问答</a>
          </router-link>
        </ul>
        <!-- / nav -->
        <ul class="h-r-login">
          <li v-if="!userInfo" id="no-login">
            <a href="/login" title="登录">
              <em class="icon18 login-icon">&nbsp;</em>
              <span class="vam ml5">登录</span>
            </a>
            |
            <a href="/register" title="注册">
              <span class="vam ml5">注册</span>
            </a>
          </li>
          <!-- 注意undis将当前节点隐藏了 -->
          <li v-if="userInfo" id="is-login-one" class="mr10">
            <a id="headerMsgCountId" href="#" title="消息">
              <em class="icon18 news-icon">&nbsp;</em>
            </a>
            <q class="red-point">&nbsp;</q>
          </li>
          <!-- 注意undis将当前节点隐藏了 -->
          <li v-if="userInfo" id="is-login-two" class="h-r-user">
            <a href="/ucenter" title>
              <img
                :src="userInfo.avatar"
                width="30"
                height="30"
                class="vam picImg"
                alt
              >
              <span id="userName" class="vam disIb">{{ userInfo.nickname }}</span>
            </a>
            <a
              href="javascript:void(0)"
              title="退出"
              class="ml5"
              @click="logout()"
            >退出</a>
          </li>
          <!-- /未登录显示第1 li；登录后显示第2，3 li -->
        </ul>
        <aside class="h-r-search">
          <form action="#" method="post">
            <label class="h-r-s-box">
              <input type="text" placeholder="搜索课程" name="queryCourse.courseName" value>
              <button type="submit" class="s-btn">
                <em class="icon18">&nbsp;</em>
              </button>
            </label>
          </form>
        </aside>
      </div>
      <aside class="mw-nav-btn">
        <div class="mw-nav-icon"/>
      </aside>
      <div class="clear"/>
    </section>
  </header>
  <!-- /公共头 -->
</template>

<script>
import loginApi from '~/api/login'
import cookie from 'js-cookie'

export default {

  data() {
    return {
      userInfo: null
    }
  },

  created() {
    this.getUserInfo()
  },

  mounted() {
    // 微信登录 url token 获取
    this.token = this.$route.query.token
    if (this.token) {
      // 将 token 存在 cookie 中
      cookie.set('shenlan_jwt_token', this.token, { domain: 'localhost' })
      // 跳转页面：擦除 url 中的 token
      // window 对象在 created 方法中无法被访问，因此要写在 mounted 中
      window.location.href = '/'
    }
  },

  methods: {
    getUserInfo() {
      // 如果 cookie 中 token 不存在，则不显示用户信息
      if (!cookie.get('shenlan_jwt_token')) {
        return
      }
      // 如果 token 存在，则根据 token 解析用户登录信息
      loginApi.getLoginInfo().then(response => {
        // 渲染页面
        this.userInfo = response.data.userInfo
      })
    },
    logout() {
      cookie.set('shenlan_jwt_token',
        '',
        { domain: 'localhost' })
      // 跳转页面
      window.location.href = '/'
    }
  }
}
</script>
