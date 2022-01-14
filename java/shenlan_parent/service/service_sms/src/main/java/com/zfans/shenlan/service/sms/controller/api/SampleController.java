package com.zfans.shenlan.service.sms.controller.api;

import com.zfans.shenlan.common.base.result.R;
import com.zfans.shenlan.service.sms.util.SmsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 凡森
 * @Date 2022/1/13
 */
@RestController
@RequestMapping("/sms/sample")
@RefreshScope
public class SampleController {

    @Value("${tencentcloud.sms.signContent}")
    private String signName;

    @Autowired
    private SmsProperties smsProperties;

    @GetMapping("test1")
    public R test1(){
        return R.ok().data("signContent", signName);
    }

    @GetMapping("test2")
    public R test2(){
        return R.ok().data("smsProperties", smsProperties);
    }
}
