package com.product.judge.common.base.model;

import java.io.Serializable;

/**
 * @author haiyan
 * @version v0.0.1
 * @project judgeApi
 * @describe 日志model类
 * @since 2018/5/10
 **/
public class Sqllog implements Serializable
{
    private static final long serialVersionUID = 7003339443903675827L;
    private String id;
    private String sql;
    private Long timeConsuming;
    private String success;
    private String callLocation;
    private int lineNum;
    private String argsInfo;

    public Sqllog()
    {
    }

    public Sqllog(String id, String sql, Long timeConsuming, String success, String callLocation, int lineNum, String argsInfo)
    {
        this.id = id;
        this.sql = sql;
        this.timeConsuming = timeConsuming;
        this.success = success;
        this.callLocation = callLocation;
        this.lineNum = lineNum;
        this.argsInfo = argsInfo;
    }

    public String toString()
    {
        String str = "Sql日志: sqlID [ " + this.id + " ] 操作的sql [ " + this.sql + " ] 调用位置 [ " + this.callLocation + ":" + this.lineNum + " ] 消耗时间 [" + timeConsuming + "毫秒]";
        return str;
    }

    public static long getSerialVersionUID()
    {
        return serialVersionUID;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getSql()
    {
        return sql;
    }

    public void setSql(String sql)
    {
        this.sql = sql;
    }

    public Long getTimeConsuming()
    {
        return timeConsuming;
    }

    public void setTimeConsuming(Long timeConsuming)
    {
        this.timeConsuming = timeConsuming;
    }

    public String getSuccess()
    {
        return success;
    }

    public void setSuccess(String success)
    {
        this.success = success;
    }

    public String getCallLocation()
    {
        return callLocation;
    }

    public void setCallLocation(String callLocation)
    {
        this.callLocation = callLocation;
    }

    public int getLineNum()
    {
        return lineNum;
    }

    public void setLineNum(int lineNum)
    {
        this.lineNum = lineNum;
    }

    public String getArgsInfo()
    {
        return argsInfo;
    }

    public void setArgsInfo(String argsInfo)
    {
        this.argsInfo = argsInfo;
    }
}
