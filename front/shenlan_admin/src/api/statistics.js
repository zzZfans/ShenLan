import request from "@/utils/request";

export default {

  createStatistics(day) {
    return request({
      // baseURL: 'http://127.0.0.1:8180',
      url: `/admin/statistics/daily/create/${day}`,
      method: "post"
    });
  },
  showChart(searchObj) {
    return request({
      // baseURL: 'http://127.0.0.1:8180',
      url: `/admin/statistics/daily/show-chart/${searchObj.begin}/${searchObj.end}`,
      method: "get"
    });
  }
};
