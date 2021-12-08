package com.zfans.shenlan.service.edu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zfans.shenlan.service.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 课程收藏
 * </p>
 *
 * @author Zfans
 * @since 2021-01-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("edu_course_collect")
@ApiModel(value="CourseCollect 对象", description="课程收藏")
public class CourseCollect extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "课程讲师ID")
    private String courseId;

    @ApiModelProperty(value = "课程专业ID")
    private String memberId;


}
