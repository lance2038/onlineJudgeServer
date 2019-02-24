package com.product.judge.common.base.model;

/**
 * @author lance
 * @version v0.0.1
 * @project judgeApi
 * @describe
 * @since 2018/5/23
 **/
public class Result
{
    private boolean success;
    private String message;

    public Result(boolean success)
    {
        this.success = success;
    }

    public Result(boolean success, String message)
    {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess()
    {
        return success;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
