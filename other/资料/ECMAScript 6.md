# **一、ECMAScript 6**

## 1、什么是 ECMAScript 6

ECMAScript 6.0（简称 ES6）是 JavaScript 语言的下一代标准， 2015 年 6 月正式发布。它的目标，是使得 JavaScript 语言可以用来编写复杂的大型应用程序，成为企业级开发语言。

## 2、ECMAScript 和 JavaScript 的关系

一个常见的问题是，ECMAScript 和 JavaScript 到底是什么关系？

要讲清楚这个问题，需要回顾历史。1996 年 11 月，JavaScript 的创造者 Netscape 公司，决定将 JavaScript 提交给标准化组织 ECMA，希望这种语言能够成为国际标准。次年，ECMA 发布 262 号标准文件（ECMA-262）的第一版，规定了浏览器脚本语言的标准，并将这种语言称为 ECMAScript，这个版本就是 1.0 版。

因此，ECMAScript 和 JavaScript 的关系是，前者是后者的规格，后者是前者的一种实现（另外的 ECMAScript 方言还有 Jscript 和 ActionScript）



# 二、基本语法

ES6相对之前的版本语法更严格，新增了面向对象的很多特性以及一些高级特性。本部分只学习项目开发中涉及到ES6的最少必要知识，方便项目开发中对代码的理解。

**创建文件夹es6_pro**

## 1、let声明变量

创建 01-let-01.js

```javascript
// var 声明的变量没有局部作用域
// let 声明的变量  有局部作用域
{
    var a = 0
    let b = 1
}
console.log(a)  // 0
console.log(b)  // ReferenceError: b is not defined
```

创建 01-let-02.js

```javascript
// var 可以声明多次
// let 只能声明一次
var m = 1
var m = 2
let n = 3
let n = 4  // Identifier 'n' has already been declared
console.log(m)  // 2
console.log(n) 
```

创建 01-let-03.js

```javascript
// var 会变量提升
// let 不存在变量提升
console.log(x)  //undefined
var x = 'apple'
console.log(y)  //ReferenceError: y is not defined
let y = 'banana'
```

## **2、const声明常量（只读变量）**

创建 02-const.js

```javascript
// 1、声明之后不允许改变    
const PI = '3.1415926'
PI = 3  // TypeError: Assignment to constant variable.
```

```javascript
// 2、一但声明必须初始化，否则会报错
const MY_AGE  // SyntaxError: Missing initializer in const declaration
```

## **3、**解构赋值

创建 03-解构赋值-数组解构.js

解构赋值是对赋值运算符的扩展。

他是一种针对数组或者对象进行模式匹配，然后对其中的变量进行赋值。

在代码书写上简洁且易读，语义更加清晰明了；也方便了复杂对象中数据字段获取。

```javascript
//1、数组解构
let arr = [1, 2, 3]
// 传统
let a = arr[0]
let b = arr[1]
let c = arr[2]
console.log(a, b, c)
// ES6
let [x, y, z] = arr
console.log(x, y, z)
```

创建 03-解构赋值-对象解构.js

```javascript
//2、对象解构
let user = {name: 'Helen', age: 18}
// 传统
let name1 = user.name
let age1 = user.age
console.log(name1, age1)
// ES6
let { name, age } =  user//注意：解构的变量必须和user中的属性同名
console.log(name, age)
```

## **4、模板字符串**

创建 04-模板字符串.js

模板字符串相当于加强版的字符串，用反引号 `，除了作为普通字符串，还可以用来定义多行字符串，还可以在字符串中加入变量和表达式。

```javascript
// 字符串插入变量和表达式。变量名写在 ${} 中，${} 中可以放入 JavaScript 表达式。
let name = 'Mike'
let age = 27
let info = `My Name is ${name},I am ${age+1} years old next year.`
console.log(info)
// My Name is Mike,I am 28 years old next year.
//原样输出
let fun = `function(){
  console.log('hello')
}`
console.log(fun)
```

## **5、声明对象简写**

创建 05-声明对象简写.js

```javascript
let age = 12
let name = 'Amy'
// 传统
let person1 = {
    age: age, 
    name: name
}
console.log(person1)
// ES6
let person2 = {
    age, 
    name
}
console.log(person2) //{age: 12, name: 'Amy'}
```

## **6、定义方法简写**

创建 06-定义方法简写.js

```javascript
// 传统
let person1 = {
    sayHi:function(){
        console.log('Hi')
    }
}
person1.sayHi();//'Hi'
// ES6
let person2 = {
    sayHi(){
        console.log('Hi')
    }
}
person2.sayHi()  //'Hi'
```

## 7、对象拓展运算符

创建 07-对象拓展运算符.js

拓展运算符（...）用于取出参数对象所有可遍历属性然后拷贝到当前对象。

 

```javascript
let person = {name: 'Amy', age: 15}
// let someone = person //引用赋值
let someone = { ...person } //对拷拷贝
someone.name = 'Helen' 
console.log(person)  //{name: 'Amy', age: 15}
console.log(someone)  //{name: 'Helen', age: 15}
```

## 8、函数的默认参数

函数在JavaScript中也是一种数据类型，JavaScript中没有方法的重载

创建 08-函数的默认参数.js

```javascript
function showInfo(name, age = 17) {
    console.log(name + "," + age)
}
// 只有在未传递参数，或者参数为 undefined 时，才会使用默认参数
// null 值被认为是有效的值传递。
showInfo("Amy", 18)  // Amy,18
showInfo("Amy")     // Amy,17
showInfo("Amy", undefined) // Amy,17
showInfo("Amy", null)  // Amy, null
```

## 9、箭头函数

创建 09-箭头函数.js

箭头函数提供了一种更加简洁的函数书写方式。基本语法是：`参数 => 函数体``箭头函数多用于匿名函数的定义`

```javascript
let arr = ["10", "5", "40", "25", "1000"]
arr.sort()
console.log(arr)
//上面的代码没有按照数值的大小对数字进行排序，
//要实现这一点，就必须使用一个排序函数
//若 a 小于 b，在排序后的数组中 a 应该出现在 b 之前，则返回一个小于 0 的值。
//若 a 等于 b，则返回 0。
//若 a 大于 b，则返回一个大于 0 的值。
arr.sort(function(a,b){
    return a - b
})
console.log(arr)
```

使用箭头函数

```javascript
//使用箭头函数
arr2 = arr.sort((a,b) => {
    return a - b
})
```

```javascript
// 当只有一行语句，并且需要返回结果时，可以省略 {} , 结果会自动返回。
//当只有一个参数时，可以不使用圆括号
arr2 = arr.sort((a,b) => a - b)
```

