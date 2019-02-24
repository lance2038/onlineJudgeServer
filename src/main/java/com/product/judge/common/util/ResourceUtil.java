package com.product.judge.common.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author haiyan
 * @version v0.0.1
 * @project judgeApi
 * @describe properties读取类
 * @since 2018/5/10
 **/
public class ResourceUtil extends PropertyPlaceholderConfigurer
{
    private static Map<String, String> ctxPropertiesMap;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException
    {
        super.processProperties(beanFactoryToProcess, props);
        ctxPropertiesMap = new HashMap<String, String>();
        for (Object key : props.keySet())
        {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            ctxPropertiesMap.put(keyStr, value);
        }
    }

    /**
     * 获取properties中的内容
     *
     * @param key
     * @return
     */
    public static String getValue(String key)
    {
        return ctxPropertiesMap.get(key);
    }
}
