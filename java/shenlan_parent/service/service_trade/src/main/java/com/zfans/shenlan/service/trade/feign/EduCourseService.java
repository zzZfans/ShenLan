package com.zfans.shenlan.service.trade.feign;

import com.zfans.shenlan.common.base.result.R;
import com.zfans.shenlan.service.base.dto.CourseDto;
import com.zfans.shenlan.service.trade.feign.fallback.EduCourseServiceFallBackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author 凡森
 * @Date 2021/12/4
 */
@Service
@FeignClient(value = "service-edu", fallback = EduCourseServiceFallBackImpl.class)
public interface EduCourseService {

    @GetMapping(value = "/api/edu/course/inner/get-course-dto/{courseId}")
    CourseDto getCourseDtoById(@PathVariable(value = "courseId") String courseId);

    @GetMapping("/api/edu/course/inner/update-buy-count/{id}")
    R updateBuyCountById(@PathVariable("id") String id);
}
