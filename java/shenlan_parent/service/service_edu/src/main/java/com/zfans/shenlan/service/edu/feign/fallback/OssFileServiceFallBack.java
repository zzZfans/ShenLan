package com.zfans.shenlan.service.edu.feign.fallback;

import com.zfans.shenlan.common.base.result.R;
import com.zfans.shenlan.service.edu.feign.OssFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author Zfans
 * @DateTime 2021/02/05 12:59
 */
@Service
@Slf4j
public class OssFileServiceFallBack implements OssFileService {

    @Override
    public R test() {
        log.info("进入 FallBack! ");
        return R.error();
    }

    @Override
    public R removeFile(String url) {
        log.info("熔断保护");
        return R.error();
    }
}
