-- cms_ad: table
CREATE TABLE `cms_ad`
(
    `id`          char(19)     NOT NULL DEFAULT '' COMMENT 'ID',
    `title`       varchar(20)           DEFAULT '' COMMENT '标题',
    `type_id`     char(19)     NOT NULL COMMENT '类型ID',
    `image_url`   varchar(500) NOT NULL DEFAULT '' COMMENT '图片地址',
    `color`       varchar(10)           DEFAULT NULL COMMENT '背景颜色',
    `link_url`    varchar(500)          DEFAULT '' COMMENT '链接地址',
    `sort`        int unsigned NOT NULL DEFAULT '0' COMMENT '排序',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    `update_time` datetime     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_name` (`title`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='广告推荐';

-- cms_ad_type: table
CREATE TABLE `cms_ad_type`
(
    `id`          char(19)    NOT NULL COMMENT 'ID',
    `title`       varchar(20) NOT NULL COMMENT '标题',
    `create_time` datetime    NOT NULL COMMENT '创建时间',
    `update_time` datetime    NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='推荐位';


