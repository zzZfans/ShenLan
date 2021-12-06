package com.zfans.shenlan.service.sms.service.impl;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import com.zfans.shenlan.common.base.result.ResultCodeEnum;
import com.zfans.shenlan.service.base.exception.ShenlanException;
import com.zfans.shenlan.service.sms.service.SmsService;
import com.zfans.shenlan.service.sms.util.SmsProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Zfans
 * @DateTime 2021/3/24 21:51
 */
@Service
@Slf4j
public class SmsServiceImpl implements SmsService {

    @Autowired
    private SmsProperties smsProperties;

    @Override
    public void send(String mobile, String checkCode, Long timeOut) throws TencentCloudSDKException {

        Credential cred = new Credential(
                smsProperties.getSecretId(),
                smsProperties.getSecretKey());

        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("sms.tencentcloudapi.com");

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);

        SmsClient client = new SmsClient(cred, "", clientProfile);

        SendSmsRequest req = new SendSmsRequest();
        String[] phoneNumberSet1 = {"+86".concat(mobile)};
        req.setPhoneNumberSet(phoneNumberSet1);

        req.setTemplateID(smsProperties.getTemplateID());
        req.setSign(smsProperties.getSignContent());

        String[] templateParamSet1 = {checkCode, timeOut.toString()};
        req.setTemplateParamSet(templateParamSet1);

        req.setSmsSdkAppid(smsProperties.getAppId());

        SendSmsResponse resp = client.SendSms(req);

        String code = resp.getSendStatusSet()[0].getCode();
        String message = resp.getSendStatusSet()[0].getMessage();

        // 错误码参考：
        // https://cloud.tencent.com/document/product/382/38780
        // 控制所有短信流向限制（同一手机号：30秒不超过一条、一个小时不超过五条、一天不超过十条）
        if (code.contains("LimitExceeded")) {
            log.error("短信发送过于频繁 " + "【code】" + code + ", 【message】" + message);
            throw new ShenlanException(ResultCodeEnum.SMS_SEND_ERROR_BUSINESS_LIMIT_CONTROL);
        }

        if (!"Ok".equals(code)) {
            log.error("短信发送失败 " + " - code: " + code + ", message: " + message);
            throw new ShenlanException(ResultCodeEnum.SMS_SEND_ERROR);
        }

        // System.out.println(SendSmsResponse.toJsonString(resp));
    }
}
