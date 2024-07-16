package org.example.domain.word.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.domain.obs.ObsUtil;
import org.example.domain.word.model.entity.WordModelPo;
import org.example.domain.word.model.vo.WordModelVo;
import org.example.domain.word.repository.WordModelDao;
import org.example.domain.word.service.WordModelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
@Service
@Transactional
public class WordModelServiceImpl extends ServiceImpl<WordModelDao, WordModelPo> implements WordModelService {

    @Override
    public void uploadModel(WordModelVo wordModelVo) throws IOException {
        MultipartFile modelFile = wordModelVo.getModelFile();
        //校验文件后缀
        ObsUtil.check(Objects.requireNonNull(modelFile.getOriginalFilename()));
        String fileName = modelFile.getOriginalFilename();
        String[] tokens = fileName.split("\\.(?=[^\\.]+$)");
        String uploadName = System.currentTimeMillis() + "." + tokens[tokens.length - 1];
        //调用obs 获得文件url
        String url = ObsUtil.uploadFile(uploadName, modelFile.getBytes());
        WordModelPo wordModelPo = WordModelPo.builder()
                .modelName(wordModelVo.getModelName())
                .modelUrl(url).build();
        wordModelPo.insert();
    }

    @Override
    public List<WordModelPo> queryModelList(WordModelVo wordModelVo) {
        QueryWrapper<WordModelPo> queryWrapper = new QueryWrapper<WordModelPo>();
        queryWrapper.lambda().like(WordModelPo::getModelName, wordModelVo.getModelName());
        return baseMapper.selectList(queryWrapper);
    }
}
