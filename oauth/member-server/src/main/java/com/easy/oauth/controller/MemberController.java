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
 * @date 2020-11-23 11:41
 */
@RestController
@RequestMapping("member")
public class MemberController {
    @GetMapping("view")
    public String view() {
        return "查询会员列表";
    }

    @DeleteMapping("delete")
    public String delete() {
        return "删除会员列表";
    }
}
