package com.zfans.shenlan.service.ucenter.controller.admin;

import com.zfans.shenlan.common.base.result.R;
import com.zfans.shenlan.service.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 凡森
 * @Date 2022/1/10
 */
//@CrossOrigin
@Api(description = "会员管理")
@RestController
@RequestMapping("/admin/ucenter/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "根据日期统计注册人数")
    @GetMapping(value = "count-register-num/{day}")
    public R countRegisterNum(
            @ApiParam(name = "day", value = "统计日期")
            @PathVariable String day){
        Integer num = memberService.countRegisterNum(day);
        return R.ok().data("registerNum", num);
    }
}
