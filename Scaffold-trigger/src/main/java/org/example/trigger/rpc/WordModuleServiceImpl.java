package org.example.trigger.rpc;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.common.Experimental;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.poi.xwpf.usermodel.*;
import org.example.api.WordModuleMsgService;
import org.example.api.WordModuleService;
import org.example.domain.obs.ObsUtil;
import org.example.domain.obs.SnowFlakeUtil;
import org.example.domain.utils.CustomXWPFDocument;
import org.example.domain.utils.FileDownloadUtils;
import org.example.domain.utils.PatternUtil;
import org.example.domain.word.model.entity.WordModuleMsgPo;
import org.example.domain.word.model.entity.WordModulePo;
import org.example.domain.word.model.vo.WordModuleMsgVo;
import org.example.domain.word.model.vo.WordModuleVo;
import org.example.domain.word.repository.WordModuleDao;
import org.example.types.validated.GroupAdd;
import org.example.types.validated.GroupGenerate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName: WordModuleServiceImpl
 * @Description: TODO
 * @Create by: 周鹏程
 * @Date: 2024/7/11 15:45
 */
@DubboService(version = "1.0.0", timeout = 450,validation = "true")
@Transactional(rollbackFor = Exception.class)
public class WordModuleServiceImpl extends ServiceImpl<WordModuleDao, WordModulePo> implements WordModuleService {

    @Resource
    WordModuleMsgService wordModuleMsgService;


    @Override
    public IPage<WordModulePo> queryPage(WordModulePo wordModulePo) {
        QueryWrapper<WordModulePo> queryWrapper = new QueryWrapper<WordModulePo>();
        return baseMapper.selectPage(new Page<>(0, 10), queryWrapper);
    }

    @Override
    public void addModule(@Validated(GroupAdd.class) WordModuleVo wordModuleVo) {
        //插入word替换信息
        List<WordModuleMsgVo> wordModuleMsgVos = wordModuleVo.getWordModuleMsgVos();
        wordModuleMsgVos.forEach(e->e.setModuleId(SnowFlakeUtil.nextId()));
        wordModuleMsgService.addMsg(wordModuleMsgVos);
        //新增
        WordModulePo wordModulePo = WordModulePo.builder()
                .moduleName(wordModuleVo.getModuleName())
                .modelId(wordModuleVo.getModelId()).build();
        wordModulePo.insert();
    }


    @Override
    public WordModulePo queryById(@NotNull(message = "写入模板id不能为空") Long moduleId) {
        return baseMapper.queryById(moduleId);
    }

    @Override
    public void generateWordModuleById(@NotNull(message = "写入模板id不能为空")Long moduleId, HttpServletResponse response) throws Exception {
        WordModulePo wordModulePo = baseMapper.queryById(moduleId);
        ByteArrayInputStream byteArrayInputStream = makeWord(wordModulePo);
        FileDownloadUtils.fileDownload(byteArrayInputStream,wordModulePo.getModuleName(),response);
    }

    @Override
    public void generateWordModuleWithVo(@Validated(GroupGenerate.class) WordModuleVo wordModuleVo, HttpServletResponse response) throws Exception {
        WordModulePo wordModulePo = wordModuleVo.toPo();
        ByteArrayInputStream byteArrayInputStream = makeWord(wordModulePo);
        FileDownloadUtils.fileDownload(byteArrayInputStream,wordModulePo.getModuleName(),response);
    }

    @Override
    public ByteArrayInputStream makeWord(WordModulePo wordModulePo) throws Exception {
        if (ObjectUtil.isEmpty(wordModulePo)) {
            throw new RuntimeException("不存在的记录 无法生成");
        }
        //读取模板文件 获取文件流
        InputStream moduleStream = ObsUtil.downFile(wordModulePo.getWordModelPo().getModelUrl());
        CustomXWPFDocument doc = new CustomXWPFDocument(moduleStream);
        //获取段落信息
        List<XWPFParagraph> paragraphs = doc.getParagraphs();
        //修改替换信息结构
        List<WordModuleMsgPo> wordModuleMsgPoList = wordModulePo.getWordModuleMsgPoList();
        Map<String, WordModuleMsgPo> keyModule = wordModuleMsgPoList.stream().collect(
                Collectors.toMap(WordModuleMsgPo::getModuleKey, Function.identity()));
        for (XWPFParagraph paragraph : paragraphs) {
            //正则匹配替换内容
            if (!PatternUtil.matcher(paragraph.getParagraphText()).find()) {
                continue;
            }
            List<XWPFRun> runs = paragraph.getRuns();
            for (int i = 0; i < runs.size(); i++) {
                String runText = runs.get(i).toString();
                if (!PatternUtil.matcher(runText).find() && keyModule.containsKey(runText)) {
                    paragraph.removeRun(i);
                    WordModuleMsgPo wordModuleMsgPo = keyModule.get(runText);
                    //根据moduleType进行对应抽象工厂调用
                    wordModuleMsgPo.getModuleType().getWordModuleAdapter()
                            .writeDocument(doc,paragraph,wordModuleMsgPo);
                }
            }
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        doc.write(byteArrayOutputStream);
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

}
