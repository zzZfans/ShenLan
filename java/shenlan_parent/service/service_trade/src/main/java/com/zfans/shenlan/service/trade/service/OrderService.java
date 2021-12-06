package com.zfans.shenlan.service.trade.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zfans.shenlan.service.trade.entity.Order;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author Zfans
 * @since 2021-12-04
 */
public interface OrderService extends IService<Order> {

    String saveOrder(String courseId, String memberId);

    Order getByOrderId(String orderId, String memberId);

    Boolean isBuyByCourseId(String courseId, String memberId);
}
