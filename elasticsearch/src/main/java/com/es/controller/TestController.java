package com.es.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 *
 * @author tallon
 * @version v1.0.0
 * @date 2021-07-24 14:56
 */
@RestController
public class TestController {

    @GetMapping("/")
    public String test() {
        return "hello";
    }
}
