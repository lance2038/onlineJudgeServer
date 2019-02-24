package com.product.judge.api.business.service;

import com.product.judge.api.business.model.Sysuser;
import com.product.judge.common.base.service.BaseService;

/**
 * @author haiyan
 * @version v0.0.1
 * @project judgeApi
 * @describe 教师端登录服务层接口
 * @since 2018/5/10
 **/
public interface LoginService extends BaseService
{
    int checkLoginInfo(Sysuser sysuser);

    Sysuser getUsrInfoByIdPw(Sysuser sysuser);

    int checkUserExists(String usr_id);

    void saveNewUser(Sysuser sysuser);
}
