package com.lanfangyi.nettyim.filter;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:01 PM
 */
@WebFilter(
    filterName = "druidWebStatFilter",
    urlPatterns = "/*",
    initParams = {
        // 忽略的资源类型
        @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*"),
    })
public class DruidStatFilter extends WebStatFilter {
}