package com.lanfangyi.nettyim.holder;

import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/21 8:36 PM
 */
public class ChannelHolder {

    private static ConcurrentHashMap<String, Channel> channelMap = new ConcurrentHashMap<>(100);

    public static boolean putAndOverWrite(String key, Channel channel) {
        return channelMap.put(key, channel) != null;
    }

    public static Channel getChannel(String key) {
        return channelMap.get(key);
    }
}
