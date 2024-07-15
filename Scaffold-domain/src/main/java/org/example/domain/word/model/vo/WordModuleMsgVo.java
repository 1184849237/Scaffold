package org.example.domain.word.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.example.domain.word.model.aggregate.WordModule;
import org.example.domain.word.repository.WordModuleFormatDao;

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
public class WordModuleMsgPo extends Model<WordModuleMsgPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    /**
     * key
     */
    private String moduleId;
    /**
     * key
     */
    private String moduleKey;
    /**
     * 值内容
     */
    private String moduleValue;

    /**
     * 类型 0 文本 1图片 2 勾选 3 表格
     */
    private WordModule moduleType;

    /**
     * 格式信息
     */
    private Long formatId;

    /**
     * 格式信息
     */
    private transient WordModuleFormatPo wordModuleFormatPo;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
