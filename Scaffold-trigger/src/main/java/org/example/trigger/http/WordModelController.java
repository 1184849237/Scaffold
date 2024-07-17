package org.example.trigger.http;


import lombok.SneakyThrows;
import org.example.domain.word.model.vo.WordModelVo;
import org.example.api.WordModelService;
import org.example.types.model.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhoupengcheng
 * @since 2024-07-15
 */
@RestController
@RequestMapping("/wordModelPo")
public class WordModelController {

    @Resource
    WordModelService wordModelService;

    @PostMapping("/uploadModel")
    @SneakyThrows
    public Result<Object> uploadModel(@RequestBody WordModelVo wordModelVo) {
        wordModelService.uploadModel(wordModelVo);
        return Result.success();
    }

    @PostMapping("/updateModel")
    @SneakyThrows
    public Result<Object> updateModel(@RequestBody WordModelVo wordModelVo) {
        wordModelService.updateModel(wordModelVo);
        return Result.success();
    }

    @PostMapping("/queryModelList")
    @SneakyThrows
    public Result<Object> queryModelList(@RequestBody WordModelVo wordModelVo) {
        return Result.data(wordModelService.queryModelList(wordModelVo));
    }
}

