package com.zfans.shenlan.service.cms.feign.fallback;

import com.zfans.shenlan.common.base.result.R;
import com.zfans.shenlan.service.cms.feign.OssFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author Zfans
 * @DateTime 2021/3/21 11:48
 */
@Service
@Slf4j
public class OssFileServiceFallBackImpl implements OssFileService {

    @Override
    public R removeFile(String url) {
        log.info("熔断保护");
        return R.error().message("调用超时");
    }
}
