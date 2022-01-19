# 一、什么是Webpack

​	Webpack 是一个前端资源加载/打包工具。它将根据模块的依赖关系进行静态分析，然后将这些模块按照指定的规则生成对应的静态资源。

从图中我们可以看出，Webpack 可以将多种静态资源 js、css、less 转换成一个静态文件，减少了页面的请求。 

![img](https://gitee.com/zzzfans/Image-Hosting-Service/raw/master/images/win-2022-01-19-23:32:55.png)

# 二、Webpack安装

## 1、全局安装

```javascript
npm install -g webpack webpack-cli
或
npm install -g webpack
npm install -g webpack-cli
```

## 2、安装后查看版本号

 

```javascript
webpack -v
```

# 三、创建项目

**创建 webpack_pro文件夹**

## 1、初始化项目

进入目录，执行命令

```javascript
npm init -y
```

## **2、**创建src文件夹

## 3、**src下**创建common.js

这里使用的是CommonJS模块化方式，这种方式不支持ES6的语法，所以不需要Babel转码

```javascript
exports.info = function (str) {
    document.write(str)
}
```

## **4、src下创建utils.js**

```javascript
exports.add = function (a, b) {
    return a + b
}
```

## **5、src下创建main.js**

```javascript
const common = require('./common')
const utils = require('./utils')
common.info('Hello world!' + utils.add(100, 200))
```

# **四、JS打包**

## **1、创建配置文件**

webpack_pro目录下创建配置文件webpack.config.js

以下配置的意思是：

- 读取当前项目目录下src文件夹中的main.js（入口文件）内容，分析资源依赖，把相关的js文件打包
- 打包后的文件放入当前目录的dist文件夹下
- 打包后的js文件名为bundle.js

```javascript
const path = require("path") //Node.js内置模块
module.exports = {
    entry: './src/main.js', //配置入口文件
    output: {
        path: path.resolve(__dirname, './dist'), //输出路径，__dirname：当前文件所在路径
        filename: 'bundle.js' //输出文件
    }
}
```

## **2、执行编译命令**

```javascript
webpack --mode=development
#执行后查看bundle.js 里面包含了上面两个js文件的内容并进行了代码打包
```

也可以配置项目的npm运行命令，修改package.json文件

```json
"scripts": {
    //...,
    "dev": "webpack --mode=development",
    "prod": "webpack --mode=production"
 }
```

运行npm命令执行打包

```javascript
npm run dev #开发打包
或
npm run prod #生产打包
```

## **3、创建入口页面**

webpack_pro目录下创建index.html，引用bundle.js

```html
<script src="dist/bundle.js"></script>
```

## 4、测试

浏览器中查看index.html

# **五、CSS打包**

## **1、安装插件**

Webpack 本身只能处理 JavaScript 模块，如果要处理其他类型的文件，就需要使用 loader 进行转换。

Loader 可以理解为是模块和资源的转换器。

首先我们需要安装相关Loader插件，css-loader 是将 css 装载到 javascript；style-loader 是让 javascript 认识css

```javascript
npm install -D style-loader css-loader 
```

## **2、修改webpack.config.js**

```javascript
const path = require("path"); //Node.js内置模块
module.exports = {
    //...,
    output:{
        //其他配置
    },
    module: {
        rules: [  
            {  
                test: /\.css$/,    //打包规则应用到以css结尾的文件上
                use: ['style-loader', 'css-loader']
            }  
        ]  
    }
}
```

## **3、在src文件夹**创建style.css

```css
body{
    background:pink;
}
```

## **4、修改main.js** 

在第一行引入style.css

```js
require('./style.css')
```

## **5、运行编译命令**

```
npm run dev
```

## 6、测试

浏览器中查看index.html，看看背景是不是变成粉色啦？

