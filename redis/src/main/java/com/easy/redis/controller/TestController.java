package com.easy.redis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * controller
 *
 * @author tallon
 * @version v1.0.0
 */
@RestController
public class TestController {

    /**
     * 测试连接
     */
    @GetMapping("test")
    public String test() {
        return "hello";
    }
}
