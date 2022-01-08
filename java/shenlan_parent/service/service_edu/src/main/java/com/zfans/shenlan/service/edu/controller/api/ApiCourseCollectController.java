package com.zfans.shenlan.service.edu.controller.api;

import com.zfans.shenlan.common.base.result.R;
import com.zfans.shenlan.common.base.util.JwtInfo;
import com.zfans.shenlan.common.base.util.JwtUtils;
import com.zfans.shenlan.service.edu.entity.vo.CourseCollectVo;
import com.zfans.shenlan.service.edu.service.CourseCollectService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author 凡森
 * @Date 2021/12/8
 */
// @CrossOrigin
@RestController
@RequestMapping("/api/edu/course-collect")
@Slf4j
public class ApiCourseCollectController {

    @Autowired
    private CourseCollectService courseCollectService;

    @ApiOperation(value = "判断是否收藏")
    @GetMapping("auth/is-collect/{courseId}")
    public R isCollect(
            @ApiParam(name = "courseId", value = "课程id", required = true)
            @PathVariable String courseId,
            HttpServletRequest request) {

        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        if (jwtInfo == null) {
            return R.error().message("jwtInfo is null");
        }
        boolean isCollect = courseCollectService.isCollect(courseId, jwtInfo.getId());
        return R.ok().data("isCollect", isCollect);
    }

    @ApiOperation(value = "收藏课程")
    @PostMapping("auth/save/{courseId}")
    public R save(
            @ApiParam(name = "courseId", value = "课程id", required = true)
            @PathVariable String courseId,
            HttpServletRequest request) {

        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        if (jwtInfo == null) {
            return R.error().message("jwtInfo is null");
        }
        courseCollectService.saveCourseCollect(courseId, jwtInfo.getId());
        return R.ok();
    }

    @ApiOperation(value = "获取课程收藏列表")
    @GetMapping("auth/list")
    public R collectList(HttpServletRequest request) {

        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        if (jwtInfo == null) {
            return R.error().message("jwtInfo is null");
        }
        List<CourseCollectVo> list = courseCollectService.selectListByMemberId(jwtInfo.getId());
        return R.ok().data("items", list);
    }

    @ApiOperation(value = "取消收藏课程")
    @DeleteMapping("auth/remove/{courseId}")
    public R remove(
            @ApiParam(name = "courseId", value = "课程id", required = true)
            @PathVariable String courseId,
            HttpServletRequest request) {

        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        if (jwtInfo == null) {
            return R.error().message("jwtInfo is null");
        }
        boolean result = courseCollectService.removeCourseCollect(courseId, jwtInfo.getId());
        if (result) {
            return R.ok().message("已取消");
        } else {
            return R.error().message("取消失败");
        }
    }
}
