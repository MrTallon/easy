package com.easy.oauth.controller;

import com.easy.oauth.common.Header;
import com.easy.oauth.param.LoginParam;
import com.easy.oauth.resonse.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    private HttpServletRequest request;

    @Resource
    private ILoginService loginService;

    /**
     * 管理员登录
     * @param loginParam {@code JSON} {@link LoginParam}
     * @return {@link ResponseResult}
     */
    @PostMapping("admin")
    public ResponseResult admin(@RequestBody LoginParam loginParam) {
        return ResponseResult.success(loginService.getToken(loginParam.getUsername(), loginParam.getPassword()));
    }

    /**
     * 用户登录，登录只是拿 Token
     * @param loginParam {@code JSON} {@link LoginParam}
     * @return {@link ResponseResult}
     */
    @PostMapping("user")
    public ResponseResult users(@RequestBody LoginParam loginParam) {
        return ResponseResult.success(loginService.getToken(loginParam.getUsername(), loginParam.getPassword()));
    }

    /**
     * 刷新令牌
     * @return {@link ResponseResult}
     */
    @PostMapping("refresh")
    public ResponseResult refresh() {
        String token = Header.getAuthorization(request.getHeader("Authorization"));
        return ResponseResult.success(loginService.refresh(token));
    }

}
