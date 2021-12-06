import router from "./router";
import store from "./store";
// Progress 进度条
import NProgress from "nprogress";
// Progress 进度条样式
import "nprogress/nprogress.css";
import {Message} from "element-ui";
// 验权
import {getToken} from "@/utils/auth";
// 不重定向白名单
const whiteList = ["/login"];
router.beforeEach((to, from, next) => {
  NProgress.start();
  if (getToken()) {
    if (to.path === "/login") {
      next({path: "/"});
      // if current page is dashboard will not trigger	afterEach hook, so manually handle it
      NProgress.done();
    } else {
      if (store.getters.roles.length === 0) {
        store.dispatch("GetInfo").then(res => {
          // 拉取用户信息
          next();
        }).catch((err) => {
          store.dispatch("FedLogOut").then(() => {
            Message.error(err || "Verification failed, please login again");
            next({path: "/"});
          });
        });
      } else {
        next();
      }
    }
  } else {
    if (whiteList.indexOf(to.path) !== -1) {
      next();
    } else {
      // 否则全部重定向到登录页
      next(`/login?redirect=${to.path}`);
      NProgress.done();
    }
  }
});

router.afterEach(() => {
  // 结束 Progress
  NProgress.done();
});
