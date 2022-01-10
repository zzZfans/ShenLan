package com.zfans.shenlan.service.statistics.feign;

import com.zfans.shenlan.common.base.result.R;
import com.zfans.shenlan.service.statistics.feign.fallback.UcenterMemberServiceFallBackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author 凡森
 * @Date 2022/1/10
 */
@FeignClient(value = "service-ucenter", fallback = UcenterMemberServiceFallBackImpl.class)
public interface UcenterMemberService {

    @GetMapping(value = "/admin/ucenter/member/count-register-num/{day}")
    R countRegisterNum(@PathVariable("day") String day);
}
