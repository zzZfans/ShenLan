package com.zfans.shenlan.common.base.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作工具类
 *
 * @Author Zfans
 * @DateTime 2021/3/24 21:47
 */
public class FormUtils {

    /**
     * 手机号验证
     */
    public static boolean isMobile(String str) {
        // 验证手机号
        Pattern p = Pattern.compile("0?(13|14|15|17|18|19)[0-9]{9}");
        Matcher m = p.matcher(str);
        return m.matches();
    }

}
