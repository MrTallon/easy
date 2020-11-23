package com.easy.oauth.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * controller
 *
 * @author tallon
 * @version v1.0.0
 * @date 2020-11-23 11:40
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @GetMapping("view")
    public String view() {
        return "查询订单列表";
    }

    @DeleteMapping("delete")
    public String delete() {
        return "删除订单列表";
    }
}
