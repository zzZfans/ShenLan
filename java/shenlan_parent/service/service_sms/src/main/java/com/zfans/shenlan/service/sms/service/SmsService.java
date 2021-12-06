package com.zfans.shenlan.service.sms.service;

import com.tencentcloudapi.common.exception.TencentCloudSDKException;

/**
 * @Author Zfans
 * @DateTime 2021/3/24 21:50
 */
public interface SmsService {

    void send(String mobile, String checkCode,Long timeOut) throws TencentCloudSDKException;
}
