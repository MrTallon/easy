package com.easy.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.data.domain.Emp;
import org.apache.ibatis.annotations.Mapper;

/**
 * mapper
 *
 * @author tallon
 * @version v1.0.0
 * @date 2020-12-11 11:20
 */
@Mapper
public interface EmpMapper extends BaseMapper<Emp> {

}
