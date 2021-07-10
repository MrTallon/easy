package com.easy.data.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * emp领域
 *
 * @author tallon
 * @version v1.0.0
 * @date 2020-12-11 09:38
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("emp")
public class Emp extends Model<Emp> {

    private static final long serialVersionUID = -8837905661505789407L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Integer name;

    private Integer hobby;

    private Integer age;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
