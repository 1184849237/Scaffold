package org.example.domain.word.adapter;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.example.domain.utils.CustomXWPFDocument;
import org.example.domain.word.model.entity.WordModuleMsgPo;

/**
 * @ClassName: WordModuleAdapter
 * @Description: word组件适配接口
 * @Create by: 周鹏程
 * @Date: 2024/7/8 17:14
 */
public interface WordModuleAdapter {

    /**
     * 格式处理
     */
    void detailFormat();

    /**
     * 写入word
     */
    void writeDocument(CustomXWPFDocument doc, XWPFParagraph paragraph, WordModuleMsgPo wordModuleMsgPo);

}
