package com.lanfangyi.nettyim.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.SimpleDateFormat;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/7/31 12:22 PM
 */
public class DateUtil {

    private static SimpleDateFormat getSdfYear() {
        return new SimpleDateFormat("yyyy");
    }

    private static SimpleDateFormat getSdfDay() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    private static SimpleDateFormat getSdfDays() {
        return new SimpleDateFormat("yyyyMMdd");
    }

    private static SimpleDateFormat getSdfTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public static String getNow() {
        return DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");
    }

}


