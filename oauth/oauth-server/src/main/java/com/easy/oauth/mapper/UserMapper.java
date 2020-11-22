package com.easy.oauth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.oauth.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * mapper
 *
 * @author tallon
 * @version v1.0.0
 * @date 2020-11-20 15:36
 */
public interface UserMapper extends BaseMapper<User> {

    @Select(" SELECT p.enname FROM tb_user AS u\n" +
            "  LEFT JOIN tb_user_role AS ur\n" +
            "            ON u.id = ur.user_id\n" +
            "  LEFT JOIN tb_role AS r\n" +
            "            ON r.id = ur.role_id\n" +
            "  LEFT JOIN tb_role_permission AS rp\n" +
            "            ON r.id = rp.role_id\n" +
            "  LEFT JOIN tb_permission AS p\n" +
            "            ON p.id = rp.permission_id\n" +
            " WHERE u.id = #{id};")
    List<String> tbUserPermissions(@Param("id") Long id);
}
