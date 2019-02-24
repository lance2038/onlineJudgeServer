package com.product.judge.api.business.dao;

import com.product.judge.api.business.model.Sysdic;
import com.product.judge.common.base.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author haiyan
 * @version v0.0.1
 * @project judgeApi
 * @describe 通用sql类
 * @since 2018/5/20
 **/
@Repository("publicMethodDao")
public class PublicMethodDao extends BaseDao
{

    /**
     * 通过字典种类获取数据字典
     *
     * @param type
     * @return
     */
    public List<Sysdic> getSysDicByType(String type)
    {
        String sql = "select dic_name,dic_value from sysdic where dic_type = ?";
        return queryForModelList(sql, new String[]{type}, Sysdic.class);
    }
}
