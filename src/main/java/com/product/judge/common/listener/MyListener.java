package com.product.judge.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener
{
    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        System.out.println("contextInitialized...webApp Start");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
        System.out.println("contextDestroyed...webApp Destroy");
    }
}
