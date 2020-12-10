package com.easy.elk.controller;

import com.easy.elk.common.ResponseResult;
import com.easy.elk.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 测试controller
 *
 * @author tallon
 * @version v1.0.0
 * @date 2020-12-10 11:23
 */
@RestController
public class TestController {

    @Resource
    private TestService testService;

    @GetMapping("add")
    public ResponseResult add() {
        Integer add = testService.add(1, 2);
        return ResponseResult.success(add);
    }

    @GetMapping("ex/{a}")
    public ResponseResult ex(@PathVariable Integer a) {
        String s = testService.exception1(a);
        return ResponseResult.success((Object) s);
    }
}
