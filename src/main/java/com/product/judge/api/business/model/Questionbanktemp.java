package com.product.judge.api.business.model;

import java.io.Serializable;

public class Questionbanktemp implements Serializable
{
    private int titleid;
    private int classid;
    private String question;
    private String opt1;
    private String opt2;
    private String opt3;
    private String opt4;
    private String answer;
    private String explainmsg;
    private String ownid;
    private String batchid;

    public int getTitleid()
    {
        return titleid;
    }

    public void setTitleid(int titleid)
    {
        this.titleid = titleid;
    }

    public int getClassid()
    {
        return classid;
    }

    public void setClassid(int classid)
    {
        this.classid = classid;
    }

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    public String getOpt1()
    {
        return opt1;
    }

    public void setOpt1(String opt1)
    {
        this.opt1 = opt1;
    }

    public String getOpt2()
    {
        return opt2;
    }

    public void setOpt2(String opt2)
    {
        this.opt2 = opt2;
    }

    public String getOpt3()
    {
        return opt3;
    }

    public void setOpt3(String opt3)
    {
        this.opt3 = opt3;
    }

    public String getOpt4()
    {
        return opt4;
    }

    public void setOpt4(String opt4)
    {
        this.opt4 = opt4;
    }

    public String getAnswer()
    {
        return answer;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
    }

    public String getExplainmsg()
    {
        return explainmsg;
    }

    public void setExplainmsg(String explainmsg)
    {
        this.explainmsg = explainmsg;
    }

    public String getOwnid()
    {
        return ownid;
    }

    public void setOwnid(String ownid)
    {
        this.ownid = ownid;
    }

    public String getBatchid()
    {
        return batchid;
    }

    public void setBatchid(String batchid)
    {
        this.batchid = batchid;
    }
}
