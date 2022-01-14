-- statistics_daily: table
CREATE TABLE `statistics_daily`
(
    `id`              char(19)    NOT NULL COMMENT '主键',
    `date_calculated` varchar(20) NOT NULL COMMENT '统计日期',
    `register_num`    int         NOT NULL DEFAULT '0' COMMENT '注册人数',
    `login_num`       int         NOT NULL DEFAULT '0' COMMENT '登录人数',
    `video_view_num`  int         NOT NULL DEFAULT '0' COMMENT '每日播放视频数',
    `course_num`      int         NOT NULL DEFAULT '0' COMMENT '每日新增课程数',
    `create_time`     datetime    NOT NULL COMMENT '创建时间',
    `update_time`     datetime    NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `statistics_day` (`date_calculated`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='网站统计日数据';

-- No native definition for element: statistics_day (index)


