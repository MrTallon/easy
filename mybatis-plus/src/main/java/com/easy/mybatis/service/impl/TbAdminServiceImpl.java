package com.easy.mybatis.service.impl;

import com.easy.mybatis.base.BaseServiceImpl;
import com.easy.mybatis.domain.TbAdmin;
import com.easy.mybatis.mapper.TbAdminMapper;
import com.easy.mybatis.service.ITbAdminService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author Tallon
 * @since 2020-12-05
 */
@Service
public class TbAdminServiceImpl extends BaseServiceImpl<TbAdminMapper, TbAdmin> implements ITbAdminService {

}
