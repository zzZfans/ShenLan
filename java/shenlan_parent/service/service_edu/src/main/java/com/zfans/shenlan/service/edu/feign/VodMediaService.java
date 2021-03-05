package com.zfans.shenlan.service.edu.feign;

import com.zfans.shenlan.common.base.result.R;
import com.zfans.shenlan.service.edu.feign.fallback.VodMediaServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author Zfans
 * @DateTime 2021/03/04 10:41
 */
@Service
@FeignClient(value = "service-vod", fallback = VodMediaServiceFallBack.class)
public interface VodMediaService {

    @DeleteMapping("/admin/vod/media/remove/{vodId}")
    R removeVideo(@PathVariable("vodId") String vodId);

    @DeleteMapping("/admin/vod/media/remove")
    R removeVideoByIdList(@RequestBody List<String> videoIdList);
}
