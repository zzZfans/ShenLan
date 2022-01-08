package com.zfans.shenlan.service.sms.controller.api;

import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.zfans.shenlan.common.base.result.R;
import com.zfans.shenlan.common.base.result.ResultCodeEnum;
import com.zfans.shenlan.common.base.util.FormUtils;
import com.zfans.shenlan.common.base.util.RandomUtils;
import com.zfans.shenlan.service.base.exception.ShenlanException;
import com.zfans.shenlan.service.sms.service.SmsService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author Zfans
 * @DateTime 2021/3/24 21:49
 */
@RestController
@RequestMapping("/api/sms")
@Api(tags = "短信管理")
// @CrossOrigin // 跨域
@Slf4j
public class ApiSmsController {

    @Autowired
    private SmsService smsService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("send/{mobile}")
    public R getCode(@PathVariable String mobile) throws TencentCloudSDKException {

        // 校验手机号是否合法
        if (StringUtils.isEmpty(mobile) || !FormUtils.isMobile(mobile)) {
            log.error("手机号码非法！");
            throw new ShenlanException(ResultCodeEnum.LOGIN_PHONE_ERROR);
        }

        long timeOut = 5L;

        // 生成验证码
        String checkCode = RandomUtils.getFourBitRandom();
        // 发送验证码
        // smsService.send(mobile, checkCode, timeOut);
        // 将验证码存入 redis 缓存
        redisTemplate.opsForValue().set(
                "SMS::".concat(mobile),
                checkCode,
                timeOut,
                TimeUnit.MINUTES);

        return R.ok().message("短信发送成功！");
    }
}
