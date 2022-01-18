# 一、搜索引擎优化

## 1、什么是SEO

![img](https://gitee.com/zzzfans/Image-Hosting-Service/raw/master/images/win-2022-01-16-01:10:58.png)

**总结：**seo是网站为了提高自已的网站排名，获得更多的流量，对网站的结构及内容进行调整和优化，以便搜索引擎 （百度，google等）更好抓取到优质网站的内容。

## 2、搜索引擎工作流程

![img](https://gitee.com/zzzfans/Image-Hosting-Service/raw/master/images/win-2022-01-16-01:10:21.png)

常见的SEO方法比如：

- 对url链接的规范化，多用restful风格的url，多用静态资源url；
- 注意keywords、description、title的设置；
- h1-h6、a标签的使用
- 等等

注意：spider对javascript支持不好，ajax获取的JSON数据无法被spider爬取

采用什么技术有利于SEO？要解答这个问题需要理解服务端渲染和客户端渲染。

# 二、服务端渲染和客户端渲染

## 1、什么是服务端渲染

服务端渲染又称SSR (Server Side Render)是在服务端完成页面的内容渲染，而不是在客户端完成页面内容的渲染。

SSR并不是前端特有的技术，我们学习过的JSP技术和Thymeleaf技术就是典型的SSR

![img](https://gitee.com/zzzfans/Image-Hosting-Service/raw/master/images/win-2022-01-16-01:11:10.png)

服务端渲染的特点：

- 在服务端生成html网页的dom元素
- 客户端（浏览器）只负责显示dom元素内容



## 2、什么是客户端渲染

客户端（浏览器） 使用AJAX向服务端发起http请 求，获取到了想要的数据，开始渲染html网页，生成dom元素，并最终将网页内容展示给用户。

![img](https://gitee.com/zzzfans/Image-Hosting-Service/raw/master/images/win-2022-01-16-01:11:18.png)

客户端渲染的特点：

- 在服务端只是给客户端响应的了数据，而不是html网页
- 客户端（浏览器）负责获取服务端的数据生成dom元素

## 3、两种方式各有什么优缺点？ 

**客户端渲染：** 

\1) 缺点：不利于网站进行SEO，因为网站大量使用javascript技术，不利于搜索引擎抓取网页。 

\2) 优点：客户端负责渲染，用户体验性好，服务端只提供数据不用关心用户界面的内容，有利于提高服务端的开发效率。 

3）适用场景：对SEO没有要求的系统，比如后台管理类的系统，如电商后台管理，用户管理等。

 **服务端渲染：** 

\1) 优点：有利于SEO，网站通过href的url将搜索引擎直接引到服务端，服务端提供优质的网页内容给搜索引擎。

\2) 缺点：服务端完成一部分客户端的工作，通常完成一个需求需要修改客户端和服务端的代码，开发效率低，不利于系统的稳定性。

3）适用场景：对SEO有要求的系统，比如：门户首页、商品详情页面等。

# 三、Nuxt.js

## 1、Nuxt.js介绍

移动互联网的兴起促进了web前后端分离开发模式的发展，服务端只专注业务，前端只专注用户体验，比如流行的vue.js实现了功能强大的前端渲染。 但是，对于有SEO需求的网页如果使用前端渲染技术去开发就不利于SEO了，有没有一种即使用vue.js 的前端技术也实现服务端渲染的技术呢？

Nuxt.js 是一个基于 Vue.js 的轻量级应用框架,可以用来创建服务端渲染 (SSR) 应用，也可充当静态站点引擎生成静态站点应用,具有优雅的代码结构分层和热加载等特性。

官网网站：https://zh.nuxtjs.org/

## 2、Nuxt.js服务器端渲染

下图展示了从客户端请求到Nuxt.js进行服务端渲染的整体的工作流程：

![img](https://gitee.com/zzzfans/Image-Hosting-Service/raw/master/images/win-2022-01-16-01:11:29.png)

1）用户打开浏览器，输入网址请求到Node.js中的前端View组件

2）部署在Node.js的应用Nuxt.js接收浏览器请求，并请求服务端获取数据 

3）Nuxt.js获取到数据后进行服务端渲染 

4）Nuxt.js将html网页响应给浏览器