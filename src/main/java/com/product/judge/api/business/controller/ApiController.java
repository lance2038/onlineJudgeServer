package com.product.judge.api.business.controller;

import com.product.judge.api.business.model.Sysuser;
import com.product.judge.api.business.service.ApiService;
import com.product.judge.common.base.model.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author haiyan
 * @version v0.0.1
 * @project judgeApi
 * @describe api数据接口
 * @since 2018/5/10
 **/
@Controller
@RequestMapping
public class ApiController
{
    @Autowired
    ApiService apiService;

    /**
     * 校验登录信息
     *
     * @param usr_id
     * @param usr_passwd
     * @return
     */
    @PostMapping("/checkLogin")
    public Map check(String usr_id, String usr_passwd)
    {
        return apiService.checkLoginInfo(usr_id, usr_passwd);
    }

    /**
     * 获取用户信息
     *
     * @param usr_id
     * @param usr_passwd
     * @return
     */
    @PostMapping("/getUserInfo")
    @ResponseBody
    public Sysuser getUserInfo(String usr_id, String usr_passwd)
    {
        return apiService.getUserInfo(usr_id, usr_passwd);
    }

    /**
     * @param sysuser
     * @return
     */
    @PostMapping(value = "/registerAndroidUser")
    @ResponseBody
    public JsonResult register(Sysuser sysuser)
    {
        try
        {
            apiService.saveNewUser(sysuser);
            return new JsonResult(true, "注册成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new JsonResult(false, "注册失败");
        }


    }
}
