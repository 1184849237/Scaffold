package org.example.api;


import com.baomidou.mybatisplus.extension.service.IService;
import org.example.domain.word.model.entity.WordModuleFormatPo;
import org.example.domain.word.model.entity.WordModuleMsgPo;
import org.example.domain.word.model.vo.WordModuleMsgVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoupengcheng
 * @since 2024-07-09
 */
public interface WordModuleMsgService  extends IService<WordModuleMsgPo> {

    void addMsg(List<WordModuleMsgVo> wordModuleMsgVoList);
}
