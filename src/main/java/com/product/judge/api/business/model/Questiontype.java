package com.product.judge.api.business.model;

import java.io.Serializable;

public class Questiontype implements Serializable
{
    private Long _id;
    private String description;

    public Long get_id()
    {
        return _id;
    }

    public void set_id(Long _id)
    {
        this._id = _id;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
