package com.product.judge.common.util;


import jodd.bean.BeanCopy;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author haiyan
 * @version v0.0.1
 * @project judgeApi
 * @describe bean工具类
 * @since 2018/5/10
 **/
public class BeanUtil
{
    /**
     * 对象属性拷贝
     *
     * @param orig 源对象
     * @param dest 目标对象
     */
    public static void copyProperties(Object orig, Object dest) throws InvocationTargetException, IllegalAccessException
    {
        try
        {
            if (dest instanceof Map)
            {
                BeanCopy beanCopier = new BeanCopy(orig, dest);
                beanCopier.copy();
            }
            else
            {
                BeanUtils.copyProperties(dest, orig);
            }
        }
        catch (Exception exception)
        {
            throw exception;
        }
    }

}
