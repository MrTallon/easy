package com.rule.service;

import com.rule.controller.TestController;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * service
 *
 * @author tallon
 * @version v1.0.0
 */
public interface TestService {

    /**
     * 单次执行
     */
    void singleRule(TestController.EventReq req);

    /**
     * 循环执行
     */
    void cycleRule(TestController.EventReq req);

    /**
     * 体验课规则
     */
    void basicRule(TestController.EventReq req);

    /**
     * 组合规则
     *
     * @param req
     */
    void unitRule(TestController.EventReq req);

    /**
     * el规则
     * 当一个事实在条件中缺失时，MVEL抛出一个异常，而SpEL将忽略它并返回false
     */
    void elRule(TestController.EventReq req);

    void yamlRule(TestController.EventReq req) throws Exception;

    void jsonRule(TestController.EventReq req) throws Exception;

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Person {
        private Integer age;
        private String name;
        private Boolean adult;
    }
}
