package com.easy.mybatis.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.easy.mybatis.base.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author Tallon
 * @since 2020-12-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_admin")
public class TbAdmin extends BaseDomain {

    private static final long serialVersionUID = 3897574705870031218L;

    /**
     * 登录名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 网址
     */
    private String url;

    /**
     * 用户状态：1(已启用) 0(已禁用)
     */
    private Integer status;

    /**
     * 逻辑删除：1(已删除) 0(未删除)
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer isDeleted;
}