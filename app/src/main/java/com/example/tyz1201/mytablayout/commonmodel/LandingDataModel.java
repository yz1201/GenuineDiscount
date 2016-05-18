package com.example.tyz1201.mytablayout.commonmodel;

/**
 * Created by tyz1201 on 2016/4/22.
 */
public class LandingDataModel {
    private String flag;
    private String notice;
    private String orders;
    private String coollect_activities;
    private MyWebData web_users;

    public LandingDataModel(String flag, String notice, String orders, String coollect_activities, MyWebData web_users) {
        this.flag = flag;
        this.notice = notice;
        this.orders = orders;
        this.coollect_activities = coollect_activities;
        this.web_users = web_users;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String getCoollect_activities() {
        return coollect_activities;
    }

    public void setCoollect_activities(String coollect_activities) {
        this.coollect_activities = coollect_activities;
    }

    public MyWebData getWeb_users() {
        return web_users;
    }

    public void setWeb_users(MyWebData web_users) {
        this.web_users = web_users;
    }
}
