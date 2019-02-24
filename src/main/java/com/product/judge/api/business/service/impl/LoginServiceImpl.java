package com.product.judge.api.business.service.impl;

import com.product.judge.api.business.dao.LoginDao;
import com.product.judge.api.business.model.Sysuser;
import com.product.judge.api.business.service.LoginService;
import com.product.judge.common.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author haiyan
 * @version v0.0.1
 * @project judgeApi
 * @describe 教师端登录服务层接口实现类
 * @since 2018/5/10
 **/
@Service("loginService")
public class LoginServiceImpl extends BaseServiceImpl implements LoginService
{

    @Autowired
    LoginDao loginDao;

    /**
     * 登录校验
     *
     * @param sysuser
     * @return
     */
    @Override
    public int checkLoginInfo(Sysuser sysuser)
    {
        return loginDao.checkLoginInfo(sysuser);
    }

    /**
     * 通过账号密码获取usr_code
     *
     * @param sysuser
     * @return
     */
    @Override
    public Sysuser getUsrInfoByIdPw(Sysuser sysuser)
    {
        return loginDao.getUsrInfoByIdPw(sysuser);
    }

    /**
     * 判断输入的账号名是否存在
     *
     * @param usr_id
     * @return
     */
    @Override
    public int checkUserExists(String usr_id)
    {
        return loginDao.checkUserExists(usr_id);
    }

    /**
     * 新建用户
     *
     * @param sysuser
     */
    @Override
    public void saveNewUser(Sysuser sysuser)
    {
        loginDao.saveNewUser(sysuser);
    }
}
