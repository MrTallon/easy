package com.easy.oauth.controller;

import com.easy.oauth.param.LoginParam;
import com.easy.oauth.resonse.ResponseResult;
import com.easy.oauth.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 登录控制器
 *
 * @author tallon
 * @version v1.0.0
 * @date 2020-11-20 14:46
 */
@RestController
@RequestMapping(value = "login")
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("user")
    public ResponseResult user(@RequestBody LoginParam param) {
        return ResponseResult.success(loginService.getToken(param.getUserName(), param.getPassword()));
    }

    @PostMapping("refresh")
    public ResponseResult refresh() {
//        String token = Header
        return ResponseResult.success();
    }
}
