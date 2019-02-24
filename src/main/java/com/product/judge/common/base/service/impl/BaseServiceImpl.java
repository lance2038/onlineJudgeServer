package com.product.judge.common.base.service.impl;

import com.product.judge.common.base.dao.BaseDao;
import com.product.judge.common.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author haiyan
 * @version v0.0.1
 * @project judgeApi
 * @describe 服务层基类实现类
 * @since 2018/5/10
 **/
@Service("baseService")
public class BaseServiceImpl implements BaseService
{
    @Autowired
    private BaseDao baseDao;

    public BaseDao getBaseDao()
    {
        return baseDao;
    }

    public void setBaseDao(BaseDao baseDao)
    {
        this.baseDao = baseDao;
    }

}
