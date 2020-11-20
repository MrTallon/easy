package com.easy.oauth.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import javax.annotation.Resource;

/**
 * 认证资源服务器配置
 *
 * @author tallon
 * @version v1.0.0
 * @date 2020-11-20 15:47
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration {

    /**
     * 支持password模式
     */
    @Resource
    private AuthenticationManager authenticationManager;

    /**
     * 默认加密方式
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Lazy
    @Resource(name = "userDetailsServiceBean")
    private UserDetailsService userDetailsService;



}
