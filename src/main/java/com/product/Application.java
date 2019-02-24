package com.product;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author haiyan
 * @version v0.0.1
 * @project judgeApi
 * @describe 入口
 * @since 2018/5/10
 **/
@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement(proxyTargetClass = true)
public class Application
{
    public static void main(String[] args)
    {
        new SpringApplicationBuilder(Application.class).run(args);
    }
}
