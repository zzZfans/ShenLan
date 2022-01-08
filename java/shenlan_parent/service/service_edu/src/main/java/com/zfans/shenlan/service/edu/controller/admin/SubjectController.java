package com.zfans.shenlan.service.edu.controller.admin;


import com.zfans.shenlan.common.base.result.R;
import com.zfans.shenlan.common.base.result.ResultCodeEnum;
import com.zfans.shenlan.service.base.exception.ShenlanException;
import com.zfans.shenlan.service.edu.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Zfans
 * @since 2021-01-25
 */
// @CrossOrigin // 允许跨域
@Api(tags = "课程分类管理")
@Slf4j
@RestController
@RequestMapping("/admin/edu/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @ApiOperation(value = "Excel批量导入课程类别数据")
    @PostMapping("import")
    public R batchImport(
            @ApiParam(value = "Excel文件", required = true)
            @RequestParam("file") MultipartFile file) {

        try {
            InputStream inputStream = file.getInputStream();
            subjectService.batchImport(inputStream);
            return R.ok().message("批量导入成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e.getCause());
            throw new ShenlanException(ResultCodeEnum.EXCEL_DATA_IMPORT_ERROR);
        }
    }

    @ApiOperation(value = "嵌套数据列表")
    @GetMapping("nested-list")
    public R nestedList() {
        return R
                .ok()
                .data("items", subjectService.nestedList());
    }
}

