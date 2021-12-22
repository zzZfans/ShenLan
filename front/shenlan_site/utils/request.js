import axios from 'axios'
import { Message } from 'element-ui'
import cookie from 'js-cookie'
// 创建 axios 实例
const service = axios.create({
  baseURL: 'http://localhost:8110',
  timeout: 12000 // 请求超时时间
})

// http request 拦截器
service.interceptors.request.use(
  config => {
    if (cookie.get('shenlan_jwt_token')) {
      // 发送后端 api 请求的时候携带 token
      config.headers['token'] = cookie.get('shenlan_jwt_token')
    }
    return config
  },
  error => {
    return Promise.reject(error)
  }
)

// response 拦截器
service.interceptors.response.use(
  response => {
    /**
     * code 为非 20000 是抛错 可结合自己业务进行修改
     */
    const res = response.data

    if (res.code === 20000) { // 正确结果
      return response.data
    } else if (res.code === 23004) { // 获取用户信息失败
      // 清除 cookie
      cookie.set('shenlan_jwt_token', '', { domain: 'localhost' })
      return response.data
    } else if (res.code === 25000) { // 支付中
      // 不显示错误信息
      return response.data
    } else { // 其他错误
      Message({
        message: res.message || 'error',
        type: 'error',
        duration: 5 * 1000
      })
      return Promise.reject('error')
    }
  },
  error => {
    console.log('err：' + error) // for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
