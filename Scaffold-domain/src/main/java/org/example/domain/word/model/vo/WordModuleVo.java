package org.example.domain.word.model.vo;


import lombok.Data;
import org.example.domain.word.model.entity.WordModulePo;
import org.example.types.validated.GroupAdd;
import org.example.types.validated.GroupGenerate;
import org.example.types.validated.GroupUpdate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhoupengcheng on 2019/10/30 .
 * Desc:  ....
 * word替换数据对象
 *
 * @author zhoupengcheng
 */
@Data
public class WordModuleVo implements Serializable {

    private Long id;
    @NotNull(message = "名称不能为空！", groups = {GroupAdd.class, GroupGenerate.class})
    private String moduleName;
    @NotNull(message = "对应模板id不能为空！", groups = {GroupAdd.class, GroupGenerate.class})
    private Long modelId;
    @Valid
    private List<WordModuleMsgVo> wordModuleMsgVos;

    public WordModulePo toPo() {
        return WordModulePo.builder()
                .id(id)
                .moduleName(moduleName)
                .modelId(modelId)
                .wordModuleMsgPoList(this.wordModuleMsgVos.stream().map(WordModuleMsgVo::toPo).collect(Collectors.toList()))
                .build();
    }


}
