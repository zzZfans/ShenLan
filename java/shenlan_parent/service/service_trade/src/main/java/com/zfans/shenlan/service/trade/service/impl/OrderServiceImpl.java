package com.zfans.shenlan.service.trade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.zfans.shenlan.common.base.result.ResultCodeEnum;
import com.zfans.shenlan.service.base.dto.CourseDto;
import com.zfans.shenlan.service.base.dto.MemberDto;
import com.zfans.shenlan.service.base.exception.ShenlanException;
import com.zfans.shenlan.service.trade.entity.Order;
import com.zfans.shenlan.service.trade.entity.PayLog;
import com.zfans.shenlan.service.trade.feign.EduCourseService;
import com.zfans.shenlan.service.trade.feign.UcenterMemberService;
import com.zfans.shenlan.service.trade.mapper.OrderMapper;
import com.zfans.shenlan.service.trade.mapper.PayLogMapper;
import com.zfans.shenlan.service.trade.service.OrderService;
import com.zfans.shenlan.service.trade.util.OrderNoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author Zfans
 * @since 2021-12-04
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private UcenterMemberService ucenterMemberService;

    @Autowired
    private PayLogMapper payLogMapper;

    @Override
    public String saveOrder(String courseId, String memberId) {

        // 查询当前用户是否已有当前课程的订单
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        queryWrapper.eq("member_id", memberId);
        Order orderExist = baseMapper.selectOne(queryWrapper);
        if (orderExist != null) {
            // 如果订单已存在，则直接返回订单 id
            return orderExist.getId();
        }

        // 查询课程信息
        CourseDto courseDto = eduCourseService.getCourseDtoById(courseId);
        if (courseDto == null) {
            throw new ShenlanException(ResultCodeEnum.PARAM_ERROR);
        }

        // 查询用户信息
        MemberDto memberDto = ucenterMemberService.getMemberDtoByMemberId(memberId);
        if (memberDto == null) {
            throw new ShenlanException(ResultCodeEnum.PARAM_ERROR);
        }

        // 创建订单
        Order order = new Order();
        order.setOrderNo(OrderNoUtils.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(courseDto.getTitle());
        order.setCourseCover(courseDto.getCover());
        order.setTeacherName(courseDto.getTeacherName());
        // 分
        order.setTotalFee(courseDto.getPrice().multiply(new BigDecimal(100)));
        order.setMemberId(memberId);
        order.setMobile(memberDto.getMobile());
        order.setNickname(memberDto.getNickname());
        // 未支付
        order.setStatus(0);
        // 微信支付
        order.setPayType(1);
        baseMapper.insert(order);
        return order.getId();
    }

    @Override
    public Order getByOrderId(String orderId, String memberId) {

        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();

        queryWrapper
                .eq("id", orderId)
                .eq("member_id", memberId);

        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public Boolean isBuyByCourseId(String courseId, String memberId) {

        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();

        queryWrapper
                .eq("member_id", memberId)
                .eq("course_id", courseId)
                .eq("status", 1);

        Integer count = baseMapper.selectCount(queryWrapper);

        return count > 0;
    }

    @Override
    public List<Order> selectByMemberId(String memberId) {

        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();

        queryWrapper.orderByDesc("create_time");
        queryWrapper.eq("member_id", memberId);

        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public boolean removeById(String orderId, String memberId) {

        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();

        queryWrapper
                .eq("id", orderId)
                .eq("member_id", memberId);

        return this.remove(queryWrapper);
    }

    @Override
    public Order getOrderByOrderNo(String orderNo) {

        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("order_no", orderNo);

        return baseMapper.selectOne(queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateOrderStatus(Map<String, String> notifyMap) {

        // 更新订单状态
        String orderNo = notifyMap.get("out_trade_no");
        Order order = this.getOrderByOrderNo(orderNo);
        // 支付成功
        order.setStatus(1);
        baseMapper.updateById(order);

        // 记录支付日志
        PayLog payLog = new PayLog();
        payLog.setOrderNo(orderNo);
        payLog.setPayTime(new Date());
        // 支付类型
        payLog.setPayType(1);
        // 总金额(分)
        payLog.setTotalFee(Long.parseLong(notifyMap.get("total_fee")));
        // 支付状态
        payLog.setTradeState(notifyMap.get("result_code"));
        payLog.setTransactionId(notifyMap.get("transaction_id"));
        payLog.setAttr(new Gson().toJson(notifyMap));
        payLogMapper.insert(payLog);

        // 更新课程销量：有问题直接熔断
        eduCourseService.updateBuyCountById(order.getCourseId());
    }

    @Override
    public boolean queryPayStatus(String orderNo) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", orderNo);
        Order order = baseMapper.selectOne(queryWrapper);
        return order.getStatus() == 1;
    }
}
