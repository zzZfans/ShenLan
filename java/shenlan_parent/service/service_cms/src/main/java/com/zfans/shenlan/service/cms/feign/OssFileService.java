package com.zfans.shenlan.service.cms.feign;

import com.zfans.shenlan.common.base.result.R;
import com.zfans.shenlan.service.cms.feign.fallback.OssFileServiceFallBackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author Zfans
 * @DateTime 2021/3/21 11:42
 */
@Service
@FeignClient(value = "service-oss", fallback = OssFileServiceFallBackImpl.class)
public interface OssFileService {

    @DeleteMapping("/admin/oss/file/remove")
    R removeFile(@RequestBody String url);
}
