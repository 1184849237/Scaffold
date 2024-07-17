package org.example.domain.word.model.aggregate;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.domain.word.adapter.WordModuleAdapter;
import org.example.domain.word.adapter.impl.PictureAdapter;
import org.example.domain.word.adapter.impl.TextAdapter;
import org.example.domain.word.adapter.poxy.JDKProxy;

/**
 * @ClassName: WordModule
 * @Description: TODO
 * @Create by: 周鹏程
 * @Date: 2024/7/8 17:04
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum WordModule {
    TEXT("0", "文本") {
        @Override
        public WordModuleAdapter getWordModuleAdapter() {
            return JDKProxy.getProxy(WordModuleAdapter.class,new TextAdapter());
        }
    },
    PICTURE("1", "图片") {
        @Override
        public WordModuleAdapter getWordModuleAdapter() {
            return JDKProxy.getProxy(WordModuleAdapter.class,new PictureAdapter());
        }
    },
    CHECK_BOX("2", "勾选框") {
        @Override
        public WordModuleAdapter getWordModuleAdapter() {
            return null;
        }
    },
    FORM("3", "表格") {
        @Override
        public WordModuleAdapter getWordModuleAdapter() {
            return null;
        }
    };
    @EnumValue
    private String code;
    private String info;

    public abstract WordModuleAdapter getWordModuleAdapter();
}
