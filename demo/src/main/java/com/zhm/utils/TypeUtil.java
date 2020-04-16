package com.zhm.utils;

import java.util.regex.Pattern;

/**
 * @author zhm
 * @date 2020/4/15 17:02
 */
public class TypeUtil {
    /**
     * 验证是否整数
     *
     * @param str
     * @return
     */
    public static boolean isNum(String str) {
        Pattern pattern = Pattern.compile("^-?[0-9]+");
        if (pattern.matcher(str).matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 验证是否小数
     *
     * @param str
     * @return
     */
    public static boolean isNumEx(String str) {
        Pattern pattern = Pattern.compile("^[-+]?[0-9]+(\\.[0-9]+)?$");
        if (pattern.matcher(str).matches()) {
            return true;
        } else {
            return false;
        }
    }
}
