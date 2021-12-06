package com.zfans.shenlan.service.trade.feign.fallback;

import com.zfans.shenlan.service.base.dto.MemberDto;
import com.zfans.shenlan.service.trade.feign.UcenterMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author 凡森
 * @Date 2021/12/4
 */
@Service
@Slf4j
public class UcenterMemberServiceFallBackImpl implements UcenterMemberService {
    @Override
    public MemberDto getMemberDtoByMemberId(String memberId) {
        log.info("熔断保护");
        return null;
    }
}
