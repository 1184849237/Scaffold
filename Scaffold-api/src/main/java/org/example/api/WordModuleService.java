package org.example.api;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.domain.word.model.entity.WordModulePo;
import org.example.domain.word.model.vo.WordModuleVo;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhoupengcheng
 * @since 2024-07-11
 */
public interface WordModuleService extends IService<WordModulePo> {

    /**
     * 按条件分页查询
     *
     * @param wordModulePo
     * @return
     */
    IPage<WordModulePo> queryPage(WordModulePo wordModulePo);

    /**
     * 新增word模板表单
     *
     * @param wordModuleVo
     */
    void addModule(WordModuleVo wordModuleVo);


    WordModulePo queryById(Long moduleId);

    /**
     * 根据id生成
     *
     * @param moduleId
     * @param response
     */
    void generateWordModuleById(Long moduleId, HttpServletResponse response) throws Exception;

    /**
     * 根据vo 生成
     *
     * @param wordModuleVo
     * @param response
     */
    void generateWordModuleWithVo(WordModuleVo wordModuleVo, HttpServletResponse response) throws Exception;

    /**
     * 生成word
     *
     * @param wordModulePo
     * @return
     */
    ByteArrayInputStream makeWord(WordModulePo wordModulePo) throws Exception;
}
