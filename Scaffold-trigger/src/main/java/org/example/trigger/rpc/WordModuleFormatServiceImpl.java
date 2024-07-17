package org.example.trigger.rpc;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;
import org.example.api.WordModuleFormatService;
import org.example.domain.obs.SnowFlakeUtil;
import org.example.domain.word.model.entity.WordModuleFormatPo;
import org.example.domain.word.model.vo.WordModuleFormatVo;
import org.example.domain.word.repository.WordModuleFormatDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: WordModuleFormatServiceImpl
 * @Description: TODO
 * @Create by: 周鹏程
 * @Date: 2024/7/11 15:39
 */
@Service
@Transactional
public class WordModuleFormatServiceImpl extends ServiceImpl<WordModuleFormatDao, WordModuleFormatPo> implements WordModuleFormatService {

    @Override
    public void addFormat(WordModuleFormatVo wordModuleFormatVo) {
        wordModuleFormatVo.setId(SnowFlakeUtil.nextId());
        WordModuleFormatPo wordModuleFormatVoPo = wordModuleFormatVo.toPo();
        wordModuleFormatVoPo.insert();
    }
}
