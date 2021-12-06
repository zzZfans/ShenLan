import request from '~/utils/request'

export default {

  sendMessage(mobile) {
    return request({
      baseURL: 'http://localhost:8150',
      url: `/api/sms/send/${mobile}`,
      method: 'get'
    })
  },

  register(member) {
    return request({
      baseURL: 'http://localhost:8160',
      url: '/api/ucenter/member/register',
      method: 'post',
      data: member
    })
  }
}
