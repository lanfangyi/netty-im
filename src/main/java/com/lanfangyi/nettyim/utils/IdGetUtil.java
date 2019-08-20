package com.lanfangyi.nettyim.utils;

import java.util.Date;
import java.util.Random;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/4 12:39 AM
 */
public class IdGetUtil {

    private static Random random = new Random();

//    private static AtomicLong atomicLong = new AtomicLong(new Date().getTime());

    public static Long get() {
        return Long.valueOf(new Date().getTime() + "" + random.nextInt(9) + "" + random.nextInt(9));
    }
}
