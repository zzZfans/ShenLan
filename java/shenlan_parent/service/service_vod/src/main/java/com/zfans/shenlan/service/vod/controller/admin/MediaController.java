package com.zfans.shenlan.service.vod.controller.admin;

import com.zfans.shenlan.common.base.result.R;
import com.zfans.shenlan.common.base.result.ResultCodeEnum;
import com.zfans.shenlan.service.base.exception.ShenlanException;
import com.zfans.shenlan.service.vod.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Zfans
 * @DateTime 2021/03/02 00:28
 */
@Api(tags = "阿里云视频点播")
// @CrossOrigin //跨域
@RestController
@RequestMapping("/admin/vod/media")
@Slf4j
public class MediaController {

    @Autowired
    private VideoService videoService;

    @PostMapping("upload")
    public R uploadVideo(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) {
        try {
            String videoId = videoService.uploadVideo(
                    file.getInputStream(),
                    file.getOriginalFilename());
            return R.ok().message("视频上传成功").data("videoId", videoId);
        } catch (IOException e) {
            log.error(Arrays.toString(e.getStackTrace()));
            throw new ShenlanException(ResultCodeEnum.VIDEO_UPLOAD_TOMCAT_ERROR);
        }
    }

    @DeleteMapping("remove/{vodId}")
    public R removeVideo(
            @ApiParam(value = "阿里云视频id", required = true)
            @PathVariable String vodId) {
        try {
            videoService.removeVideo(vodId);
            return R.ok().message("视频删除成功");
        } catch (Exception e) {
            log.error(Arrays.toString(e.getStackTrace()));
            throw new ShenlanException(ResultCodeEnum.VIDEO_DELETE_ALIYUN_ERROR);
        }
    }

    @DeleteMapping("remove")
    public R removeVideoByIdList(
            @ApiParam(value = "阿里云视频id列表", required = true)
            @RequestBody List<String> videoIdList) {
        try {
            videoService.removeVideoByIdList(videoIdList);
            return R.ok().message("视频删除成功");
        } catch (Exception e) {
            log.error(Arrays.toString(e.getStackTrace()));
            throw new ShenlanException(ResultCodeEnum.VIDEO_DELETE_ALIYUN_ERROR);
        }
    }

}
