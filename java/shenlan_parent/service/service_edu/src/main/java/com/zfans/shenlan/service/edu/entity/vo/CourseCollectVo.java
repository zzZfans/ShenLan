package com.zfans.shenlan.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author 凡森
 * @Date 2021/12/8
 */
@Data
public class CourseCollectVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    // 课程 id
    private String courseId;
    // 标题
    private String title;
    // 价格
    private BigDecimal price;
    // 课时数
    private Integer lessonNum;
    // 封面
    private String cover;
    // 收藏时间
    private String createTime;
    // 讲师
    private String teacherName;
}
