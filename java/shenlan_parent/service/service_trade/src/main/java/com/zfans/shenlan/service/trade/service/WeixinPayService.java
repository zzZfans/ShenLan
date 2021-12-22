package com.zfans.shenlan.service.trade.service;

import java.util.Map;

/**
 * @Author 凡森
 * @Date 2021/12/12
 */
public interface WeixinPayService {
    Map<String, Object> createNative(String orderNo, String remoteAddr);
}
