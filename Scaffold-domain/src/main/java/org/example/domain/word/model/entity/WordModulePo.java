package org.example.domain.word.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.List;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author zhoupengcheng
 * @since 2024-07-11
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("word_module")
public class WordModulePo extends Model<WordModulePo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    private String moduleName;
    /**
     * 关联模板id
     */
    private Long modelId;

    private transient List<WordModuleMsgPo> wordModuleMsgPoList;

    private transient WordModelPo wordModelPo;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
