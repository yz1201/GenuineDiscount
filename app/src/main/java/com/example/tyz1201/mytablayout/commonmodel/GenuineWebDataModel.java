package com.example.tyz1201.mytablayout.commonmodel;

import java.util.List;

/**
 * Created by tyz1201 on 2016/5/11.
 */
public class GenuineWebDataModel {
    private String activity_amount;
    private String id;
    private String image_url;
    private String is_show;
    private String is_special;
    private String name;
    private String new_image;
    private String note;
    private String phone_image;
    private String product_amount;
    private String rank;
    private String redirect_uri;
    private String scp_activity_amount;
    private String phone_image_url;
    private List<GenuineTagsModel> sub_tags;

    public String getActivity_amount() {
        return activity_amount;
    }

    public void setActivity_amount(String activity_amount) {
        this.activity_amount = activity_amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getIs_show() {
        return is_show;
    }

    public void setIs_show(String is_show) {
        this.is_show = is_show;
    }

    public String getIs_special() {
        return is_special;
    }

    public void setIs_special(String is_special) {
        this.is_special = is_special;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNew_image() {
        return new_image;
    }

    public void setNew_image(String new_image) {
        this.new_image = new_image;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPhone_image() {
        return phone_image;
    }

    public void setPhone_image(String phone_image) {
        this.phone_image = phone_image;
    }

    public String getProduct_amount() {
        return product_amount;
    }

    public void setProduct_amount(String product_amount) {
        this.product_amount = product_amount;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getScp_activity_amount() {
        return scp_activity_amount;
    }

    public void setScp_activity_amount(String scp_activity_amount) {
        this.scp_activity_amount = scp_activity_amount;
    }

    public String getPhone_image_url() {
        return phone_image_url;
    }

    public void setPhone_image_url(String phone_image_url) {
        this.phone_image_url = phone_image_url;
    }

    public List<GenuineTagsModel> getSub_tags() {
        return sub_tags;
    }

    public void setSub_tags(List<GenuineTagsModel> sub_tags) {
        this.sub_tags = sub_tags;
    }
}
