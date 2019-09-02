package com.lanfangyi.nettyim.exception;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/7/31 1:10 PM
 */
public class SendMsgButChannelIsNullxception extends RuntimeException {

    public SendMsgButChannelIsNullxception() {
        super("准备发送消息，但是channel为null");
    }

    public SendMsgButChannelIsNullxception(String message) {
        super(message);
    }

    public SendMsgButChannelIsNullxception(Throwable cause) {
        super(cause);
    }

    public SendMsgButChannelIsNullxception(String message, Throwable cause) {
        super(message, cause);
    }
}
