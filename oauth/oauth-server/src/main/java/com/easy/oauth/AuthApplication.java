package com.easy.oauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author tallon
 * @version v1.0.0
 * @date 2020-11-20 14:38
 */
@MapperScan(basePackages = "com.easy.oauth.mapper")
@SpringBootApplication
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class);
    }
}
