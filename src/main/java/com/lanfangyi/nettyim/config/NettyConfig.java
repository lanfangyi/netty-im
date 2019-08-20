package com.lanfangyi.nettyim.config;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/7/30 2:24 PM
 */
public class NettyConfig {

    //存储每一个客户端接入进来的对象
    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}
