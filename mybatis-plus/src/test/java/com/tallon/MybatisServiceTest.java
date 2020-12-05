package com.tallon;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.easy.mybatis.Application;
import com.easy.mybatis.domain.TbAdmin;
import com.easy.mybatis.service.ITbAdminService;
import com.easy.mybatis.service.ITbUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 测试连接
 *
 * @author tallon
 * @version v1.0.0
 * @date 2020-12-05 10:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MybatisServiceTest {
    @Resource
    private ITbUserService userService;

    @Resource
    private ITbAdminService adminService;

    @Test
    public void test1() {
        TbAdmin tbAdmin = new TbAdmin();
        tbAdmin.setUsername("eric");
        tbAdmin.setPassword("123456");
        tbAdmin.setNickname("eric");
        tbAdmin.setEmail("123@qq.com");
        tbAdmin.setUrl("www.baidu.com");
        tbAdmin.setStatus(0);
        Assert.assertTrue(adminService.create(tbAdmin));
    }

    @Test
    public void test2() {
        TbAdmin admin = new TbAdmin();
        admin.setEmail("123@qq.com");
        IPage<?> page = adminService.page(1, 1, admin);
        System.out.println(page.getPages());
    }

    @Test
    public void test3() {
        boolean remove = adminService.remove(1335105026542981122L);
        Assert.assertTrue(remove);

    }
}
