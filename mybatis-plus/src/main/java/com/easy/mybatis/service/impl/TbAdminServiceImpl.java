package com.easy.mybatis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    @Override
    public IPage<TbAdmin> page(int current, int size, TbAdmin tbAdmin) {
        Page<TbAdmin> page = new Page<>(current, size);

        LambdaQueryWrapper<TbAdmin> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(tbAdmin.getNickname()), TbAdmin::getNickname, tbAdmin.getNickname())
                .and(StringUtils.isNotBlank(tbAdmin.getEmail()), i -> i.eq(TbAdmin::getEmail, tbAdmin.getEmail()))
                .and(StringUtils.isNotBlank(tbAdmin.getUsername()), i -> i.eq(TbAdmin::getUsername, tbAdmin.getUsername()));
        wrapper.orderByDesc(TbAdmin::getCreateTime);

        return baseMapper.listByQuery(page, wrapper);
    }

}
