package com.easy.mybatis.controller;

import com.easy.mybatis.base.BaseController;
import com.easy.mybatis.domain.TbAdmin;
import com.easy.mybatis.service.ITbAdminService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author Tallon
 * @since 2020-12-05
 */
@RestController
@RequestMapping("admin")
public class TbAdminController extends BaseController<TbAdmin, ITbAdminService> {
}
