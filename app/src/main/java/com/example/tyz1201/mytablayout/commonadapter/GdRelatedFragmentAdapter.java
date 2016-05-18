package com.example.tyz1201.mytablayout.commonadapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tyz1201.mytablayout.R;
import com.example.tyz1201.mytablayout.commonmodel.RelatedGoods;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyz1201 on 2016/5/18.
 */
public class GdRelatedFragmentAdapter extends BaseAdapter {
    private Context context;
    private List<RelatedGoods> list = new ArrayList<>();

    public GdRelatedFragmentAdapter(Context context, List<RelatedGoods> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_gd_fragment, null);
        SimpleDraweeView sdv = (SimpleDraweeView) view.findViewById(R.id.sdv_gd_fragment);
        TextView textViewName = (TextView) view.findViewById(R.id.tv_gd_fragment_name);
        TextView textViewPrice = (TextView) view.findViewById(R.id.tv_gd_fragment_price);
        TextView textViewDiscount = (TextView) view.findViewById(R.id.tv_gd_fragment_discount);

        sdv.setImageURI(Uri.parse(list.get(position).getImage_url()));
        textViewName.setText(list.get(position).getName());
        textViewPrice.setText(list.get(position).getPrice());
        textViewDiscount.setText(list.get(position).getDiscount());

        textViewName.setTextColor(view.getResources().getColor(R.color.text_color_black));
        textViewPrice.setTextColor(view.getResources().getColor(R.color.text_color_red));
        textViewDiscount.setTextColor(view.getResources().getColor(R.color.white));
        Log.d("ysjzytsssasadad", list.get(position).getName());
        return view;
    }
}
