package com.easy.mybatis;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

    /**
     * MyBatis Plus 分页插件
     * @return {@link PaginationInterceptor}
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
