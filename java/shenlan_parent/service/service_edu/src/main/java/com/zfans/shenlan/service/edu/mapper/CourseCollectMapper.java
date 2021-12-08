package com.zfans.shenlan.service.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zfans.shenlan.service.edu.entity.CourseCollect;
import com.zfans.shenlan.service.edu.entity.vo.CourseCollectVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 课程收藏 Mapper 接口
 * </p>
 *
 * @author Zfans
 * @since 2021-01-25
 */
@Repository
public interface CourseCollectMapper extends BaseMapper<CourseCollect> {

    List<CourseCollectVo> selectPageByMemberId(String memberId);
}
