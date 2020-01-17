package com.lanfangyi.nettyim.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2020/1/15 6:01 PM
 */
public class Md5Util {

    /**
     * 对字符串进行md5加密
     *
     * @param strValue 需要加密的字符串
     * @return 加密之后的结果
     * @throws Exception
     */
    public static String getMD5Str(String strValue) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        return Base64.encodeBase64String(md5.digest(strValue.getBytes()));
    }
}
