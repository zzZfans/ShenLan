-- ucenter_member: table
CREATE TABLE `ucenter_member`
(
    `id`          char(19)   NOT NULL COMMENT '会员id',
    `openid`      varchar(128)        DEFAULT NULL COMMENT '微信openid',
    `mobile`      varchar(11)         DEFAULT '' COMMENT '手机号',
    `password`    varchar(255)        DEFAULT NULL COMMENT '密码',
    `nickname`    varchar(50)         DEFAULT NULL COMMENT '昵称',
    `sex`         tinyint unsigned    DEFAULT NULL COMMENT '性别 1 男，2 女',
    `age`         tinyint unsigned    DEFAULT NULL COMMENT '年龄',
    `avatar`      varchar(255)        DEFAULT NULL COMMENT '用户头像',
    `sign`        varchar(100)        DEFAULT NULL COMMENT '用户签名',
    `is_disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用 1（true）已禁用，  0（false）未禁用',
    `is_deleted`  tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
    `create_time` datetime   NOT NULL COMMENT '创建时间',
    `update_time` datetime   NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='会员表';


