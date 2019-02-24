package com.product.judge.api.business.model;

import java.io.Serializable;

public class Sysuser implements Serializable
{
    private String usr_id;
    private String usr_code;
    private String usr_name;
    private String usr_passwd;
    private Long usr_type;
    private Long usr_flag;
    private Long usr_black;
    private String usr_mail;
    private String usr_phone;
    private String usr_mark;

    public String getUsr_id()
    {
        return usr_id;
    }

    public void setUsr_id(String usr_id)
    {
        this.usr_id = usr_id;
    }

    public String getUsr_code()
    {
        return usr_code;
    }

    public void setUsr_code(String usr_code)
    {
        this.usr_code = usr_code;
    }

    public String getUsr_name()
    {
        return usr_name;
    }

    public void setUsr_name(String usr_name)
    {
        this.usr_name = usr_name;
    }

    public String getUsr_passwd()
    {
        return usr_passwd;
    }

    public void setUsr_passwd(String usr_passwd)
    {
        this.usr_passwd = usr_passwd;
    }

    public Long getUsr_type()
    {
        return usr_type;
    }

    public void setUsr_type(Long usr_type)
    {
        this.usr_type = usr_type;
    }

    public Long getUsr_flag()
    {
        return usr_flag;
    }

    public void setUsr_flag(Long usr_flag)
    {
        this.usr_flag = usr_flag;
    }

    public Long getUsr_black()
    {
        return usr_black;
    }

    public void setUsr_black(Long usr_black)
    {
        this.usr_black = usr_black;
    }

    public String getUsr_mail()
    {
        return usr_mail;
    }

    public void setUsr_mail(String usr_mail)
    {
        this.usr_mail = usr_mail;
    }

    public String getUsr_phone()
    {
        return usr_phone;
    }

    public void setUsr_phone(String usr_phone)
    {
        this.usr_phone = usr_phone;
    }

    public String getUsr_mark()
    {
        return usr_mark;
    }

    public void setUsr_mark(String usr_mark)
    {
        this.usr_mark = usr_mark;
    }
}
