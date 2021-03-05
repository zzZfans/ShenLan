package com.zfans.shenlan.service.vod.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author Zfans
 * @DateTime 2021/03/01 18:40
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.vod")
public class VodProperties {
    private String keyid;
    private String keysecret;
    private String templateGroupId;
    private String workflowId;
}
