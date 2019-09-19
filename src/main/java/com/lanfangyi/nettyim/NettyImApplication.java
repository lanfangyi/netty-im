package com.lanfangyi.nettyim;

import com.alibaba.druid.pool.DruidDataSource;
import com.lanfangyi.nettyim.pool.FutureTaskPool;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;

@MapperScan("com.lanfangyi.nettyim.mapper")
@SpringBootApplication
@ServletComponentScan
@ComponentScan("com.lanfangyi.*") //不加这一行，jar包中的aop不生效
public class NettyImApplication {

    public static void main(String[] args) {
        SpringApplication.run(NettyImApplication.class, args);
    }

    @Bean("druidDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    @PreDestroy
    public void shutDown() {
        FutureTaskPool.shutDown();
    }
}
