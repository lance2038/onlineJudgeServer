package com.product.judge.api.business.model;

/**
 * 试题批次信息
 */
public class Batchinfo
{
    private String batchid;//批次编号
    private String ownid;//出题人
    private String descinfo;//试题描述信息
    private int buycount;//购买次数
    private Double price;//价格
    private String title;//标题


    public String getDescinfo()
    {
        return descinfo;
    }

    public void setDescinfo(String descinfo)
    {
        this.descinfo = descinfo;
    }

    public int getBuycount()
    {
        return buycount;
    }

    public void setBuycount(int buycount)
    {
        this.buycount = buycount;
    }

    public String getBatchid()
    {
        return batchid;
    }

    public void setBatchid(String batchid)
    {
        this.batchid = batchid;
    }

    public String getOwnid()
    {
        return ownid;
    }

    public void setOwnid(String ownid)
    {
        this.ownid = ownid;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    @Override
    public String toString()
    {
        return "Batchinfo{" + "batchid='" + batchid + '\'' + ", ownid='" + ownid + '\'' + ", descinfo='" + descinfo + '\'' + ", buycount=" + buycount + ", price=" + price + ", title='" + title + '\'' + '}';
    }
}
