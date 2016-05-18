package com.example.tyz1201.mytablayout.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tyz1201.mytablayout.R;
import com.example.tyz1201.mytablayout.commonmodel.GenuineWebDataModel;
import com.example.tyz1201.mytablayout.util.BrandInflateHandler;
import com.example.tyz1201.mytablayout.util.InterfaceSchdule;
import com.example.tyz1201.mytablayout.util.NetworkRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyz1201 on 2016/4/16.
 */
public class GenuineFragment extends Fragment {
    private List<GenuineWebDataModel> models = new ArrayList<>();
    TabLayout tabLayout;
    ViewPager viewPager;

    public static GenuineFragment newInstance() {

        Bundle args = new Bundle();
        GenuineFragment fragment = new GenuineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_genuine, null);
        TextView textView = (TextView) view.findViewById(R.id.toolbar_genuine);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager_genuine);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout_genuine);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        textView.setText("正品");

        InterfaceSchdule.createThread(getActivity(), new BrandInflateHandler() {
            @Override
            public void openNewThread() {
                String gson = NetworkRequest.getRequestData("http://api.zpzk100.com/client/tag_list");
                models = new Gson().fromJson(gson, new TypeToken<List<GenuineWebDataModel>>() {
                }.getType());
            }

            @Override
            public void initUIThread() {
                viewPager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
                    @Override
                    public Fragment getItem(int position) {
                        return GenuineContentFragment.newInstance(models.get(position).getId());
                    }

                    @Override
                    public int getCount() {
                        return models.size();
                    }

                    @Override
                    public CharSequence getPageTitle(int position) {
                        return models.get(position).getName();
                    }
                });
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return view;
    }


}




