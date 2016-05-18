package com.example.tyz1201.mytablayout.commonmodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyz1201 on 2016/5/4.
 */
public class MyHomepageWebData {
    private List<HotBrands> hot_brands = new ArrayList<>();
    private List<ActivitiesModel> activities = new ArrayList<>();

    public List<ActivitiesModel> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivitiesModel> activities) {
        this.activities = activities;
    }

    public List<HotBrands> getHot_brands() {
        return hot_brands;
    }

    public void setHot_brands(List<HotBrands> hot_brands) {
        this.hot_brands = hot_brands;
    }
}
