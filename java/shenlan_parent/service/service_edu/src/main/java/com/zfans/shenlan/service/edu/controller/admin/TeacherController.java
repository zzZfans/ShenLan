package com.zfans.shenlan.service.edu.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zfans.shenlan.common.base.result.R;
import com.zfans.shenlan.service.edu.entity.Teacher;
import com.zfans.shenlan.service.edu.entity.vo.TeacherQueryVo;
import com.zfans.shenlan.service.edu.feign.OssFileService;
import com.zfans.shenlan.service.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Zfans
 * @since 2021-01-25
 */
// @CrossOrigin // 允许跨域
@Api(tags = "讲师管理")
@RestController
@RequestMapping("/admin/edu/teacher")
@Slf4j
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private OssFileService ossFileService;

    @ApiOperation("所有讲师列表")
    @GetMapping("list")
    public R listAll() {
        return R.ok().data("items", teacherService.list());
    }

    @ApiOperation(value = "根据 id 删除讲师", notes = "逻辑删除")
    @DeleteMapping("remove/{id}")
    public R removeById(@ApiParam("讲师 id ") @PathVariable String id) {
        //删除图片
        teacherService.removeAvatarById(id);
        return teacherService.removeById(id) ?
                R.ok().message("删除成功") : R.error().message("数据不存在");
    }


    @ApiOperation(value = "讲师分页列表")
    @GetMapping("list/{page}/{limit}")
    public R listPage(@ApiParam(value = "当前页码", required = true) @PathVariable Long page,
                      @ApiParam(value = "每页记录数", required = true) @PathVariable Long limit,
                      @ApiParam("讲师列表查询对象") TeacherQueryVo teacherQueryVo) {
        Page<Teacher> pageParam = new Page<>(page, limit);
        IPage<Teacher> pageModel = teacherService.selectPage(pageParam, teacherQueryVo);
        List<Teacher> records = pageModel.getRecords();
        long total = pageModel.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation("新增讲师")
    @PostMapping("save")
    public R save(@ApiParam("讲师对象") @RequestBody Teacher teacher) {
        teacherService.save(teacher);
        return R.ok().message("保存成功");
    }

    @ApiOperation("更新讲师")
    @PutMapping("update")
    public R updateById(@ApiParam("讲师对象") @RequestBody Teacher teacher) {
        return teacherService.updateById(teacher) ?
                R.ok().message("更新成功") : R.error().message("数据不存在");
    }

    @ApiOperation("根据 id 获取讲师信息")
    @GetMapping("get/{id}")
    public R getById(@ApiParam("讲师 id ") @PathVariable String id) {
        Teacher teacher = teacherService.getById(id);
        return teacher != null ?
                R.ok().data("item", teacher) : R.error().message("数据不存在");
    }

    @ApiOperation("根据id列表删除讲师")
    @DeleteMapping("batch-remove")
    public R removeRows(
            @ApiParam(value = "讲师id列表", required = true)
            @RequestBody List<String> idList) {
        boolean result = teacherService.removeByIds(idList);
        if (result) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation("根据左关键字查询讲师名列表")
    @GetMapping("list/name/{key}")
    public R selectNameListByKey(
            @ApiParam(value = "查询关键字", required = true)
            @PathVariable String key) {

        List<Map<String, Object>> nameList = teacherService.selectNameListByKey(key);

        return R.ok().data("nameList", nameList);
    }

    @ApiOperation("测试服务调用")
    @GetMapping("test")
    public R test() {
        ossFileService.test();
        log.info("edu执行成功");
        return R.ok();
    }

    @ApiOperation("测试并发")
    @GetMapping("test_concurrent")
    public R testConcurrent() {
        log.info("test_concurrent");
        return R.ok();
    }

    @GetMapping("/message1")
    public String message1() {
        return "message1";
    }

    @GetMapping("/message2")
    public String message2() {
        return "message2";
    }
}

