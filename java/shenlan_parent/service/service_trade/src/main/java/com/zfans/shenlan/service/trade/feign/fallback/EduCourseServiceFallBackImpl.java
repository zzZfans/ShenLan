package com.zfans.shenlan.service.trade.feign.fallback;

import com.zfans.shenlan.common.base.result.R;
import com.zfans.shenlan.service.base.dto.CourseDto;
import com.zfans.shenlan.service.trade.feign.EduCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author 凡森
 * @Date 2021/12/4
 */
@Service
@Slf4j
public class EduCourseServiceFallBackImpl implements EduCourseService {
    @Override
    public CourseDto getCourseDtoById(String courseId) {
        log.info("熔断保护");
        return null;
    }

    @Override
    public R updateBuyCountById(String id) {
        log.error("熔断器被执行");
        return R.error();
    }
}
