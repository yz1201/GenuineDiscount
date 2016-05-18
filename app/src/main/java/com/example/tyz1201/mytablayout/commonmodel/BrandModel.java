package com.example.tyz1201.mytablayout.commonmodel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tyz1201 on 2016/4/20.
 */
public class BrandModel {
    private int id;

    @SerializedName("pic_path")
    private String picUrl;

    @SerializedName("oline")
    private String name;

    @SerializedName("tline")
    private String picPath;

    @SerializedName("outline")
    private String discount;

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }
}
