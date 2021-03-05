import request from "@/utils/request";

export default {

  getNestedTreeList(courseId) {
    return request({
      url: `/admin/edu/chapter/nested-list/${courseId}`,
      method: "get"
    });
  },

  removeById(id) {
    return request({
      url: `/admin/edu/chapter/remove/${id}`,
      method: "delete"
    });
  },

  save(chapter) {
    return request({
      url: "/admin/edu/chapter/save",
      method: "post",
      data: chapter
    });
  },

  getById(id) {
    return request({
      url: `/admin/edu/chapter/get/${id}`,
      method: "get"
    });
  },

  updateById(chapter) {
    return request({
      url: "/admin/edu/chapter/update",
      method: "put",
      data: chapter
    });
  }
};
