package com.zfans.shenlan.service.trade.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author 凡森
 * @Date 2021/12/12
 */
@Data
@Component
@ConfigurationProperties(prefix="weixin.pay")
public class WeixinPayProperties {
    private String appId;
    private String partner;
    private String partnerKey;
    private String notifyUrl;
}
