package com.product.judge.api.business.controller;

import com.product.judge.api.business.model.Sysuser;
import com.product.judge.api.business.service.LoginService;
import com.product.judge.common.base.model.JsonResult;
import com.product.judge.common.util.SessionUtil;
import com.product.judge.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
public class LoginController
{
    @Autowired
    LoginService loginService;

    public static final String LOGIN = "login";//登录界面
    public static final String SIGNUP = "signup";//注册界面

    /**
     * 跳转到登录界面
     *
     * @return
     */
    @GetMapping(value = "login")
    public String login()
    {
        return LOGIN;
    }

    /**
     * 登录事件处理
     *
     * @param username
     * @param password
     * @param map
     * @param session
     * @return
     */
    @PostMapping(value = "/logon")
    public String logon(@RequestParam("username") String username, @RequestParam("password") String password, Map<String, Object> map, HttpSession session)
    {
        Sysuser sysuser = new Sysuser();
        sysuser.setUsr_id(username);
        sysuser.setUsr_passwd(StringUtil.getMD5Str(password));
        sysuser = loginService.getUsrInfoByIdPw(sysuser);
        if (!StringUtil.isNullString(sysuser.getUsr_code()))
        {
            //登陆成功，防止表单重复提交，可以重定向到主页
            session.setAttribute(SessionUtil.SHOW_ATTR, sysuser.getUsr_name());
            session.setAttribute(SessionUtil.SESSION_ATTR, sysuser.getUsr_code());
            return "redirect:/main.html";
        }
        else
        {
            //登陆失败
            map.put("msg", "用户名或密码错误");
            return LOGIN;
        }
    }

    /**
     * 登出
     *
     * @return
     */
    @GetMapping(value = "/logout")
    public String logout()
    {
        return LOGIN;
    }

    /**
     * 跳转到注册页面
     *
     * @return
     */
    @GetMapping(value = "/signup")
    public String signup()
    {
        return SIGNUP;
    }

    /**
     * 校验用户
     *
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/checkuser")
    public JsonResult checkuser(@RequestBody Sysuser sysuser)
    {
        if (loginService.checkUserExists(sysuser.getUsr_id()) > 0)
        {
            return new JsonResult(true, "账号已存在");
        }
        else
        {
            return new JsonResult(false, "账号不存在");
        }
    }

    /**
     * @param sysuser
     * @return
     */
    @PostMapping(value = "/register")
    public String register(Sysuser sysuser, Model model)
    {
        sysuser.setUsr_passwd(StringUtil.getMD5Str(sysuser.getUsr_passwd()));
        loginService.saveNewUser(sysuser);
        model.addAttribute("regFlag", "1");
        model.addAttribute("usr_id", sysuser.getUsr_id());
        model.addAttribute("usr_mail", sysuser.getUsr_mail());
        model.addAttribute("usr_name", sysuser.getUsr_name());
        return SIGNUP;
    }

    /**
     * 修改用户信息
     *
     * @return
     */
    @RequestMapping(value = "/modifyUserInfo")
    public String modifyUserInfo()
    {
        return "modifyUserInfo";
    }
}
