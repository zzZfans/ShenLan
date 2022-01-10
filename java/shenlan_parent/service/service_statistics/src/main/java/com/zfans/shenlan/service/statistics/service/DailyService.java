package com.zfans.shenlan.service.statistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zfans.shenlan.service.statistics.entity.Daily;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author Zfans
 * @since 2022-01-10
 */
public interface DailyService extends IService<Daily> {
    void createStatisticsByDay(String day);
}
