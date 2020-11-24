package com.oauth;

import com.easy.oauth.AuthApplication;
import com.easy.oauth.common.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 测试方法
 *
 * @author tallon
 * @version v1.0.0
 * @date 2020-11-20 15:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthApplication.class)
public class TestMethod {
    @Resource
    private BCryptPasswordEncoder encoder;

    @Resource
    private RedisUtil redisUtil;

    @Test
    public void passwd() {
        System.out.println(encoder.encode("tallon"));
    }

    @Test
    public void test() {
        Object o = redisUtil.get("38161727-9fbf-4f7a-88f2-5c8cfaef4231");
        System.out.println(o);
    }
}
