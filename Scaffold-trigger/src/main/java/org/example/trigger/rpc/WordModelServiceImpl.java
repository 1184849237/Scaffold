package org.example.trigger.rpc;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;
import org.example.api.WordModelService;
import org.example.domain.obs.ObsUtil;
import org.example.domain.word.model.entity.WordModelPo;
import org.example.domain.word.model.vo.WordModelVo;
import org.example.domain.word.repository.WordModelDao;
import org.example.types.validated.GroupAdd;
import org.example.types.validated.GroupUpdate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhoupengcheng
 * @since 2024-07-15
 */
@DubboService(version = "1.0.0", timeout = 450,validation = "true")
@Transactional
public class WordModelServiceImpl extends ServiceImpl<WordModelDao, WordModelPo> implements WordModelService {

    @Override
    public void uploadModel(@Validated(GroupAdd.class) WordModelVo wordModelVo) throws IOException {
        MultipartFile modelFile = wordModelVo.getModelFile();
        //文件上传并存入数据库
        WordModelPo wordModelPo = WordModelPo.builder()
                .modelName(wordModelVo.getModelName())
                .modelUrl(ObsUtil.uploadFile(modelFile)).build();
        wordModelPo.insert();
    }

    @Override
    public void updateModel(@Validated(GroupUpdate.class) WordModelVo wordModelVo) throws IOException {
        WordModelPo wordModelPo = baseMapper.queryById(wordModelVo.getId());
        if(ObjectUtil.isEmpty(wordModelPo)){
            throw new RuntimeException("未查询到模板信息");
        }
        if(!StrUtil.isEmpty(wordModelVo.getModelName())){
            wordModelPo.setModelName(wordModelVo.getModelName());
        }
        if(!ObjectUtil.isEmpty(wordModelVo.getModelFile())){
            wordModelPo.setModelUrl(ObsUtil.uploadFile(wordModelVo.getModelFile()));
        }
        wordModelPo.updateById();
    }

    @Override
    public List<WordModelPo> queryModelList(WordModelVo wordModelVo) {
        QueryWrapper<WordModelPo> queryWrapper = new QueryWrapper<WordModelPo>();
        queryWrapper.lambda().like(WordModelPo::getModelName, wordModelVo.getModelName());
        return baseMapper.selectList(queryWrapper);
    }
}
