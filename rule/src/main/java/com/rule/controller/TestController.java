package com.rule.controller;

import com.rule.service.TestService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * controller
 *
 * @author tallon
 * @version v1.0.0
 */
@RestController
@RequestMapping("/rule")
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping("/single")
    public String single(@RequestBody EventReq req) {
        testService.singleRule(req);
        return "single";
    }

    @PostMapping("/cycle")
    public String cycle(@RequestBody EventReq req) {
        testService.cycleRule(req);
        return "cycle";
    }

    @PostMapping("/basic")
    public String basic(@RequestBody EventReq req) {
        testService.basicRule(req);
        return "basic";
    }

    @PostMapping("/unit")
    public String unit(@RequestBody EventReq req) {
        testService.unitRule(req);
        return "unit";
    }

    @PostMapping("/el")
    public String el(@RequestBody EventReq req) {
        testService.elRule(req);
        return "el";
    }

    @PostMapping("/yaml")
    public String yaml(@RequestBody EventReq req) throws Exception {
        testService.yamlRule(req);
        return "yaml";
    }

    @PostMapping("/json")
    public String json(@RequestBody EventReq req) throws Exception {
        testService.jsonRule(req);
        return "json";
    }

    /**
     * 模拟事件参数
     */
    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EventReq {
        private Boolean isBasic;
        private Boolean isSystem;
        private Boolean isGift;
    }
}
