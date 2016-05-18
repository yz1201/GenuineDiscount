package com.example.tyz1201.mytablayout.commonmodel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tyz1201 on 2016/5/9.
 */
public class SinglegoodsData implements Serializable {
    private int id;
    private String name;
    private String discount;
    private String price;
    private String old_price;
    private String image_url;
    private String shop_name;
    private String count_comment;
    private BrandContent brand;
    private List<Tags> tags;
    private List<RelatedGoods> relation_list;

    public BrandContent getBrand() {
        return brand;
    }

    public void setBrand(BrandContent brand) {
        this.brand = brand;
    }


    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public List<RelatedGoods> getRelation_list() {
        return relation_list;
    }

    public void setRelation_list(List<RelatedGoods> relation_list) {
        this.relation_list = relation_list;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getCount_comment() {
        return count_comment;
    }

    public void setCount_comment(String count_comment) {
        this.count_comment = count_comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOld_price() {
        return old_price;
    }

    public void setOld_price(String old_price) {
        this.old_price = old_price;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
