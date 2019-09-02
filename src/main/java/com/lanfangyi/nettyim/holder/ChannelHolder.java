package com.lanfangyi.nettyim.holder;

import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * 此类存储channel和用户的信息
 *
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/21 8:36 PM
 */
public class ChannelHolder {

    /**
     * key是userId
     */
    private static ConcurrentHashMap<Long, Channel> channelMap = new ConcurrentHashMap<>(100);

    public static boolean putAndOverWrite(Long userId, Channel channel) {
        return channelMap.put(userId, channel) != null;
    }

    /**
     * 根据key拿到对应的channel
     * @param userId
     */
    public static Channel getChannel(Long userId) {
        return channelMap.get(userId);
    }
}
