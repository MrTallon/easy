package com.easy.oauth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.easy.oauth.domain.User;
import com.easy.oauth.mapper.UserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义认证授权
 *
 * @author tallon
 * @version v1.0.0
 * @date 2020-11-20 15:08
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 用户登录
        LambdaQueryWrapper<User> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(userWrapper);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (null != user) {
            List<String> permissions = userMapper.tbUserPermissions(user.getId());
            permissions.forEach(t -> grantedAuthorities.add(new SimpleGrantedAuthority(t)));
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
        }
        return null;
    }
}
