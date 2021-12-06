package com.zfans.shenlan.service.trade.feign;

import com.zfans.shenlan.service.base.dto.MemberDto;
import com.zfans.shenlan.service.trade.feign.fallback.UcenterMemberServiceFallBackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author 凡森
 * @Date 2021/12/4
 */
@Service
@FeignClient(value = "service-ucenter", fallback = UcenterMemberServiceFallBackImpl.class)
public interface UcenterMemberService {

    @GetMapping(value = "/api/ucenter/member/inner/get-member-dto/{memberId}")
    MemberDto getMemberDtoByMemberId(@PathVariable(value = "memberId") String memberId);
}
