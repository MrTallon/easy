package com.easy.mybatis.service.impl;

import com.easy.mybatis.base.BaseServiceImpl;
import com.easy.mybatis.domain.TbUser;
import com.easy.mybatis.mapper.TbUserMapper;
import com.easy.mybatis.service.ITbUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Tallon
 * @since 2020-12-05
 */
@Service
public class TbUserServiceImpl extends BaseServiceImpl<TbUserMapper, TbUser> implements ITbUserService {

}
