package com.zfans.shenlan.service.vod.controller.api;

import com.zfans.shenlan.common.base.result.R;
import com.zfans.shenlan.common.base.result.ResultCodeEnum;
import com.zfans.shenlan.service.base.exception.ShenlanException;
import com.zfans.shenlan.service.vod.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @Author Zfans
 * @DateTime 2021/3/17 19:35
 */
@Api(tags = "阿里云视频点播")
// 跨域
// @CrossOrigin
@RestController
@RequestMapping("/api/vod/media")
@Slf4j
public class ApiMediaController {

    @Autowired
    private VideoService videoService;

    @ApiOperation("获取播放凭证")
    @GetMapping("get-play-auth/{videoSourceId}")
    public R getPlayAuth(
            @ApiParam(value = "阿里云视频文件的 id", required = true)
            @PathVariable String videoSourceId) {

        try {
            String playAuth = videoService.getPlayAuth(videoSourceId);
            return R.ok().message("获取播放凭证成功").data("playAuth", playAuth);
        } catch (Exception e) {
            log.error(Arrays.toString(e.getStackTrace()));
            throw new ShenlanException(ResultCodeEnum.FETCH_PLAYAUTH_ERROR);
        }
    }
}
