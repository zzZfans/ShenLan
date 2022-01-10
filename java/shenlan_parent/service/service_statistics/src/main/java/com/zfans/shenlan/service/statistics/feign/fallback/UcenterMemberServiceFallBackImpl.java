package com.zfans.shenlan.service.statistics.feign.fallback;

import com.zfans.shenlan.common.base.result.R;
import com.zfans.shenlan.service.statistics.feign.UcenterMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author 凡森
 * @Date 2022/1/10
 */
@Service
@Slf4j
public class UcenterMemberServiceFallBackImpl implements UcenterMemberService {
    @Override
    public R countRegisterNum(String day) {
        // 错误日志
        log.error("熔断器被执行");
        return R.ok().data("registerNum", 0);
    }
}
