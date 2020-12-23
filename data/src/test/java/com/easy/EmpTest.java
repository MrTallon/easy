package com.easy;

import com.easy.data.Application;
import com.easy.data.domain.Emp;
import com.easy.data.mapper.EmpMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * test
 *
 * @author tallon
 * @version v1.0.0
 * @date 2020-12-11 09:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class EmpTest {

    @Resource
    private EmpMapper mapper;

    @Test
    public void test1() {
        Emp e = new Emp();
        e.setName(0);
        e.setHobby(0);
        e.setAge(0);
        mapper.insert(e);

    }

    @Test
    public void test2() {
        // 创建一个最多同时运行 3 个任务的线程池
        ExecutorService service = Executors.newFixedThreadPool(3);
        AtomicInteger a = new AtomicInteger(0);




    }
}
