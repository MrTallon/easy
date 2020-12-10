package com.easy.elk.service.impl;

import com.easy.elk.common.ResponseCode;
import com.easy.elk.exception.BusinessException;
import com.easy.elk.service.TestService;
import org.springframework.stereotype.Service;

/**
 * 测试service
 *
 * @author tallon
 * @version v1.0.0
 * @date 2020-12-10 11:17
 */
@Service
public class TestServiceImpl implements TestService {

    @Override
    public Integer add(Integer a, Integer b) {
        return a + b;
    }

    @Override
    public String exception1(Integer a) {
        if (a == 1) {
            throw new BusinessException(ResponseCode.DATA_ALREADY_EXISTED);
        } else if (a == 2) {
            throw new BusinessException(ResponseCode.DATA_IS_WRONG);
        } else if (a == 3) {
            a = a / 0;
            System.out.println(a);
        }
        return ResponseCode.SUCCESS.message();
    }
}
