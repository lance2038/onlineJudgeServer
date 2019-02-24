package com.product.judge.api.business.model;

import java.io.Serializable;

public class Examresult implements Serializable
{
    private Long _id;
    private Long totalscore;
    private String datetime;
    private String usetime;
    private Long totalcount;
    private Long wrongcount;
    private Long rightcount;

    public Long get_id()
    {
        return _id;
    }

    public void set_id(Long _id)
    {
        this._id = _id;
    }

    public Long getTotalscore()
    {
        return totalscore;
    }

    public void setTotalscore(Long totalscore)
    {
        this.totalscore = totalscore;
    }

    public String getDatetime()
    {
        return datetime;
    }

    public void setDatetime(String datetime)
    {
        this.datetime = datetime;
    }

    public String getUsetime()
    {
        return usetime;
    }

    public void setUsetime(String usetime)
    {
        this.usetime = usetime;
    }

    public Long getTotalcount()
    {
        return totalcount;
    }

    public void setTotalcount(Long totalcount)
    {
        this.totalcount = totalcount;
    }

    public Long getWrongcount()
    {
        return wrongcount;
    }

    public void setWrongcount(Long wrongcount)
    {
        this.wrongcount = wrongcount;
    }

    public Long getRightcount()
    {
        return rightcount;
    }

    public void setRightcount(Long rightcount)
    {
        this.rightcount = rightcount;
    }
}
