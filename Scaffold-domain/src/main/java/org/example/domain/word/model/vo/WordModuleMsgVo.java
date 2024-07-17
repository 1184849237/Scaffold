package org.example.domain.word.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.example.domain.word.model.aggregate.WordModule;
import org.example.domain.word.model.entity.WordModuleFormatPo;
import org.example.domain.word.model.entity.WordModuleMsgPo;
import org.example.types.validated.GroupAdd;
import org.example.types.validated.GroupGenerate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
public class WordModuleMsgVo implements Serializable{


    private Long id;
    /**
     * key
     */
    private Long moduleId;
    /**
     * key
     */
    @NotNull(message = "替换key不能为空！", groups = {GroupAdd.class, GroupGenerate.class})
    private String moduleKey;
    /**
     * 值内容
     */
    private String moduleValue;

    /**
     * 类型 0 文本 1图片 2 勾选 3 表格
     */
    @NotNull(message = "替换类型不能为空！", groups = {GroupAdd.class, GroupGenerate.class})
    private WordModule moduleType;

    /**
     * 格式信息
     */
    private Long formatId;

    /**
     * 格式信息
     */
    @Valid
    private  WordModuleFormatVo wordModuleFormatVo;

    public WordModuleMsgPo toPo(){
        return WordModuleMsgPo.builder()
                .id(this.id)
                .moduleId(this.moduleId)
                .moduleKey(this.moduleKey)
                .moduleValue(this.moduleValue)
                .moduleType(this.moduleType)
                .formatId(this.formatId)
                .wordModuleFormatPo(this.wordModuleFormatVo.toPo())
                .build();
    }

}
