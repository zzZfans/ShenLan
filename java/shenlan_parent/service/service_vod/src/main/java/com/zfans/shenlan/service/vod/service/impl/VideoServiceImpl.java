package com.zfans.shenlan.service.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.zfans.shenlan.common.base.result.ResultCodeEnum;
import com.zfans.shenlan.service.base.exception.ShenlanException;
import com.zfans.shenlan.service.vod.service.VideoService;
import com.zfans.shenlan.service.vod.util.AliyunVodSDKUtils;
import com.zfans.shenlan.service.vod.util.VodProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.List;

/**
 * @Author Zfans
 * @DateTime 2021/03/01 19:08
 */
@Service
@Slf4j
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VodProperties vodProperties;

    @Override
    public String uploadVideo(InputStream inputStream, String originalFilename) {

        String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));

        UploadStreamRequest request = new UploadStreamRequest(
                vodProperties.getKeyid(),
                vodProperties.getKeysecret(),
                title, originalFilename, inputStream);

        /* 模板组ID(可选) */
        // request.setTemplateGroupId(vodProperties.getTemplateGroupId());
        /* 工作流ID(可选) */
        // request.setWorkflowId(vodProperties.getWorkflowId());

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);

        String videoId = response.getVideoId();
        //没有正确的返回videoid则说明上传失败
        if (StringUtils.isEmpty(videoId)) {
            log.error("阿里云上传失败：" + response.getCode() + " - " + response.getMessage());
            throw new ShenlanException(ResultCodeEnum.VIDEO_UPLOAD_ALIYUN_ERROR);
        }

        return videoId;
    }

    @Override
    public void removeVideo(String videoId) throws ClientException {

        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                vodProperties.getKeyid(),
                vodProperties.getKeysecret());

        DeleteVideoRequest request = new DeleteVideoRequest();
        request.setVideoIds(videoId);
        client.getAcsResponse(request);
    }

    @Override
    public void removeVideoByIdList(List<String> videoIdList) throws ClientException {

        //初始化client对象
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                vodProperties.getKeyid(),
                vodProperties.getKeysecret());

        DeleteVideoRequest request = new DeleteVideoRequest();

        int size = videoIdList.size();

        StringBuffer idListStr = new StringBuffer();

        for (int i = 0; i < size; i++) {
            idListStr.append(videoIdList.get(i));
            if (i == size - 1 || i % 20 == 19) {
                //支持传入多个视频ID，多个用逗号分隔，最多20个
                request.setVideoIds(idListStr.toString());
                client.getAcsResponse(request);
                idListStr = new StringBuffer();
            } else {
                idListStr.append(",");
            }
        }
    }
}
