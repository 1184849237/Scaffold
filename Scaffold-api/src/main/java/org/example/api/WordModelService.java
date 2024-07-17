package org.example.api;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.domain.word.model.entity.WordModelPo;
import org.example.domain.word.model.vo.WordModelVo;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoupengcheng
 * @since 2024-07-15
 */
public interface WordModelService extends IService<WordModelPo> {

    /**
     * 上传word模板
     * @param wordModelVo
     */
    void uploadModel(WordModelVo wordModelVo) throws IOException;

    void updateModel(WordModelVo wordModelVo) throws IOException;

    /**
     * 列表查询模板文件
     * @param wordModelVo
     * @return
     */
    List<WordModelPo> queryModelList(WordModelVo wordModelVo);
}
