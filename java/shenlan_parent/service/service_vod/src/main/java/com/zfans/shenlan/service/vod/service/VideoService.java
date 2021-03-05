package com.zfans.shenlan.service.vod.service;

import com.aliyuncs.exceptions.ClientException;

import java.io.InputStream;
import java.util.List;

/**
 * @Author Zfans
 * @DateTime 2021/03/01 19:05
 */
public interface VideoService {
    String uploadVideo(InputStream file, String originalFilename);

    void removeVideo(String videoId) throws ClientException;

    void removeVideoByIdList(List<String> videoIdList) throws ClientException;
}
