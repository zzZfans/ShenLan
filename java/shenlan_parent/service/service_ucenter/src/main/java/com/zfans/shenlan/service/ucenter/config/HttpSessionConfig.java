package com.zfans.shenlan.service.ucenter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * @Author 凡森
 * @Date 2021/12/1
 */
@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {

    // 可选配置
    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        // 将 Spring Session 默认的 Cookie Key 从 SESSION 替换为原生的 JSESSIONID
        serializer.setCookieName("JSESSIONID");
        // CookiePath 设置为根路径
        serializer.setCookiePath("/");
        // 配置了相关的正则表达式，可以达到同父域下的单点登录的效果
        serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
        return serializer;
    }
}
