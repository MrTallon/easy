package com.easy.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author tallon
 * @version v1.0.0
 * @date 2020-12-05 10:38
 */
@SpringBootApplication
@MapperScan(basePackages = "com.easy.mybatis.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
