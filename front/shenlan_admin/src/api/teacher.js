// @ 符号在build/webpack.base.conf.js 中配置 表示 'src' 路径
import request from "@/utils/request";

export default {
  list() {
    return request({
      url: "/admin/edu/teacher/list",
      method: "get"
    });
  },
  pageList(page, limit, searchObj) {
    return request({
      url: `/admin/edu/teacher/list/${page}/${limit}`,
      method: "get",
      params: searchObj
    });
  }
};
