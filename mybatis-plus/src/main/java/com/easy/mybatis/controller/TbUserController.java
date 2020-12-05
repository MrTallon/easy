package com.easy.mybatis.controller;

import com.easy.mybatis.base.BaseController;
import com.easy.mybatis.domain.TbUser;
import com.easy.mybatis.service.ITbUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Tallon
 * @since 2020-12-05
 */
@RestController
@RequestMapping("user")
public class TbUserController extends BaseController<TbUser, ITbUserService> {
}
