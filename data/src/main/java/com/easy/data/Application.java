package com.easy.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author tallon
 * @version v1.0.0
 * @date 2020-12-11 09:36
 */
@SpringBootApplication
//@MapperScan(basePackages = "com.easy.data.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
