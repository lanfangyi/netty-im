package com.lanfangyi.nettyim;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

@MapperScan("com.lanfangyi.nettyim.mapper")
@SpringBootApplication
@ServletComponentScan
@ComponentScan("com.lanfangyi.*")
public class NettyImApplication {

    public static void main(String[] args) {
        SpringApplication.run(NettyImApplication.class, args);
    }

    @Bean("druidDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }
}
