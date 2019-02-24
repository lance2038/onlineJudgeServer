package com.product.judge.common.util;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * @author haiyan
 * @version v0.0.1
 * @project judgeApi
 * @describe json转换类
 * @since 2018/5/10
 **/
public class JSONUtils
{
    public static String beanToJson(Object object)
    {
        return (null == object) ? null : JSON.toJSONString(object);
    }

    public static String beanToJson(Object object, String dataFormatString)
    {
        return (null == object) ? null : ((StringUtils.isEmpty(dataFormatString)) ? JSON.toJSONString(object) : JSON.toJSONStringWithDateFormat(object, dataFormatString));
    }

    public static <T> T jsonToBean(String json, Class<T> clazz)
    {
        return (StringUtils.isEmpty(json) || clazz == null) ? null : JSON.parseObject(json, clazz);
    }

    public static String stringToJson(String key, String value)
    {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value))
        {
            return null;
        }
        Map<String, String> data = new HashMap();
        data.put(key, value);
        return JSON.toJSONString(data);
    }
}
