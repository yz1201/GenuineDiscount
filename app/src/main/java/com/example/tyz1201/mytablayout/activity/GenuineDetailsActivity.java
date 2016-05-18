package com.example.tyz1201.mytablayout.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.tyz1201.mytablayout.R;
import com.example.tyz1201.mytablayout.commonmodel.GenuineContentWedDataModel;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by tyz1201 on 2016/5/17.
 */
public class GenuineDetailsActivity extends AppCompatActivity {
    private GenuineContentWedDataModel model;
    private TextView textViewTitle, textViewPrice, textViewOldPrice, textViewDiscount, textViewShopping, textViewName;
    private SimpleDraweeView sdvPic, sdvBrandPic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genuine_details);

        Intent intent = getIntent();
        model = (GenuineContentWedDataModel) intent.getSerializableExtra("messages");

        textViewTitle = (TextView) findViewById(R.id.tv_genuine_title);
        sdvPic = (SimpleDraweeView) findViewById(R.id.sdv_genuinedetails_pic);
        textViewPrice = (TextView) findViewById(R.id.tv_genuinedetails_price);
        textViewOldPrice = (TextView) findViewById(R.id.tv_genuinedetails_oldprice);
        textViewDiscount = (TextView) findViewById(R.id.tv_genuinedetails_discount);
        textViewShopping = (TextView) findViewById(R.id.tv_genuine_goshopping);
        textViewName = (TextView) findViewById(R.id.tv_genuinedetails_name);
        sdvBrandPic = (SimpleDraweeView) findViewById(R.id.sdv_gd_brand_pic);

        textViewTitle.setText(model.getName());
        textViewTitle.setTextColor(getResources().getColor(R.color.black));
        sdvPic.setImageURI(Uri.parse(model.getImage_url()));
        textViewPrice.setText("¥" + model.getPrice());
        textViewPrice.setTextColor(getResources().getColor(R.color.text_color_red));
        textViewOldPrice.setText("原价: ¥" + model.getOld_price());
        textViewOldPrice.setTextColor(getResources().getColor(R.color.text_color_black));
        textViewDiscount.setText(model.getDiscount() + "折");
        textViewDiscount.setTextColor(getResources().getColor(R.color.white));
        textViewShopping.setTextColor(getResources().getColor(R.color.white));
        textViewName.setText(model.getName());
    }
}
