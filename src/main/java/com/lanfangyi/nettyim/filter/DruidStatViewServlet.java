package com.lanfangyi.nettyim.filter;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:03 PM
 */
@WebServlet(urlPatterns = "/druid/*", initParams={
    @WebInitParam(name="allow",value=""),// IP白名单 (没有配置或者为空，则允许所有访问)
    @WebInitParam(name="deny",value="192.168.16.11"),// IP黑名单 (存在共同时，deny优先于allow)
    @WebInitParam(name="loginUsername",value="admin"),// 用户名
    @WebInitParam(name="loginPassword",value="admin"),// 密码
    @WebInitParam(name="resetEnable",value="true")// 禁用HTML页面上的“Reset All”功能
})
public class DruidStatViewServlet extends StatViewServlet{
}
