package com.zfans.shenlan.service.statistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author 凡森
 * @Date 2022/1/10
 */
@SpringBootApplication
@ComponentScan({"com.zfans.shenlan"})
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
public class ServiceStatisticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceStatisticsApplication.class, args);
    }
}
