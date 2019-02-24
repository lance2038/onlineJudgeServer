package com.product.judge.api.business.service.impl;

import com.product.judge.api.business.dao.ApiDao;
import com.product.judge.api.business.model.Sysuser;
import com.product.judge.api.business.service.ApiService;
import com.product.judge.common.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author haiyan
 * @version v0.0.1
 * @project judgeApi
 * @describe api服务层接口实现类
 * @since 2018/5/10
 **/
@Service("apiService")
public class ApiServiceImpl extends BaseServiceImpl implements ApiService
{

    @Autowired
    ApiDao apiDao;


    @Override
    public Map checkLoginInfo(String usr_id, String usr_passwd)
    {
        return apiDao.checkLoginInfo(usr_id, usr_passwd);
    }

    @Override
    public Sysuser getUserInfo(String usr_id, String usr_passwd)
    {
        return apiDao.getUserInfo(usr_id, usr_passwd);
    }

    /**
     * 保存新用户
     *
     * @param sysuser
     */
    @Override
    public void saveNewUser(Sysuser sysuser)
    {
        apiDao.saveNewUser(sysuser);
    }
}
