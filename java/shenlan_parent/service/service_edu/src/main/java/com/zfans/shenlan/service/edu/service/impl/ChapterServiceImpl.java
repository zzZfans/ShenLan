package com.zfans.shenlan.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zfans.shenlan.service.edu.entity.Chapter;
import com.zfans.shenlan.service.edu.entity.Video;
import com.zfans.shenlan.service.edu.entity.vo.ChapterVo;
import com.zfans.shenlan.service.edu.entity.vo.VideoVo;
import com.zfans.shenlan.service.edu.mapper.ChapterMapper;
import com.zfans.shenlan.service.edu.mapper.VideoMapper;
import com.zfans.shenlan.service.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Zfans
 * @since 2021-01-25
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private VideoMapper videoMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeChapterById(String id) {

        //课时信息：video
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("chapter_id", id);
        videoMapper.delete(videoQueryWrapper);

        //章节信息：chapter
        return this.removeById(id);
    }

    @Override
    public List<ChapterVo> nestedList(String courseId) {

        List<ChapterVo> chapterVoList = new ArrayList<>();

        //获取章信息
        QueryWrapper<Chapter> queryWrapperChapter = new QueryWrapper<>();
        queryWrapperChapter.eq("course_id", courseId);
        queryWrapperChapter.orderByAsc("sort", "id");
        List<Chapter> chapterList = baseMapper.selectList(queryWrapperChapter);

        //获取课时信息
        QueryWrapper<Video> queryWrapperVideo = new QueryWrapper<>();
        queryWrapperVideo.eq("course_id", courseId);
        queryWrapperVideo.orderByAsc("sort", "id");
        List<Video> videoList = videoMapper.selectList(queryWrapperVideo);

        //填充列表数据：Chapter列表
        chapterList.forEach(chapter -> {

            //创建ChapterVo对象
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            chapterVoList.add(chapterVo);

            //填充列表数据：Video列表
            List<VideoVo> videoVoList = new ArrayList<>();

            videoList.forEach(video -> {
                if (chapter.getId().equals(video.getChapterId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video, videoVo);
                    videoVoList.add(videoVo);
                }
            });

            chapterVo.setChildren(videoVoList);
        });

        return chapterVoList;
    }
}
