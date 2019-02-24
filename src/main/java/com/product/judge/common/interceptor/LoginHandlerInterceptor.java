package com.product.judge.common.interceptor;

import com.product.judge.common.util.SessionUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆检查，
 */
public class LoginHandlerInterceptor implements HandlerInterceptor
{
    //目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {

        Object user = request.getSession().getAttribute(SessionUtil.SESSION_ATTR);
        if ("androidRequest".equals(request.getHeader("entryType")))
        {
            return true;
        }
        if (user == null)
        {
            //未登陆，返回登陆页面
            request.setAttribute("msg", "没有权限请先登陆");
            request.getRequestDispatcher("/login").forward(request, response);
            return false;
        }
        else
        {
            //已登陆，放行请求
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {
        if (response.getStatus() == 500)
        {
            modelAndView.setViewName("error/5xx");
        }
        else if (response.getStatus() == 404)
        {
            modelAndView.setViewName("error/404");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {

    }
}
