package com.example.tyz1201.mytablayout.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.tyz1201.mytablayout.R;
import com.example.tyz1201.mytablayout.commonadapter.GdRelatedFragmentAdapter;
import com.example.tyz1201.mytablayout.commonmodel.RelatedGoods;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tyz1201 on 2016/5/18.
 */
public class BrandSinGoodsDetailsFragment extends Fragment {
    public static BrandSinGoodsDetailsFragment newInstance(List<RelatedGoods> lists) {

        Bundle args = new Bundle();
        args.putSerializable("messages", (Serializable) lists);
        BrandSinGoodsDetailsFragment fragment = new BrandSinGoodsDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        List<RelatedGoods> goods = (List<RelatedGoods>) bundle.getSerializable("messages");
        View view = inflater.inflate(R.layout.fragment_brand_singoods_details, null);
        GridView gridview = (GridView) view.findViewById(R.id.gridview_gd_related_fragment);
        GdRelatedFragmentAdapter gdRelatedFragmentAdapter = new GdRelatedFragmentAdapter(getActivity(),goods);
        gridview.setAdapter(gdRelatedFragmentAdapter);

        return view;
    }
}
