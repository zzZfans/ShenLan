# 功能简介

深蓝学院，是一个 `B2C` 模式的职业技能在线教育系统，分为前台用户系统和后台运营平台。  

> **Business To Customer 会员模式**
>
> 商家到用户，这种模式是自己制作大量自有版权的视频，放在自有平台上。 这种模式简单，快速，只要专心录制大量视频即可快速发展。但在中国由于版权保护意识不强，教育内容易于复制，有海量的免费资源的竞争对手众多等原因，难以取得像样的现金流。

![image-20220115174022685](https://gitee.com/zzzfans/Image-Hosting-Service/raw/master/images/win-2022-01-15-17:40:24.png)

# 技术架构

系统开发阶段使用了前后端分离架构。

![image-20220115174204083](https://gitee.com/zzzfans/Image-Hosting-Service/raw/master/images/win-2022-01-15-17:42:06.png)

# 后端（**shenlan_parent**）工程结构

- **guli_parent：根目录（父工程），管理四个子模块：**
  - **common**：公共模块父节点
    - common_util：工具类模块，所有模块都可以依赖于它
    - service_base：service 服务的 base 包，包含 service 服务的公共配置类，所有 service 模块依赖于它

  - **infrastructure**：基础服务模块父节点
    - api_gateway：api 网关服务

  - **service**：api 接口服务父节点
    - service_edu：教学相关 api 接口服务
    - service_oss：阿里云 oss api 接口服务
    - service_cms：cms api 接口服务
    - service_sms：短信 api 接口服务
    - service_trade：订单和支付相关 api 接口服务
    - service_statistics：统计报表 api 接口服务
    - service_ucenter：会员 api 接口服务
    - service_vod：视频点播 api 接口服务

# 数据库设计规约

注意：数据库设计规约并不是数据库设计的严格规范，根据不同团队的不同要求设计

本项目参考《阿里巴巴Java开发手册》：五、MySQL数据库

1、库名与应用名称尽量一致

2、表名、字段名必须使用小写字母或数字，禁止出现数字开头，

3、表名不使用复数名词

4、表的命名最好是加上“业务名称_表的作用”。如，edu_teacher

5、表必备三字段：id, gmt_create, gmt_modified

6、单表行数超过 500 万行或者单表容量超过 2GB，才推荐进行分库分表。 说明：如果预计三年后的数据量根本达不到这个级别，请不要在创建表时就分库分表。 

7、表达是与否概念的字段，必须使用 is_xxx 的方式命名，数据类型是 unsigned tinyint （1 表示是，0 表示否）。 

说明：任何字段如果为非负数，必须是 unsigned。

注意：POJO 类中的任何布尔类型的变量，都不要加 is 前缀。数据库表示是与否的值，使用 tinyint 类型，坚持 is_xxx 的 命名方式是为了明确其取值含义与取值范围。 

正例：表达逻辑删除的字段名 is_deleted，1 表示删除，0 表示未删除。 

8、小数类型为 decimal，禁止使用 float 和 double。 说明：float 和 double 在存储的时候，存在精度损失的问题，很可能在值的比较时，得到不 正确的结果。如果存储的数据范围超过 decimal 的范围，建议将数据拆成整数和小数分开存储。

9、如果存储的字符串长度几乎相等，使用 char 定长字符串类型。 

10、varchar 是可变长字符串，不预先分配存储空间，长度不要超过 5000，如果存储长度大于此值，定义字段类型为 text，独立出来一张表，用主键来对应，避免影响其它字段索引效率。

11、唯一索引名为 uk_字段名(unique key)；普通索引名则为 idx_字段名(index)。

说明：uk_ 即 unique key；idx_ 即 index 的简称

12、不得使用外键与级联，一切外键概念必须在应用层解决。外键与级联更新适用于单机低并发，不适合分布式、高并发集群；级联更新是强阻塞，存在数据库更新风暴的风险；外键影响数据库的插入速度。 

# 说明

1. 注册时验证码根据手机号发送。
2. 首页开启了 Redis 缓存，因此某些数据修改不会立即生效，可以关闭缓存或者去 Redis 中删除缓存即可。

# 后端相关技术

系统后端接口部分，使用目前市面上流行的 `SpringBoot` + `SpringCloud` 进行微服务架构，使用 `Feign`、`Gateway`、`Sentinel`，以及阿里巴巴的 `Nacos` 等组件搭建了项目的基础环境。采用 `MyBatis-Plus ` 进行持久层的操作，使用了 `OAuth2` + `JWT` 实现了分布式的访问。此外，项目中使用了阿里巴巴的 `EasyExcel` 实现对 `Excel` 的读写操作，使用了 `Redis` 进行首页数据的缓存，使用 `Git` 进行代码的版本控制，整合了 `Knife4j` 生成接口文档。

# 前端相关技术

系统前端部分，使用主流的前端框架 `Vue`，使用 `ES6` 的开发规范，采用模块化的开发模式，搭建页面环境使用了 `Nuxt` 框架和 `vue-admin-template` 模板，使用 `Element` 进行页面布局。前端环境中使用 `npm` 进行依赖管理，使用 `Babel` 进行代码转换，使用 `webpack` 进行静态资源的打包，采用 `Axios` 进行 `AJAX` 请求调用，使用 `ECharts` 实现数据的可视化。

# 本地启动运行

## 前端

两个前端，分别 clone 后，执行命令

```bash
npm install
npm run dev
```

可能会出现安装 sass 失败的问题，有可能需要 python 环境，有可能 node、npm 版本过低，有可能需要指定一下淘宝镜像。

```bash
npm i node-sass --sass_binary_site=https://npm.taobao.org/mirrors/node-sass/
```

`nuxt.config.js` 文件可以配置一些基本的网站信息。

`utils/request.js` 文件中指定了 api 接口地址。

## 后端

后端，安装完依赖后，除了修改基本的数据库连接信息，还要修改 application.yml 中的一些配置信息，然后根据需要选择启动模块。

前后端某些依赖随着时间版本推进可能出现不适用等的情况，请根据提示自行解决，阿里云 VOD 依赖不开源需要自行安装，同时阿里云 VOD 与 OSS 版本需相互兼容。

# other guide

### 项目骨架

| 框架                 |
| -------------------- |
| Spring Boot          |
| Spring Cloud         |
| Spring Cloud Alibaba |
| Mybatis Plus         |
| Knife4j              |
| Spring Cloud Gateway |

### 数据库

| 数据库 | 作用                                           |
| ------ | ---------------------------------------------- |
| MySQL  | 存储数据                                       |
| Redis  | 缓存首页数据，缓存 token 用于 refresh 以及校验 |

### 第三方服务

| 服务名     | 作用             |
| ---------- | ---------------- |
| 微信支付   | 支付             |
| 阿里云 VOD | 视频上传，解码等 |
| 阿里云 OSS | 图片和文件存储   |
| 腾讯云 SMS | 短信发送         |

### 其它

| 组件名    | 作用                           |
| --------- | ------------------------------ |
| Nacos     | 服务注册与发现、配置中心       |
| Sentinel  | 服务监控，以及熔断、限流、降级 |
| OpenFegin | http 远程调用                  |
| EasyExcel | Excel 操作                     |
| JJWT      | jwt token 工具                 |

## 前端技术栈

### 后台

| 框架、UI、模板    |
| ----------------- |
| Vue               |
| Element           |
| vue-element-admin |

### 前台

| 框架、UI |
| -------- |
| Vue      |
| Nuxt     |
| Element  |

## 环境搭建

### 导入数据库

导入 sql 文件夹下的所有 sql 文件

### 搭建 Redis

### 启动 nacos

### 启动 sentinel

### 修改配置文件

oss、vod、sms、ucenter、trade 服务中都涉及到了 Key 和 Secret，请自行申请并替换 ( 阿里云的，腾讯云的，微信支付的 )，涉及到 nacos 和 sentinel 的端口以及 ip 请自行修改。

## 启动项目

### 前台启动

shenlan_admin 为后台，shenlan_site 为前台，分别：

- npm install
- npm run dev

### 后台启动

