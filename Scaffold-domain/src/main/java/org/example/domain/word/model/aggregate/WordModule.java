package org.example.types.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    TEXT("0", "文本"),
    PICTURE("1", "图片"),
    CHECK_BOX("2", "勾选框"),
    FORM("3", "表格")
    ;

    private String code;
    private String info;
}
