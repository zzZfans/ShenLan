import request from '~/utils/request'

export default {

  getList(searchObj) {
    return request({
      url: '/api/edu/course/list',
      method: 'get',
      params: searchObj
    })
  },
  getSubjectNestedList() {
    return request({
      url: '/api/edu/subject/nested-list',
      method: 'get'
    })
  },
  getById(id) {
    return request({
      url: `/api/edu/course/get/${id}`,
      method: 'get'
    })
  },
  getPlayAuth(vid) {
    return request({
      baseURL: 'http://localhost:8130',
      url: `/api/vod/media/get-play-auth/${vid}`,
      method: 'get'
    })
  }
}
