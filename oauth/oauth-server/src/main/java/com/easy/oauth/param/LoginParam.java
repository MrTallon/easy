package com.easy.oauth.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 登录参数
 *
 * @author tallon
 * @version v1.0.0
 * @date 2020-11-20 14:44
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginParam implements Serializable {

    private static final long serialVersionUID = -5489227205613379745L;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
