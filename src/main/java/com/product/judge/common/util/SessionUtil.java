package com.product.judge.common.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author haiyan
 * @version v0.0.1
 * @project judgeApi
 * @describe session工具类
 * @since 2018/5/20
 **/
public class SessionUtil
{
    public static final String SHOW_ATTR = "loginUser";
    public static final String SESSION_ATTR = "_loginUser";

    /**
     * 获取当前用户
     *
     * @param request
     * @return
     */
    public static String getCurrentUser(HttpServletRequest request)
    {
        String user = (String) request.getSession().getAttribute(SessionUtil.SESSION_ATTR);
        return user;
    }

    /**
     * 输出request结果
     *
     * @param request
     */
    public static void printRequestMsg(HttpServletRequest request)
    {
        Enumeration rnames = request.getParameterNames();
        for (Enumeration e = rnames; e.hasMoreElements(); )
        {
            String thisName = e.nextElement().toString();
            String thisValue = request.getParameter(thisName);
            System.out.println(thisName + ":------->>>" + thisValue);
        }
    }
}
