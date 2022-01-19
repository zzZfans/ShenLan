# 一、什么是组件

组件（Component）是 Vue.js 最强大的功能之一。

组件可以扩展 HTML 元素，封装可重用的代码。

组件系统让我们可以用独立可复用的小组件来构建大型应用，几乎任意类型的应用界面都可以抽象为一个组件树：

![img](https://gitee.com/zzzfans/Image-Hosting-Service/raw/master/images/win-2022-01-15-23:40:05.png)

# 二、前端项目组件分析

## 1、三要素

- 入口js：src/main.js
- 入口页面：src/App.vue
- 路由：src/router/index.js

main.js 中引入了App.vue和 router/index.js，根据路由配置，App.vue中会显示相应的页面内容

## 2、入口文件

src/main.js

![img](https://gitee.com/zzzfans/Image-Hosting-Service/raw/master/images/win-2022-01-15-23:41:18.png)

## 3、主页面模块

src/App.vue

![img](https://gitee.com/zzzfans/Image-Hosting-Service/raw/master/images/win-2022-01-15-23:41:26.png)

## 4、路由模块

src/router/index.js

![img](https://gitee.com/zzzfans/Image-Hosting-Service/raw/master/images/win-2022-01-15-23:41:32.png)

## 5、登录页面组件

![img](https://gitee.com/zzzfans/Image-Hosting-Service/raw/master/images/win-2022-01-15-23:41:36.png)

# 三、前端项目布局分析

## 1、路由模块

src/router/index.js

![img](https://gitee.com/zzzfans/Image-Hosting-Service/raw/master/images/win-2022-01-15-23:41:41.png)

## 2、布局模块

src/views/layout/Layout.vue

![img](https://gitee.com/zzzfans/Image-Hosting-Service/raw/master/images/win-2022-01-15-23:41:46.png)

## 3、核心内容区域

src/views/layout/components/AppMain.vue

![img](https://gitee.com/zzzfans/Image-Hosting-Service/raw/master/images/win-2022-01-15-23:41:51.png)

## 4、讲师列表页面组件

![img](https://gitee.com/zzzfans/Image-Hosting-Service/raw/master/images/win-2022-01-15-23:41:55.png)