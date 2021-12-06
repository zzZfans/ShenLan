package com.zfans.shenlan.service.trade.controller.api;


import com.zfans.shenlan.common.base.result.R;
import com.zfans.shenlan.common.base.util.JwtInfo;
import com.zfans.shenlan.common.base.util.JwtUtils;
import com.zfans.shenlan.service.trade.entity.Order;
import com.zfans.shenlan.service.trade.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author Zfans
 * @since 2021-12-04
 */
@RestController
@RequestMapping("/api/trade/order")
@Api(description = "网站订单管理")
@CrossOrigin // 跨域
@Slf4j
public class ApiOrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation("新增订单")
    @PostMapping("auth/save/{courseId}")
    public R save(@PathVariable String courseId, HttpServletRequest request) {

        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        if (jwtInfo == null) {
            return R.error().message("jwtInfo is null");
        }
        String orderId = orderService.saveOrder(courseId, jwtInfo.getId());
        return R.ok().data("orderId", orderId);
    }

    @ApiOperation("获取订单")
    @GetMapping("auth/get/{orderId}")
    public R get(@PathVariable String orderId, HttpServletRequest request) {
        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        if (jwtInfo == null) {
            return R.error().message("jwtInfo is null");
        }
        Order order = orderService.getByOrderId(orderId, jwtInfo.getId());
        return R.ok().data("item", order);
    }

    @ApiOperation("判断课程是否购买")
    @GetMapping("auth/is-buy/{courseId}")
    public R isBuyByCourseId(@PathVariable String courseId, HttpServletRequest request) {

        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        if (jwtInfo == null) {
            return R.error().message("jwtInfo is null");
        }
        Boolean isBuy = orderService.isBuyByCourseId(courseId, jwtInfo.getId());
        return R.ok().data("isBuy", isBuy);
    }
}

