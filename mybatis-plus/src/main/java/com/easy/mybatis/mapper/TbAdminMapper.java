package com.easy.mybatis.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easy.mybatis.domain.TbAdmin;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 *
 * @author Tallon
 * @since 2020-12-05
 */
public interface TbAdminMapper extends BaseMapper<TbAdmin> {


    /**
     * 分页查询
     * @param page {@link IPage}
     * @param wrapper {@link Wrapper}
     * @return {@link IPage}
     */
    IPage<TbAdmin> listByQuery(Page<TbAdmin> page, @Param(Constants.WRAPPER) LambdaQueryWrapper<TbAdmin> wrapper);
}
