package com.easy.oauth.common;

import cn.hutool.core.util.StrUtil;

/**
 * 获取token
 *
 * @author tallon
 * @version v1.0.0
 * @date 2020-11-21 17:52
 */
public class Header {

    private static final String AUTHORIZATION_BEARER_TOKEN = "Basic ";

    /**
     * 获取 Token
     *
     * @param header {@code String} request.getHeader("Authorization")
     * @return {@code String} token
     */
    public static String getAuthorization(String header) {
        if (StrUtil.isBlank(header) || header.startsWith(AUTHORIZATION_BEARER_TOKEN)) {
            // 自定义异常
            return null;
        }
        return header.substring(AUTHORIZATION_BEARER_TOKEN.length() + 1);
    }

}
