package com.example.tyz1201.mytablayout.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tyz1201.mytablayout.R;
import com.example.tyz1201.mytablayout.commonadapter.BrandAdapter;
import com.example.tyz1201.mytablayout.commonadapter.BrandSinglegoodsAdapter;
import com.example.tyz1201.mytablayout.commonmodel.BrandDataModel;
import com.example.tyz1201.mytablayout.commonmodel.SinglegoodsData;
import com.example.tyz1201.mytablayout.util.BrandInflateHandler;
import com.example.tyz1201.mytablayout.util.InterfaceSchdule;
import com.example.tyz1201.mytablayout.util.NetworkRequest;
import com.example.tyz1201.mytablayout.util.RefreshLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyz1201 on 2016/5/9.
 */
public class BrandSingleGoodsActivity extends AppCompatActivity {
    private List<SinglegoodsData> singlegoodsDatas = new ArrayList<>();
    private GridView gridView;
    private String brandId;
    private String brandName;
    private int page = 1;
    private String[] strs = null;
    private RefreshLayout refreshLayout;
    private BrandSinglegoodsAdapter brandSinglegoodsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_brand_singlegoods);
        super.onCreate(savedInstanceState);

        TextView textViewTitle = (TextView) findViewById(R.id.tv_brand_title);
        textViewTitle.setText("明星产品");
/**
 *  从上个界面得到对应的品牌名字，通过这个品牌去获取对应数据。
 */
        Intent intent = getIntent();
        String str = intent.getStringExtra("BrandName");
        strs = str.split("/");
        brandName = strs[0];
        brandId = strs[1];
/**
 *  Toolbar中显示内容的设置
 */
        final ImageView imageView = (ImageView) findViewById(R.id.iv_sggoods_back);
        TextView textView = (TextView) findViewById(R.id.tv_sggoods_title);
//        ImageView imageViewGuanzhu = (ImageView) findViewById(R.id.iv_guanzhu);
//        imageViewGuanzhu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imageView.setImageResource(gridView.getResources().getDrawable(R.mipmap.guanzhu_down));
//            }
//        });
        textView.setText(brandName);
        textView.setTextColor(getResources().getColor(R.color.text_color_black));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        refreshLayout = (RefreshLayout) findViewById(R.id.srl_brand_singlegoods);
        gridView = (GridView) findViewById(R.id.gv_brand_singlegoods);
        brandSinglegoodsAdapter = new BrandSinglegoodsAdapter(BrandSingleGoodsActivity.this, singlegoodsDatas);
        gridView.setAdapter(brandSinglegoodsAdapter);
/**
 * 刷新，显示第一页的数据
 */
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                initFirstPageData();
            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initFirstPageData();
            }
        });
/**
 * 加载下一页的数据
 */
        refreshLayout.setChildView(gridView);
        refreshLayout.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                page++;
                initData();
                refreshLayout.setLoading(false);
            }
        });

    }

    private void initData() {
        InterfaceSchdule.createThread(BrandSingleGoodsActivity.this, new BrandInflateHandler() {
            @Override
            public void openNewThread() {
                String gson = NetworkRequest.getRequestData("http://api.zpzk100.com/api/brand/detail?brand_id=" + brandId + "&page=" + page + "&sort=price-desc");
                List<SinglegoodsData> datas = new Gson().fromJson(gson, new TypeToken<List<SinglegoodsData>>() {
                }.getType());
                singlegoodsDatas.addAll(datas);
            }

            @Override
            public void initUIThread() {
                brandSinglegoodsAdapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }
        });
    }

    private void initFirstPageData() {
        InterfaceSchdule.createThread(BrandSingleGoodsActivity.this, new BrandInflateHandler() {
            @Override
            public void openNewThread() {
                String gson = NetworkRequest.getRequestData("http://api.zpzk100.com/api/brand/detail?brand_id=" + brandId + "&page=1&sort=price-desc");
                List<SinglegoodsData> datas = new Gson().fromJson(gson, new TypeToken<List<SinglegoodsData>>() {
                }.getType());
                singlegoodsDatas.removeAll(singlegoodsDatas);
                singlegoodsDatas.addAll(datas);
            }

            @Override
            public void initUIThread() {
                brandSinglegoodsAdapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }
        });
    }

}

//        toolbar.setTitle(brandName);
//        toolbar.setTitleTextColor(0xff757575);
//        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.back_03black));
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_brand_single_goods);