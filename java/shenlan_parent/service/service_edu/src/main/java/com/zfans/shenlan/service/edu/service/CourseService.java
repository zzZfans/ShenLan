package com.zfans.shenlan.service.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zfans.shenlan.service.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zfans.shenlan.service.edu.entity.form.CourseInfoForm;
import com.zfans.shenlan.service.edu.entity.vo.CoursePublishVo;
import com.zfans.shenlan.service.edu.entity.vo.CourseQueryVo;
import com.zfans.shenlan.service.edu.entity.vo.CourseVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Zfans
 * @since 2021-01-25
 */
public interface CourseService extends IService<Course> {
    /**
     * 保存课程和课程详情信息
     *
     * @param courseInfoForm
     * @return 新生成的课程id
     */
    String saveCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfoById(String id);

    void updateCourseInfoById(CourseInfoForm courseInfoForm);

    IPage<CourseVo> selectPage(Long page, Long limit, CourseQueryVo courseQueryVo);

    boolean removeCoverById(String id);

    boolean removeCourseById(String id);

    CoursePublishVo getCoursePublishVoById(String id);

    boolean publishCourseById(String id);
}
