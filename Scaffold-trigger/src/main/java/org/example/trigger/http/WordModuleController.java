package org.example.trigger.http;


import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.SneakyThrows;
import org.example.domain.word.model.entity.WordModulePo;
import org.example.domain.word.model.vo.WordModuleVo;
import org.example.api.WordModuleService;
import org.example.types.model.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhoupengcheng
 * @since 2024-07-11
 */
@RestController
@RequestMapping("/wordModulePo")
public class WordModuleController {

    @Resource
    WordModuleService wordModuleService;

    /**
     * 分页查询
     *
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<WordModulePo>> queryPage() {
        IPage<WordModulePo> wordModulePoIPage = wordModuleService.queryPage(new WordModulePo());
        return Result.data(wordModulePoIPage);
    }

    /**
     * 添加
     *
     * @param moduleId
     * @return
     */
    @PostMapping("/addModule")
    public Result<Object> addModule(@RequestBody WordModuleVo wordModuleVo) {
        wordModuleService.addModule(wordModuleVo);
        return Result.success();
    }

    /**
     * 根据id查询
     *
     * @param moduleId
     * @return
     */
    @PostMapping("/queryById")
    public Result<WordModulePo> queryById(Long moduleId) {
        return Result.data(wordModuleService.queryById(moduleId));
    }

    /**
     * 根据id生成
     * @param moduleId
     * @param response
     */
    @PostMapping("/generateWordModuleById")
    @SneakyThrows
    public void generateWordModuleById(Long moduleId, HttpServletResponse response) {
        wordModuleService.generateWordModuleById(moduleId,response);
    }

    @PostMapping("/generateWordModuleWithVo")
    @SneakyThrows
    public void generateWordModuleWithVo(@RequestBody WordModuleVo wordModuleVo, HttpServletResponse response) {
        wordModuleService.generateWordModuleWithVo(wordModuleVo,response);
    }
}

