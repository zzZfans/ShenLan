package com.zfans.shenlan.service.base.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author 凡森
 * @Date 2021/12/4
 */
@Data
public class CourseDto implements Serializable {

    private static final long serialVersionUID = 1L;
    // 课程 ID
    private String id;
    // 课程标题
    private String title;
    // 课程销售价格，设置为 0 则可免费观看
    private BigDecimal price;
    // 课程封面图片路径
    private String cover;
    // 课程讲师
    private String teacherName;
}
