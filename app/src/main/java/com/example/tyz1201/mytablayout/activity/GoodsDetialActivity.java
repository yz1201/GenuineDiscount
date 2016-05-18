package com.example.tyz1201.mytablayout.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tyz1201.mytablayout.R;
import com.example.tyz1201.mytablayout.commonmodel.SinglegoodsData;
import com.example.tyz1201.mytablayout.fragment.BrandSinGoodsDetailsFragment;
import com.example.tyz1201.mytablayout.util.BrandInflateHandler;
import com.example.tyz1201.mytablayout.util.InterfaceSchdule;
import com.example.tyz1201.mytablayout.util.NetworkRequest;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;


/**
 * Created by tyz1201 on 2016/5/16.
 */
public class GoodsDetialActivity extends AppCompatActivity {
    private String id;
    private SinglegoodsData datas;
    private TextView textViewTitle, textViewPrice, textViewOldPrice, textViewDiscount, textViewShopping, textViewName, textViewRes, textViewComments;
    private SimpleDraweeView sdvPic, sdvBrandPic;
    private TextView textViewBrandName;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
/**
 *  数据的接收
 */
        Intent intent = getIntent();
        id = intent.getStringExtra("messages");
/**
 *  商品详情
 */
        textViewTitle = (TextView) findViewById(R.id.toolbar_gd_title);
        sdvPic = (SimpleDraweeView) findViewById(R.id.sdv_gd_pic);
        textViewPrice = (TextView) findViewById(R.id.tv_gd_price);
        textViewOldPrice = (TextView) findViewById(R.id.tv_gd_oldprice);
        textViewDiscount = (TextView) findViewById(R.id.tv_gd_discount);
        textViewShopping = (TextView) findViewById(R.id.tv_gd_goshopping);
        textViewName = (TextView) findViewById(R.id.tv_gd_name);
        textViewRes = (TextView) findViewById(R.id.tv_gd_res);
        textViewComments = (TextView) findViewById(R.id.tv_gd_comments);
        sdvBrandPic = (SimpleDraweeView) findViewById(R.id.sdv_gd_brand_pic);
        textViewBrandName = (TextView) findViewById(R.id.tv_gd_brand_name);
        initData();
    }


    private void initData() {
        InterfaceSchdule.createThread(GoodsDetialActivity.this, new BrandInflateHandler() {
            @Override
            public void openNewThread() {
                String gson = NetworkRequest.getRequestData("http://api.zpzk100.com/client/product_detail?id=" + id + "&is_wf=true");
                datas = new Gson().fromJson(gson, SinglegoodsData.class);
            }

            @Override
            public void initUIThread() {
                textViewTitle.setText(datas.getName());
                textViewTitle.setTextColor(getResources().getColor(R.color.black));
                sdvPic.setImageURI(Uri.parse(datas.getImage_url()));
                textViewPrice.setText("¥" + datas.getPrice());
                textViewPrice.setTextColor(getResources().getColor(R.color.text_color_red));
                textViewOldPrice.setText("原价: ¥" + datas.getOld_price());
                textViewOldPrice.setTextColor(getResources().getColor(R.color.text_color_black));
                textViewDiscount.setText(datas.getDiscount() + "折");
                textViewDiscount.setTextColor(getResources().getColor(R.color.white));
                textViewShopping.setTextColor(getResources().getColor(R.color.white));
                textViewName.setText(datas.getName());
                textViewRes.setText("来自: " + datas.getShop_name());
                textViewRes.setTextColor(getResources().getColor(R.color.text_color_black));
                textViewComments.setText("评论数：" + datas.getCount_comment());
                textViewComments.setTextColor(getResources().getColor(R.color.text_color_black));
                sdvBrandPic.setImageURI(Uri.parse(datas.getBrand().getImage_url()));
                textViewBrandName.setText(datas.getBrand().getName());
                LinearLayout tagLayout = (LinearLayout) findViewById(R.id.ll__gd_tags);

                DisplayMetrics dm = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);
                int width = dm.widthPixels;

/**
 *  动态添加按钮，宽度不对。
 */
                if (datas.getTags().size() != 0) {
                    for (int i = 0; i < datas.getTags().size(); i++) {
                        Button button = new Button(GoodsDetialActivity.this);
                        button.setText(datas.getTags().get(i).getName());
                        button.setTextSize(12);
                        button.setTextColor(getResources().getColor(R.color.text_color_red));
                        button.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_rect));
                        button.setGravity(Gravity.CENTER);
                        button.setWidth(width / datas.getTags().size());
                        tagLayout.addView(button);


//                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0,10);
//                        lp.weight = 1;
//                        LinearLayout linearLayout = new LinearLayout(GoodsDetialActivity.this);
//                        Button button1 = new Button(GoodsDetialActivity.this);
//                        button1.setLayoutParams(lp);
//                        Button button2 = new Button(GoodsDetialActivity.this);
//                        button2.setLayoutParams(lp);
//                        linearLayout.addView(button1);
//                        linearLayout.addView(button2);
                    }
                }
/**
 *  看了又看与同款比价。
 */
                ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager_gd_related);
                viewPager.getLayoutParams().height = width / 2 * 3 + 40;
                viewPager.setLayoutParams(viewPager.getLayoutParams());
                TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout_gd);
                viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
                    @Override
                    public Fragment getItem(int position) {
                        return BrandSinGoodsDetailsFragment.newInstance(datas.getRelation_list());
                    }

                    @Override
                    public int getCount() {
                        return 2;
                    }
                });

            }
        });
    }
}
