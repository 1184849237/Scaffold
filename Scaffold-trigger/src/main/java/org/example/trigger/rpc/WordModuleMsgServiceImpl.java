package org.example.trigger.rpc;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;
import org.example.api.WordModuleFormatService;
import org.example.api.WordModuleMsgService;
import org.example.domain.obs.SnowFlakeUtil;
import org.example.domain.word.model.entity.WordModuleMsgPo;
import org.example.domain.word.model.vo.WordModuleFormatVo;
import org.example.domain.word.model.vo.WordModuleMsgVo;
import org.example.domain.word.repository.WordModuleMsgDao;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: WordModuleMsgServiceImpl
 * @Description: TODO
 * @Create by: 周鹏程
 * @Date: 2024/7/11 15:42
 */
@DubboService(version = "1.0.0", timeout = 450)
@Transactional
public class WordModuleMsgServiceImpl extends ServiceImpl<WordModuleMsgDao, WordModuleMsgPo> implements WordModuleMsgService {

    @Resource
    WordModuleFormatService wordModuleFormatService;
    @Override
    public void addMsg(List<WordModuleMsgVo> wordModuleMsgVoList) {
        wordModuleMsgVoList.forEach(e->e.setId(SnowFlakeUtil.nextId()));
        //先插入格式信息
        for (WordModuleMsgVo wordModuleMsgVo : wordModuleMsgVoList) {
            WordModuleFormatVo wordModuleFormatVo = wordModuleMsgVo.getWordModuleFormatVo();
            wordModuleFormatService.addFormat(wordModuleFormatVo);
            wordModuleMsgVo.setFormatId(wordModuleFormatVo.getId());
        }
        //插入替换信息
        List<WordModuleMsgPo> wordModuleMsgPos = wordModuleMsgVoList.stream()
                .map(WordModuleMsgVo::toPo).collect(Collectors.toList());
        saveBatch(wordModuleMsgPos);
    }
}
