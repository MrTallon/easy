package com.easy.oauth.service.impl;

import com.easy.oauth.service.LoginService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 登录实现类
 *
 * @author tallon
 * @version v1.0.0
 * @date 2020-11-20 14:56
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Value("${security.oauth2.client.access-token-uri}")
    private String accessTokenUri;

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Resource(name = "userDetailsServiceBean")
    private UserDetailsService userDetailsService;

    @Override
    public Map<String, String> getToken(String userName, String password) {

        return null;
    }
}
