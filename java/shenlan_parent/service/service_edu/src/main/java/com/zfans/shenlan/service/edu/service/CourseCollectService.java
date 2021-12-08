package com.zfans.shenlan.service.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zfans.shenlan.service.edu.entity.CourseCollect;
import com.zfans.shenlan.service.edu.entity.vo.CourseCollectVo;

import java.util.List;

/**
 * <p>
 * 课程收藏 服务类
 * </p>
 *
 * @author Zfans
 * @since 2021-01-25
 */
public interface CourseCollectService extends IService<CourseCollect> {

    boolean isCollect(String courseId, String memberId);

    void saveCourseCollect(String courseId, String memberId);

    List<CourseCollectVo> selectListByMemberId(String memberId);

    boolean removeCourseCollect(String courseId, String memberId);
}
