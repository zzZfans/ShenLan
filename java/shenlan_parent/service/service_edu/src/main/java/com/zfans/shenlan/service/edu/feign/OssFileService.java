package com.zfans.shenlan.service.edu.feign;

import com.zfans.shenlan.common.base.result.R;
import com.zfans.shenlan.service.edu.feign.fallback.OssFileServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author Zfans
 * @DateTime 2021/02/03 00:56
 */
@Service
@FeignClient(value = "service-oss"
        , fallback = OssFileServiceFallBack.class)
public interface OssFileService {

    @GetMapping("/admin/oss/file/test")
    R test();

    @DeleteMapping("/admin/oss/file/remove")
    R removeFile(@RequestBody String url);
}
