package com.zfans.shenlan.service.sms.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author Zfans
 * @DateTime 2021/3/24 19:21
 */
@Data
@Component
//注意prefix要写到最后一个 "." 符号之前
@ConfigurationProperties(prefix="tencentcloud.sms")
public class SmsProperties {
    private String secretId;
    private String secretKey;
    private String appId;
    private String templateID;
    private String signContent;
}
