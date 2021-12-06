package com.zfans.shenlan.service.sms;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import com.zfans.shenlan.service.sms.util.SmsProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author Zfans
 * @DateTime 2021/3/24 21:06
 */
@SpringBootTest
public class TencentSmsTest {

    @Autowired
    private SmsProperties smsProperties;

    @Test
    public void sendSms() {

        try {

            Credential cred = new Credential(
                    smsProperties.getSecretId(),
                    smsProperties.getSecretKey());

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            SmsClient client = new SmsClient(cred, "", clientProfile);

            SendSmsRequest req = new SendSmsRequest();
            String[] phoneNumberSet1 = {"+8613308342326"};
            req.setPhoneNumberSet(phoneNumberSet1);

            req.setTemplateID(smsProperties.getTemplateID());
            req.setSign(smsProperties.getSignContent());

            String[] templateParamSet1 = {"999999","5"};
            req.setTemplateParamSet(templateParamSet1);

            req.setSmsSdkAppid(smsProperties.getAppId());

            SendSmsResponse resp = client.SendSms(req);

            System.out.println(SendSmsResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }
}
