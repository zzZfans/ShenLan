import request from "@/utils/request";

export default {

  save(video) {
    return request({
      url: "/admin/edu/video/save",
      method: "post",
      data: video
    });
  },

  getById(id) {
    return request({
      url: `/admin/edu/video/get/${id}`,
      method: "get"
    });
  },

  updateById(video) {
    return request({
      url: "/admin/edu/video/update",
      method: "put",
      data: video
    });
  },

  removeById(id) {
    return request({
      url: `/admin/edu/video/remove/${id}`,
      method: "delete"
    });
  }
};
