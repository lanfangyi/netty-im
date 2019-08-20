package com.lanfangyi.nettyim.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/1 2:37 PM
 */
public class UUIDUtil {

    public static String get() {
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }

    public static List<String> get(int num) {
        List<String> uuidList = new ArrayList<>();
        if (num > 0) {
            for (int i = 0; i < num; i++) {
                uuidList.add(get());
            }
        }
        return uuidList;
    }
}
