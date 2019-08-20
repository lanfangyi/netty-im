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
        @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*"),// 忽略资源
    })
public class DruidStatFilter extends WebStatFilter {
}