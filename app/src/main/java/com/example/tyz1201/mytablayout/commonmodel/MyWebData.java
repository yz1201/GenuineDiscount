package com.example.tyz1201.mytablayout.commonmodel;

/**
 * Created by tyz1201 on 2016/4/22.
 */
public class MyWebData {
    private String name;
    private String img_url;
    private String user_id;
    private String email;
    private String phone;
    private String join_time;
    private String nickname;
    private String id;
    private String integral;
    private String experience;
    private String freeze_integral;

    public MyWebData(String name, String img_url, String user_id, String email, String phone, String join_time, String nickname, String id, String integral, String experience, String freeze_integral) {
        this.name = name;
        this.img_url = img_url;
        this.user_id = user_id;
        this.email = email;
        this.phone = phone;
        this.join_time = join_time;
        this.nickname = nickname;
        this.id = id;
        this.integral = integral;
        this.experience = experience;
        this.freeze_integral = freeze_integral;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJoin_time() {
        return join_time;
    }

    public void setJoin_time(String join_time) {
        this.join_time = join_time;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getFreeze_integral() {
        return freeze_integral;
    }

    public void setFreeze_integral(String freeze_integral) {
        this.freeze_integral = freeze_integral;
    }
}
