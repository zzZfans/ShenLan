package com.zfans.shenlan.service.statistics.task;

import com.zfans.shenlan.service.statistics.service.DailyService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author 凡森
 * @Date 2022/1/10
 */
@Slf4j
@Component
public class ScheduledTask {

    @Autowired
    private DailyService dailyService;

    // /**
    //  * 测试
    //  */
    // @Scheduled(cron="0/3 * * * * *") // 每隔 3 秒执行一次
    // public void task1() {
    //     log.info("task1 执行");
    // }

    /**
     * 每天凌晨 1 点执行定时任务
     */
    @Scheduled(cron = "0 0 1 * * ?") // 只支持 6 位表达式
    public void taskGenarateStatisticsData() {
        // 获取上一天的日期
        String day = new DateTime().minusDays(1).toString("yyyy-MM-dd");
        dailyService.createStatisticsByDay(day);
        log.info("taskGenarateStatisticsData 统计完毕");
    }
}
