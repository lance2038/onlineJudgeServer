package com.product.judge.api.business.dao;

import com.product.judge.api.business.model.Sysuser;
import com.product.judge.common.base.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author haiyan
 * @version v0.0.1
 * @project judgeApi
 * @describe api持久层
 * @since 2018/5/10
 **/
@Repository("apiDao")
public class ApiDao extends BaseDao
{
    /**
     * 校验登录信息
     *
     * @param usr_id
     * @param usr_passwd
     * @return
     */
    public Map checkLoginInfo(String usr_id, String usr_passwd)
    {
        String sql = "select count(1) flag from sysuser where usr_id = ? and usr_passwd = ?";
        return queryForMap(sql, new String[]{usr_id, usr_passwd});
    }

    /**
     * 查询用户信息
     *
     * @return
     */
    public Sysuser getUserInfo(String usr_id, String usr_passwd)
    {
        String sql = "select * from sysuser where usr_id = ? and usr_passwd = ? and usr_flag = 1 and usr_type = 0 and usr_black = 0";
        return queryForModel(sql, new String[]{usr_id, usr_passwd}, Sysuser.class);
    }

    /**
     * 保存新用户
     *
     * @param sysuser
     */
    public void saveNewUser(Sysuser sysuser)
    {
        StringBuffer sql = new StringBuffer("\n");
        sql.append("insert into sysuser (usr_id,usr_code,usr_name,usr_passwd,usr_type,usr_mail)");
        sql.append("values(?,replace(uuid(),'-',''),?,?,0,?)");
        update(sql.toString(), new Object[]{sysuser.getUsr_id(), sysuser.getUsr_name(), sysuser.getUsr_passwd(), sysuser.getUsr_mail()});
    }
}
