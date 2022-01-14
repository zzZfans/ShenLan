-- edu_chapter: table
CREATE TABLE `edu_chapter`
(
    `id`          char(19)     NOT NULL COMMENT '章节ID',
    `course_id`   char(19)     NOT NULL COMMENT '课程ID',
    `title`       varchar(50)  NOT NULL COMMENT '章节名称',
    `sort`        int unsigned NOT NULL DEFAULT '0' COMMENT '显示排序',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    `update_time` datetime     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_course_id` (`course_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = COMPACT COMMENT ='课程';

-- No native definition for element: idx_course_id (index)

-- edu_comment: table
CREATE TABLE `edu_comment`
(
    `id`          char(19) NOT NULL COMMENT '讲师ID',
    `course_id`   char(19) NOT NULL DEFAULT '' COMMENT '课程id',
    `teacher_id`  char(19) NOT NULL DEFAULT '' COMMENT '讲师id',
    `member_id`   char(19) NOT NULL DEFAULT '' COMMENT '会员id',
    `nickname`    varchar(50)       DEFAULT NULL COMMENT '会员昵称',
    `avatar`      varchar(255)      DEFAULT NULL COMMENT '会员头像',
    `content`     varchar(500)      DEFAULT NULL COMMENT '评论内容',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_course_id` (`course_id`),
    KEY `idx_teacher_id` (`teacher_id`),
    KEY `idx_member_id` (`member_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='评论';

-- No native definition for element: idx_course_id (index)

-- No native definition for element: idx_teacher_id (index)

-- No native definition for element: idx_member_id (index)

-- edu_course: table
CREATE TABLE `edu_course`
(
    `id`                char(19)                                                NOT NULL COMMENT '课程ID',
    `teacher_id`        char(19)                                                NOT NULL COMMENT '课程讲师ID',
    `subject_id`        char(19)                                                NOT NULL COMMENT '课程专业ID',
    `subject_parent_id` char(19)                                                NOT NULL COMMENT '课程专业父级ID',
    `title`             varchar(50)                                             NOT NULL COMMENT '课程标题',
    `price`             decimal(10, 2) unsigned                                 NOT NULL DEFAULT '0.00' COMMENT '课程销售价格，设置为0则可免费观看',
    `lesson_num`        int unsigned                                            NOT NULL DEFAULT '0' COMMENT '总课时',
    `cover`             varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程封面图片路径',
    `buy_count`         bigint unsigned                                         NOT NULL DEFAULT '0' COMMENT '销售数量',
    `view_count`        bigint unsigned                                         NOT NULL DEFAULT '0' COMMENT '浏览数量',
    `version`           bigint unsigned                                         NOT NULL DEFAULT '1' COMMENT '乐观锁',
    `status`            varchar(10)                                             NOT NULL DEFAULT 'Draft' COMMENT '课程状态 Draft未发布  Normal已发布',
    `create_time`       datetime                                                NOT NULL COMMENT '创建时间',
    `update_time`       datetime                                                NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_title` (`title`),
    KEY `idx_subject_id` (`subject_id`),
    KEY `idx_teacher_id` (`teacher_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = COMPACT COMMENT ='课程';

-- No native definition for element: idx_teacher_id (index)

-- No native definition for element: idx_subject_id (index)

-- No native definition for element: idx_title (index)

-- edu_course_collect: table
CREATE TABLE `edu_course_collect`
(
    `id`          char(19) NOT NULL COMMENT '收藏ID',
    `course_id`   char(19) NOT NULL COMMENT '课程讲师ID',
    `member_id`   char(19) NOT NULL DEFAULT '' COMMENT '课程专业ID',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = COMPACT COMMENT ='课程收藏';

-- edu_course_description: table
CREATE TABLE `edu_course_description`
(
    `id`          char(19) NOT NULL COMMENT '课程ID',
    `description` text COMMENT '课程简介',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='课程简介';

-- edu_subject: table
CREATE TABLE `edu_subject`
(
    `id`          char(19)     NOT NULL COMMENT '课程类别ID',
    `title`       varchar(10)  NOT NULL COMMENT '类别名称',
    `parent_id`   char(19)     NOT NULL DEFAULT '0' COMMENT '父ID',
    `sort`        int unsigned NOT NULL DEFAULT '0' COMMENT '排序字段',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    `update_time` datetime     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_parent_id` (`parent_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = COMPACT COMMENT ='课程科目';

-- No native definition for element: idx_parent_id (index)

-- edu_teacher: table
CREATE TABLE `edu_teacher`
(
    `id`          char(19)         NOT NULL COMMENT '讲师ID',
    `name`        varchar(20)      NOT NULL COMMENT '讲师姓名',
    `intro`       varchar(500)     NOT NULL DEFAULT '' COMMENT '讲师简介',
    `career`      varchar(500)              DEFAULT NULL COMMENT '讲师资历,一句话说明讲师',
    `level`       int unsigned     NOT NULL COMMENT '头衔 1高级讲师 2首席讲师',
    `avatar`      varchar(255)              DEFAULT NULL COMMENT '讲师头像',
    `sort`        int unsigned     NOT NULL DEFAULT '0' COMMENT '排序',
    `join_date`   date                      DEFAULT NULL COMMENT '入驻时间',
    `is_deleted`  tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
    `create_time` datetime         NOT NULL COMMENT '创建时间',
    `update_time` datetime         NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_name` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='讲师';

-- edu_video: table
CREATE TABLE `edu_video`
(
    `id`                  char(19)         NOT NULL COMMENT '视频ID',
    `course_id`           char(19)         NOT NULL COMMENT '课程ID',
    `chapter_id`          char(19)         NOT NULL COMMENT '章节ID',
    `title`               varchar(50)      NOT NULL COMMENT '节点名称',
    `video_source_id`     varchar(100)              DEFAULT NULL COMMENT '云端视频资源',
    `video_original_name` varchar(100)              DEFAULT NULL COMMENT '原始文件名称',
    `sort`                int unsigned     NOT NULL DEFAULT '0' COMMENT '排序字段',
    `play_count`          bigint unsigned  NOT NULL DEFAULT '0' COMMENT '播放次数',
    `is_free`             tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否可以试听：0收费 1免费',
    `duration`            float            NOT NULL DEFAULT '0' COMMENT '视频时长（秒）',
    `status`              varchar(20)      NOT NULL DEFAULT 'Empty' COMMENT '状态',
    `size`                bigint unsigned  NOT NULL DEFAULT '0' COMMENT '视频源文件大小（字节）',
    `version`             bigint unsigned  NOT NULL DEFAULT '1' COMMENT '乐观锁',
    `create_time`         datetime         NOT NULL COMMENT '创建时间',
    `update_time`         datetime         NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_course_id` (`course_id`),
    KEY `idx_chapter_id` (`chapter_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = COMPACT COMMENT ='课程视频';

-- No native definition for element: idx_course_id (index)

-- No native definition for element: idx_chapter_id (index)


