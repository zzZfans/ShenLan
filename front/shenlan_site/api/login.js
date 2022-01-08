import request from '~/utils/request'

export default {
  submitLogin(user) {
    return request({
      // baseURL: 'http://localhost:8160',
      url: '/api/ucenter/member/login',
      method: 'post',
      data: user
    })
  },

  getLoginInfo() {
    return request({
      // baseURL: 'http://localhost:8160',
      url: '/api/ucenter/member/get-login-info',
      method: 'get'
    })
  }
}
