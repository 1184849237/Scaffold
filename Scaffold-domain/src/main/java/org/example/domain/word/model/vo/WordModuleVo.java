package org.example.domain.word.model.vo;


import lombok.Data;
import org.example.domain.word.model.entity.WordModuleMsgPo;

import java.util.List;

/**
 * Created by zhoupengcheng on 2019/10/30 .
 * Desc:  ....
 * word替换数据对象
 *
 * @author zhoupengcheng
 */
@Data
public class WordParamVo {

    private Long id;

    private String moduleName;

    private Long modelId;

    private List<WordModuleMsgVo> wordModuleMsgVos;


}
