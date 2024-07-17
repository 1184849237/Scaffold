package org.example.domain.word.adapter.impl;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.example.domain.utils.CustomXWPFDocument;
import org.example.domain.word.adapter.WordModuleAdapter;
import org.example.domain.word.model.entity.WordModuleMsgPo;

/**
 * @ClassName: TextAdapter
 * @Description: 文本实现
 * @Create by: 周鹏程
 * @Date: 2024/7/8 17:40
 */
public class TextAdapter implements WordModuleAdapter {
    @Override
    public void detailFormat() {
        System.out.println("123");
    }

    @Override
    public void writeDocument(CustomXWPFDocument doc, XWPFParagraph paragraph, WordModuleMsgPo wordModuleMsgPo) {
        XWPFRun paraRun = paragraph.createRun();
        String value = wordModuleMsgPo.getModuleValue();
        if (value.indexOf("\n") > 0) {
            String[] text = value.split("\n");
            paraRun = paragraph.insertNewRun(0);
            for (int f = 0; f < text.length; f++) {
                if (f == 0) {
                    paraRun.setText(text[f].trim());
                } else {
                    // 换行
                    paraRun.addBreak();
                    paraRun.setText(text[f].trim());
                }
            }
        } else {
            paraRun.setText(value);
        }
    }
}
