package org.example.infrastructure.persistent.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author zhoupengcheng
 * @since 2024-07-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WordModuleMsgPO extends Model<WordModuleMsgPO> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 值内容
     */
    private String moduleValue;

    /**
     * 类型 0 文本 1图片 2 勾选 3 表格
     */
    private String moduleType;

    private Long formatId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
