package com.product.judge.common.base.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.HashMap;

/**
 * @author haiyan
 * @version v0.0.1
 * @project judgeApi
 * @describe 请求结果封装类
 * @since 2018/5/10
 **/
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class JsonResult extends HashMap<String, Object>
{
    public static final String SUCCESS = "success";

    public static final String MESSAGE = "message";

    public static final String DATA = "data";

    public JsonResult()
    {
        super();
        this.setSuccess(true);
    }

    public JsonResult(Object data)
    {
        super();
        this.setSuccess(true);
        this.setData(data);
    }

    public JsonResult(Object data, int totalCount)
    {
        super();
        this.setSuccess(true);
        this.setData(data);
    }

    public JsonResult(Object data, int totalCount, int start, int limit)
    {
        super();
        this.setSuccess(true);
        this.setData(data);
    }

    public JsonResult(boolean success, String message)
    {
        super();
        this.setSuccess(success);
        this.setMessage(message);
    }

    public JsonResult(boolean success, String message, Object data)
    {
        super();
        this.setSuccess(success);
        this.setMessage(message);
        this.setData(data);
    }

    public boolean getSuccess()
    {
        return (Boolean) this.get(SUCCESS);
    }

    public JsonResult setSuccess(boolean value)
    {
        this.put(SUCCESS, value);
        return this;
    }

    public String getMessage()
    {
        return (String) this.get(MESSAGE);
    }

    public JsonResult setMessage(String value)
    {
        this.put(MESSAGE, value);
        return this;
    }

    public Object getData()
    {
        Object obj = this.get(DATA);
        return obj;
    }

    public JsonResult setData(Object value)
    {
        this.put(DATA, value);
        return this;
    }
}
