package com.easy.oauth.controller;

import com.easy.oauth.common.Header;
import com.easy.oauth.resonse.ResponseCode;
import com.easy.oauth.resonse.ResponseResult;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 登出
 *
 * @author tallon
 * @version v1.0.0
 * @date 2020-11-21 17:13
 */
@RestController
@RequestMapping(value = "logout")
public class LogoutController {

    @Resource
    public TokenStore tokenStore;

    @Resource
    public HttpServletRequest request;

    /**
     * 注销
     * (应该是框架的规定，此处必须指定路径 "/logout/自定义",不可以是 "/logout")
     *
     * @return {@link ResponseResult}
     */
    @PostMapping("user")
    public ResponseResult userLogout() {
        String token = Header.getAuthorization(request.getHeader("Authorization"));

        // 删除 token 以注销
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        if (null != oAuth2AccessToken) {
            tokenStore.removeAccessToken(oAuth2AccessToken);
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResponseCode.INTERFACE_ADDRESS_INVALID);
    }
}
