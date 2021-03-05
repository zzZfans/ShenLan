package com.zfans.shenlan.service.edu.service;

import com.zfans.shenlan.service.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zfans.shenlan.service.edu.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Zfans
 * @since 2021-01-25
 */
public interface ChapterService extends IService<Chapter> {

    boolean removeChapterById(String id);

    List<ChapterVo> nestedList(String courseId);
}
