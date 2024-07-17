package org.example.domain.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: PatternUtil
 * @Description: TODO
 * @Create by: 周鹏程
 * @Date: 2024/7/12 15:16
 */
public class PatternUtil {
    //正则表达式 匹配${}
    private static final Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);

    public static Matcher matcher(String str) {
        return pattern.matcher(str);
    }
}
