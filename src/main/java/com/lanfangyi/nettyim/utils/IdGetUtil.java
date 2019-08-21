package com.lanfangyi.nettyim.utils;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/4 12:39 AM
 */
public class IdGetUtil {

    private static AtomicLong atomicLong = new AtomicLong();

    private static long getRandomNum() {
        long randomNum = atomicLong.getAndIncrement();
        if (randomNum == Integer.MAX_VALUE) {
            atomicLong.set(0);
        }
        return randomNum;
    }

    /**
     * 最大并发：Integer.MAX_VALUE
     */
    public static Long get() {
        return Long.valueOf(System.currentTimeMillis() + "" + getRandomNum());
    }
}
