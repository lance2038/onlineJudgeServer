package com.product.judge.api.plugins.timer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author haiyan
 * @version v0.0.1
 * @project judgeApi
 * @describe 定时任务
 * @since 2018/5/10
 **/
@Component
public class Timer
{
    @Scheduled(cron = "0 0/30 * * * ? ") //cron表达式
    public void doSomething() throws Exception
    {
        System.out.println("系统运行中：" + new Date());
    }

}