package com.zfans.shenlan.service.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author Zfans
 * @DateTime 2021/3/18 12:08
 */
@SpringBootApplication
@ComponentScan({"com.zfans.shenlan"})
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceCmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCmsApplication.class, args);
    }

}
