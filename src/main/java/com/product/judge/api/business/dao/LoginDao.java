package com.product.judge.api.business.dao;

import com.product.judge.api.business.model.Sysuser;
import com.product.judge.common.base.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author haiyan
 * @version v0.0.1
 * @project judgeApi
 * @describe 登录持久层
 * @since 2018/5/10
 **/
@Repository("loginDao")
public class LoginDao extends BaseDao
{
    /**
     * 校验登录信息(教师类型的type=1,没有进行账号注销的usr_flag=1,不是黑名单用户usr_black=0)
     *
     * @return
     */
    public int checkLoginInfo(Sysuser sysuser)
    {
        String sql = "select count(1) flag from sysuser where usr_id = ? and usr_passwd = ? and usr_flag = 1 and usr_type = 1 and usr_black = 0";
        return queryForInt(sql, new String[]{sysuser.getUsr_id(), sysuser.getUsr_passwd()});
    }

    /**
     * 校验用户名是否存在
     *
     * @param usr_id 用户账号名
     * @return
     */
    public int checkUserExists(String usr_id)
    {
        String sql = "select count(1) from sysuser where usr_id = ?";
        return queryForInt(sql, new String[]{usr_id});
    }

    /**
     * 新建用户
     *
     * @param sysuser
     */
    public void saveNewUser(Sysuser sysuser)
    {
        StringBuffer sql = new StringBuffer("\n");
        sql.append("insert into sysuser (usr_id,usr_code,usr_name,usr_passwd,usr_type,usr_mail)");
        sql.append("values(?,replace(uuid(),'-',''),?,?,1,?)");
        update(sql.toString(), new Object[]{sysuser.getUsr_id(), sysuser.getUsr_name(), sysuser.getUsr_passwd(), sysuser.getUsr_mail()});
    }

    /**
     * 通过账号密码获取usr_code
     *
     * @param sysuser
     * @return
     */
    public Sysuser getUsrInfoByIdPw(Sysuser sysuser)
    {
        String sql = "select usr_code from sysuser where usr_id = ? and usr_passwd = ? and usr_flag = 1 and usr_type = 1 and usr_black = 0";
        return queryForModel(sql, new String[]{sysuser.getUsr_id(), sysuser.getUsr_passwd()}, Sysuser.class);
    }
}
