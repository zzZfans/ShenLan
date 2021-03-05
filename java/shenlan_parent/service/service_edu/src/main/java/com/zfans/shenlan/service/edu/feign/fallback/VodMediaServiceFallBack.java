package com.zfans.shenlan.service.edu.feign.fallback;

import com.zfans.shenlan.common.base.result.R;
import com.zfans.shenlan.service.edu.feign.VodMediaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Zfans
 * @DateTime 2021/03/04 10:42
 */
@Service
@Slf4j
public class VodMediaServiceFallBack implements VodMediaService {
    @Override
    public R removeVideo(String vodId) {
        log.info("熔断保护");
        return R.error();
    }

    @Override
    public R removeVideoByIdList(List<String> videoIdList) {
        log.info("熔断保护");
        return R.error().message("调用超时");
    }
}
