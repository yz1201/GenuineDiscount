package com.example.tyz1201.mytablayout.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tyz1201.mytablayout.R;
import com.example.tyz1201.mytablayout.fragment.BrandFragment;
import com.example.tyz1201.mytablayout.fragment.GenuineFragment;
import com.example.tyz1201.mytablayout.fragment.HomePageFragment;
import com.example.tyz1201.mytablayout.fragment.MyFragment;
import com.example.tyz1201.mytablayout.fragment.SpFragment;
import com.example.tyz1201.mytablayout.util.Injector;
import com.facebook.drawee.backends.pipeline.Fresco;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(MainActivity.this);
        setContentView(R.layout.activity_main);

        Injector.inject(MainActivity.this, R.id.tablayout,
                Injector.newTabModel("首页", R.drawable.drawable_res, HomePageFragment.class),
                Injector.newTabModel("促销", R.drawable.drawable_res, SpFragment.class),
                Injector.newTabModel("品牌", R.drawable.drawable_res, BrandFragment.class),
                Injector.newTabModel("正品", R.drawable.drawable_res, GenuineFragment.class),
                Injector.newTabModel("我的", R.drawable.drawable_res, MyFragment.class));

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}