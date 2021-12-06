package com.zfans.shenlan.service.cms.controller.api;

import com.zfans.shenlan.common.base.result.R;
import com.zfans.shenlan.service.cms.entity.Ad;
import com.zfans.shenlan.service.cms.service.AdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author Zfans
 * @DateTime 2021/3/21 18:27
 */
// 解决跨域问题
@CrossOrigin
@Api(tags = "广告推荐")
@RestController
@RequestMapping("/api/cms/ad")
public class ApiAdController {

    @Autowired
    private AdService adService;

    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation("根据推荐位 id 显示广告推荐")
    @GetMapping("list/{adTypeId}")
    public R listByAdTypeId(
            @ApiParam(value = "推荐位id", required = true)
            @PathVariable String adTypeId) {
        List<Ad> ads;
        if (redisTemplate.hasKey("index:ad:" + adTypeId)) {
            ads = (List<Ad>) redisTemplate.opsForValue().get("index:ad:" + adTypeId);
        } else {
            ads = adService.selectByAdTypeId(adTypeId);
            redisTemplate.opsForValue().set("index:ad:" + adTypeId, ads, RandomUtils.nextInt(10) + 1, TimeUnit.MINUTES);
        }
        return R.ok().data("items", ads);
    }

    @PostMapping("save-test")
    public R saveAd(@RequestBody Ad ad) {
        //redisTemplate.opsForValue().set("ad1", ad);
        redisTemplate.opsForValue().set("index:ad:key", ad);
        return R.ok().data(ad.getId(), ad);
    }

    @GetMapping("get-test/{key}")
    public R getAd(@PathVariable String key) {
        Ad ad = (Ad) redisTemplate.opsForValue().get(key);
        return R.ok().data("ad", ad);
    }

    @DeleteMapping("remove-test/{key}")
    public R removeAd(@PathVariable String key) {
        Boolean delete = redisTemplate.delete(key);
        // 是否删除成功
        System.out.println(delete);
        Boolean hasKey = redisTemplate.hasKey(key);
        // key 是否存在
        System.out.println(hasKey);
        return R.ok();
    }

}
