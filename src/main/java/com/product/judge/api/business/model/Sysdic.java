package com.product.judge.api.business.model;

import java.io.Serializable;

public class Sysdic implements Serializable
{
    private String dic_name;
    private int dic_value;
    private int dic_type;

    public String getDic_name()
    {
        return dic_name;
    }

    public void setDic_name(String dic_name)
    {
        this.dic_name = dic_name;
    }

    public int getDic_value()
    {
        return dic_value;
    }

    public void setDic_value(int dic_value)
    {
        this.dic_value = dic_value;
    }

    public int getDic_type()
    {
        return dic_type;
    }

    public void setDic_type(int dic_type)
    {
        this.dic_type = dic_type;
    }
}
