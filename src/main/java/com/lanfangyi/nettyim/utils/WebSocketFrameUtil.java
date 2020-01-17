package com.lanfangyi.nettyim.utils;

import com.alibaba.fastjson.JSON;
import com.lanfangyi.nettyim.vo.responsevo.WebSocketResponseVo;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2020/1/17 3:37 PM
 */
@Slf4j
public class WebSocketFrameUtil {

    public static TextWebSocketFrame getTextWebSocketFrame(Long providerId,
                                                           String providerType,
                                                           Long receiveUserId,
                                                           String receiveUserType,
                                                           String text) {
        if (StringUtils.isEmpty(text)) {
            log.error("WebSocketFrameUtil text 不能为空. time:{}", DateUtil.getNow());
        }
        WebSocketResponseVo webSocketResponseVo = new WebSocketResponseVo();
        webSocketResponseVo.setProviderId(providerId);
        webSocketResponseVo.setProviderType(providerType);
        webSocketResponseVo.setReceiveUserId(receiveUserId);
        webSocketResponseVo.setReceiveUserType(receiveUserType);
        webSocketResponseVo.setData(text);
        return new TextWebSocketFrame(JSON.toJSONString(webSocketResponseVo));
    }
}
