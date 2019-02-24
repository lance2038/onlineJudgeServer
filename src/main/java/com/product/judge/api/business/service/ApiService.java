package com.product.judge.api.business.service;

import com.product.judge.api.business.model.Sysuser;
import com.product.judge.common.base.service.BaseService;

import java.util.Map;

/**
 * @author haiyan
 * @version v0.0.1
 * @project judgeApi
 * @describe api服务层接口
 * @since 2018/5/10
 **/
public interface ApiService extends BaseService
{
    Map checkLoginInfo(String usr_id, String usr_passwd);

    Sysuser getUserInfo(String usr_id, String usr_passwd);

    void saveNewUser(Sysuser sysuser);
}
