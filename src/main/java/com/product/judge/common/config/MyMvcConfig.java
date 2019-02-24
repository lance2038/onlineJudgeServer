package com.product.judge.common.config;

import com.product.judge.common.interceptor.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter
{
    @Bean
    public HttpPutFormContentFilter httpPutFormContentFilter()
    {
        return new HttpPutFormContentFilter();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        //浏览器发送 /adminPas 请求来到 success
        registry.addViewController("/adminPas").setViewName("success");
        registry.addViewController("/vue").setViewName("/index-bak.html");
    }

    //所有的WebMvcConfigurerAdapter组件都会一起起作用
    @Bean //将组件注册在容器
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter()
    {
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter()
        {
            @Override
            public void addViewControllers(ViewControllerRegistry registry)
            {
                registry.addViewController("/vue").setViewName("/index-bak.html");
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry)
            {
                //放行登录注册等请求
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/index.html", "/", "/login", "/logon", "/register", "/signup", "/checkuser");
            }
        };
        return adapter;
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        super.addResourceHandlers(registry);
    }

}
