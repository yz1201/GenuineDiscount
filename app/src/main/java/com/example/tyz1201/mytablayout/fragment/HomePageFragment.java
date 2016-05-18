package com.example.tyz1201.mytablayout.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.tyz1201.mytablayout.R;
import com.example.tyz1201.mytablayout.commonadapter.HomepageAdapter;
import com.example.tyz1201.mytablayout.commonmodel.ActivitiesModel;
import com.example.tyz1201.mytablayout.commonmodel.HotBrands;
import com.example.tyz1201.mytablayout.commonmodel.MyHomepageWebData;
import com.example.tyz1201.mytablayout.util.BrandInflateHandler;
import com.example.tyz1201.mytablayout.util.InterfaceSchdule;
import com.example.tyz1201.mytablayout.util.NetworkRequest;
import com.example.tyz1201.mytablayout.util.RefreshLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by tyz1201 on 2016/4/16.
 */
public class HomePageFragment extends Fragment {
    List<HotBrands> messages = new ArrayList<>();
    List<ActivitiesModel> models = new ArrayList<>();
    ListView listView;
    SwipeRefreshLayout swipeRefreshLayout;
    HomepageAdapter homepageAdapter;
    ViewPager viewPager;
    Timer timer;

    public static HomePageFragment newInstance() {
        Bundle args = new Bundle();
        HomePageFragment fragment = new HomePageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_homepage, null);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_homepage);
        listView = (ListView) view.findViewById(R.id.lv_homepage);
        homepageAdapter = new HomepageAdapter(messages, getActivity());
/**
 *  轮播图，ListView前,设置在headerView中
 */
        int width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        viewPager = new ViewPager(getActivity());
        viewPager.setLayoutParams(new AbsListView.LayoutParams(width, width / 3));
        listView.addHeaderView(viewPager);
        initData();

        listView.setAdapter(homepageAdapter);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                initData();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    private void initData() {
        InterfaceSchdule.createThread(getActivity(), new BrandInflateHandler() {
            @Override
            public void openNewThread() {
                String gson = NetworkRequest.getRequestData("http://api.zpzk100.com/client/brand_theme_index");
                MyHomepageWebData myHomepageWebData = new Gson().fromJson(gson, MyHomepageWebData.class);
                List<HotBrands> datas = myHomepageWebData.getHot_brands();
                models = myHomepageWebData.getActivities();
                messages.addAll(datas);
            }

            @Override
            public void initUIThread() {
                homepageAdapter.notifyDataSetChanged();

                timer = new Timer();
                timer.schedule(new TimerTask() {
                                   @Override
                                   public void run() {
                                       getActivity().runOnUiThread(new Runnable() {
                                           @Override
                                           public void run() {
                                               viewPager.setAdapter(new PagerAdapter() {
                                                   @Override
                                                   public int getCount() {
                                                       Log.d("LTAG", messages.size() + "");
                                                       return models.size();
                                                   }

                                                   @Override
                                                   public boolean isViewFromObject(View view, Object object) {
                                                       return view == object;
                                                   }

                                                   @Override
                                                   public void destroyItem(ViewGroup container, int position, Object object) {

                                                   }

                                                   @Override
                                                   public Object instantiateItem(ViewGroup container, int position) {
                                                       SimpleDraweeView sdv = (SimpleDraweeView) LayoutInflater.from(getActivity()).inflate(R.layout.hp_rolledpic, viewPager, false).findViewById(R.id.hp_imageview);
                                                       sdv.setImageURI(Uri.parse(models.get(position).getImage_url()));
                                                       container.addView(sdv);
                                                       return sdv;
                                                   }
                                               });
                                           }
                                       });

                                   }
                               },
                        1000,1000);
                swipeRefreshLayout.setRefreshing(false);
            }

            private void initPageAdapter() {

            }
        });
    }

    public void closeTimer() {
        if (getFragmentManager().getFragments().get(0).isHidden()) {
            timer.cancel();
        } else {
        }
    }
}
