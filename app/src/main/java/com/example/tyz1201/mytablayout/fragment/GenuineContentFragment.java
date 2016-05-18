package com.example.tyz1201.mytablayout.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.tyz1201.mytablayout.R;
import com.example.tyz1201.mytablayout.commonadapter.GenuineContentAdapter;
import com.example.tyz1201.mytablayout.commonmodel.GenuineContentWedDataModel;
import com.example.tyz1201.mytablayout.util.BrandInflateHandler;
import com.example.tyz1201.mytablayout.util.InterfaceSchdule;
import com.example.tyz1201.mytablayout.util.NetworkRequest;
import com.example.tyz1201.mytablayout.util.RefreshLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyz1201 on 2016/5/11.
 */
public class GenuineContentFragment extends Fragment {
    String id;
    GenuineContentAdapter genuineContentAdapter;
    List<GenuineContentWedDataModel> models = new ArrayList<>();
    RefreshLayout refreshLayout;
    GridView gridView;

    public static GenuineContentFragment newInstance(String id) {

        Bundle args = new Bundle();
        args.putSerializable("messages", id);
        GenuineContentFragment fragment = new GenuineContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_genuine_content, null);
        gridView = (GridView) view.findViewById(R.id.gridview_genuine_contentl);
        refreshLayout = (RefreshLayout) view.findViewById(R.id.genuine_rfl);
        Bundle bundle = getArguments();
        id = (String) bundle.getSerializable("messages");
        genuineContentAdapter = new GenuineContentAdapter(getActivity(), models);
        gridView.setAdapter(genuineContentAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

/**
 *  刷新界面显示第一页内容
 */
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                initFirstData(id);
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initFirstData(id);
            }
        });
        /**
         *   加载下一页
         */

        refreshLayout.setChildView(gridView);
        refreshLayout.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                int page = 1;
                page++;
                initData(id, page);
                refreshLayout.setLoading(false);
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    private void initFirstData(final String id) {
        InterfaceSchdule.createThread(getActivity(), new BrandInflateHandler() {
            @Override
            public void openNewThread() {
                String gson = NetworkRequest.getRequestData("http://api.zpzk100.com/client/list?tag_id=" + id + "&page=1");
                List<GenuineContentWedDataModel> datas = new Gson().fromJson(gson, new TypeToken<List<GenuineContentWedDataModel>>() {
                }.getType());
                models.removeAll(models);
                models.addAll(datas);
            }

            @Override
            public void initUIThread() {
                genuineContentAdapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }
        });
    }

    private void initData(final String id, final int page) {
        Log.d("ysjzytzzzzzzzz", page + "");
        InterfaceSchdule.createThread(getActivity(), new BrandInflateHandler() {
            @Override
            public void openNewThread() {
                String gson = NetworkRequest.getRequestData("http://api.zpzk100.com/client/list?tag_id=" + id + "&page=" + page);
                List<GenuineContentWedDataModel> datas = new Gson().fromJson(gson, new TypeToken<List<GenuineContentWedDataModel>>() {
                }.getType());
                models.addAll(datas);
            }

            @Override
            public void initUIThread() {
                genuineContentAdapter.notifyDataSetChanged();
//                refreshLayout.setLoading(false);
            }
        });
    }

}
