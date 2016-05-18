package com.example.tyz1201.mytablayout.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.tyz1201.mytablayout.activity.BrandSingleGoodsActivity;
import com.example.tyz1201.mytablayout.activity.MainActivity;
import com.example.tyz1201.mytablayout.commonadapter.BrandAdapter;
import com.example.tyz1201.mytablayout.commonmodel.BrandDataModel;
import com.example.tyz1201.mytablayout.commonmodel.BrandModel;
import com.example.tyz1201.mytablayout.R;
import com.example.tyz1201.mytablayout.util.BrandInflateHandler;
import com.example.tyz1201.mytablayout.util.InterfaceSchdule;
import com.example.tyz1201.mytablayout.util.NetworkRequest;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyz1201 on 2016/4/16.
 */
public class BrandFragment extends Fragment {
    List<BrandModel> brandModels = new ArrayList<>();
    SwipeRefreshLayout srlBrand;
    GridView gridView;

    public static BrandFragment newInstance() {

        Bundle args = new Bundle();
        BrandFragment fragment = new BrandFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, null);
        srlBrand = (SwipeRefreshLayout) view.findViewById(R.id.srl_brand);
        gridView = (GridView) view.findViewById(R.id.gv_brand);
        initData();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), BrandSingleGoodsActivity.class);
                intent.putExtra("BrandName", brandModels.get(position).getName() + "/" + brandModels.get(position).getId());
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        srlBrand.post(new Runnable() {
            @Override
            public void run() {
                srlBrand.setRefreshing(true);
            }
        });
        srlBrand.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
                String gson = NetworkRequest.getRequestData("http://api.zpzk100.com/client/index_brand");
                BrandDataModel brandDataModel = new Gson().fromJson(gson, BrandDataModel.class);
                brandModels = brandDataModel.getRes();
            }

            @Override
            public void initUIThread() {
                BrandAdapter brandAdapter = new BrandAdapter(brandModels, getActivity());
                gridView.setAdapter(brandAdapter);
                srlBrand.setRefreshing(false);
            }
        });
    }
}
