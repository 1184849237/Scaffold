package org.example.domain.word.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.example.domain.word.model.entity.WordModuleFormatPo;
import org.example.domain.word.model.vo.WordModuleFormatVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoupengcheng
 * @since 2024-07-10
 */
public interface WordModuleFormatService extends IService<WordModuleFormatPo> {

    void addFormat(WordModuleFormatVo wordModuleFormatVo);
}
