package com.easy.oauth.service;

import java.util.Map;

/**
 * 登录service
 *
 * @author tallon
 * @version v1.0.0
 * @date 2020-11-20 14:55
 */
public interface LoginService {

    /**
     * 登录成功，返回 Token
     *
     * @param userName {@code String} 账号
     * @param password {@code String} 密码
     * @return {@code Map<String,String>} key:token
     */
    Map<String, String> getToken(String userName, String password);
}
