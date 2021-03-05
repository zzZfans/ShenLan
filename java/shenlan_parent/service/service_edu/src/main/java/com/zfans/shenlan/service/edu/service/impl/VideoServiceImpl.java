package com.zfans.shenlan.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zfans.shenlan.service.edu.entity.Video;
import com.zfans.shenlan.service.edu.feign.VodMediaService;
import com.zfans.shenlan.service.edu.mapper.VideoMapper;
import com.zfans.shenlan.service.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author Zfans
 * @since 2021-01-25
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Autowired
    private VodMediaService vodMediaService;

    @Override
    public void removeMediaVideoById(String id) {
        vodMediaService.removeVideo(baseMapper.selectById(id).getVideoSourceId());
    }

    @Override
    public void removeMediaVideoByChapterId(String chapterId) {

        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("video_source_id");
        queryWrapper.eq("chapter_id", chapterId);

        vodMediaService.removeVideoByIdList(
                this.getVideoSourceIdList(
                        baseMapper.selectMaps(queryWrapper)
                ));
    }

    /**
     * 获取阿里云视频id列表
     */
    private List<String> getVideoSourceIdList(List<Map<String, Object>> maps) {
        List<String> videoSourceIdList = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            String videoSourceId = (String) map.get("video_source_id");
            videoSourceIdList.add(videoSourceId);
        }
        return videoSourceIdList;
    }

    @Override
    public void removeMediaVideoByCourseId(String courseId) {

        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("video_source_id");
        queryWrapper.eq("course_id", courseId);

        vodMediaService.removeVideoByIdList(
                this.getVideoSourceIdList(
                        baseMapper.selectMaps(queryWrapper)
                ));
    }
}
