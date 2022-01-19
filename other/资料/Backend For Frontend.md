# BFF

## 1、BFF 解决什么问题

一个前端页面向 Service A、Service B 以及 Service C发送请求，不同的微服务返回的值用于渲染页面中不同的组件。此时，每次访问该页面都需要发送 3 个请求。我们需要一个服务来聚合Service A、Service B 以及 Service C响应的数据，这个服务层叫做BFF。

![image-20220115213625935](https://gitee.com/zzzfans/Image-Hosting-Service/raw/master/images/win-2022-01-15-21:36:27.png)

手机、平板端、PC机等用户终端都需要向每个Service，例如Service A发送请求。对于同一个功能，不同的终端需要的数据格式和内容会有不同。此时 Service A 的一个接口，不能同时满足三个客户端的不同需求。我们可以在Service A中开发三个接口，也可以增加一个数据裁剪服务，将数据按照不同终端的不同要求进行裁剪，这个服务层叫做BFF。

![img](https://gitee.com/zzzfans/Image-Hosting-Service/raw/master/images/win-2022-01-15-21:36:16.png)

BFF层的作用是让前端有能力自由组装后台数据，减少大量的业务沟通成本，加快业务的迭代速度。无论是数据聚合还是数据剪裁，这类程序的特点是不需要太强大的服务器运算能力，但是对程序的灵活性有较高的要求，这两个特点都正好和Node.js的优势相吻合。

## 2、什么是BFF

用户体验适配器

