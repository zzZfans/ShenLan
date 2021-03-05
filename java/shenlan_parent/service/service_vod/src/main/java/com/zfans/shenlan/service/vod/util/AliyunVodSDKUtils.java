package com.zfans.shenlan.service.vod.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @Author Zfans
 * @DateTime 2021/03/03 20:41
 */
public class AliyunVodSDKUtils {

    public static DefaultAcsClient initVodClient(
            String accessKeyId,
            String accessKeySecret) {
        String regionId = "cn-shanghai";  // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(
                regionId,
                accessKeyId,
                accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }
}
