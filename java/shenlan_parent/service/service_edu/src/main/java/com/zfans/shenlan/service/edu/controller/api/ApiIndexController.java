package com.zfans.shenlan.service.edu.controller.api;

import com.zfans.shenlan.common.base.result.R;
import com.zfans.shenlan.service.edu.entity.Course;
import com.zfans.shenlan.service.edu.entity.Teacher;
import com.zfans.shenlan.service.edu.service.CourseService;
import com.zfans.shenlan.service.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Zfans
 * @DateTime 2021/3/22 13:03
 */
@CrossOrigin
@Api(tags = "首页")
@RestController
@RequestMapping("/api/edu/index")
public class ApiIndexController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    @ApiOperation("课程列表")
    @GetMapping
    public R index() {

        // 获取首页最热门前 8 条课程数据
        List<Course> courseList = courseService.selectHotCourse();
        // 获取首页推荐前 4 条讲师数据
        List<Teacher> teacherList = teacherService.selectHotTeacher();

        return R.ok().data("courseList", courseList).data("teacherList", teacherList);
    }
}
