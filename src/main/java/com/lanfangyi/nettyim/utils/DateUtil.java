package com.lanfangyi.nettyim.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.SimpleDateFormat;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/7/31 12:22 PM
 */
public class DateUtil {

    private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

    private final static SimpleDateFormat sdfDay = new SimpleDateFormat(
        "yyyy-MM-dd");

    private final static SimpleDateFormat sdfDays = new SimpleDateFormat(
        "yyyyMMdd");

    private final static SimpleDateFormat sdfTime = new SimpleDateFormat(
        "yyyy-MM-dd HH:mm:ss");

    public static String getNow() {
        return DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");
    }

}


