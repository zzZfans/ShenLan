-- trade_order: table
CREATE TABLE `trade_order`
(
    `id`           char(19)         NOT NULL DEFAULT '',
    `order_no`     varchar(20)      NOT NULL DEFAULT '' COMMENT '订单号',
    `course_id`    varchar(19)      NOT NULL DEFAULT '' COMMENT '课程id',
    `course_title` varchar(100)              DEFAULT NULL COMMENT '课程名称',
    `course_cover` varchar(255)              DEFAULT NULL COMMENT '课程封面',
    `teacher_name` varchar(20)               DEFAULT NULL COMMENT '讲师名称',
    `member_id`    varchar(19)      NOT NULL DEFAULT '' COMMENT '会员id',
    `nickname`     varchar(50)               DEFAULT NULL COMMENT '会员昵称',
    `mobile`       varchar(11)               DEFAULT NULL COMMENT '会员手机',
    `total_fee`    decimal(20, 2)            DEFAULT NULL COMMENT '订单金额（分）',
    `pay_type`     tinyint                   DEFAULT NULL COMMENT '支付类型（1：微信 2：支付宝）',
    `status`       tinyint                   DEFAULT NULL COMMENT '订单状态（0：未支付 1：已支付）',
    `is_deleted`   tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
    `create_time`  datetime         NOT NULL COMMENT '创建时间',
    `update_time`  datetime         NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `ux_order_no` (`order_no`),
    KEY `idx_course_id` (`course_id`),
    KEY `idx_member_id` (`member_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='订单';

-- No native definition for element: idx_course_id (index)

-- No native definition for element: idx_member_id (index)

-- trade_pay_log: table
CREATE TABLE `trade_pay_log`
(
    `id`             char(19)         NOT NULL DEFAULT '',
    `order_no`       varchar(20)      NOT NULL DEFAULT '' COMMENT '订单号',
    `pay_time`       datetime                  DEFAULT NULL COMMENT '支付完成时间',
    `total_fee`      bigint                    DEFAULT NULL COMMENT '支付金额（分）',
    `transaction_id` varchar(30)               DEFAULT NULL COMMENT '交易流水号',
    `trade_state`    char(20)                  DEFAULT NULL COMMENT '交易状态',
    `pay_type`       tinyint          NOT NULL DEFAULT '0' COMMENT '支付类型（1：微信 2：支付宝）',
    `attr`           text COMMENT '其他属性',
    `is_deleted`     tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
    `create_time`    datetime         NOT NULL COMMENT '创建时间',
    `update_time`    datetime         NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_order_no` (`order_no`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='支付日志表';


