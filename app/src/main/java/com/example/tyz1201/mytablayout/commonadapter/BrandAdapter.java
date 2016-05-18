package com.example.tyz1201.mytablayout.commonadapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tyz1201.mytablayout.R;
import com.example.tyz1201.mytablayout.activity.BrandSingleGoodsActivity;
import com.example.tyz1201.mytablayout.activity.MainActivity;
import com.example.tyz1201.mytablayout.commonmodel.BrandModel;
import com.example.tyz1201.mytablayout.util.InterfaceSchdule;
import com.example.tyz1201.mytablayout.util.BrandInflateHandler;
import com.example.tyz1201.mytablayout.util.NetworkRequest;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyz1201 on 2016/4/20.
 */
public class BrandAdapter extends BaseAdapter {
    private List<BrandModel> messages = new ArrayList<>();
    private Context context;


    public BrandAdapter(List<BrandModel> messages, Context context) {
        this.messages = messages;
        this.context = context;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (null == convertView) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_brand_index, null, true);
            viewHolder.sdv = (SimpleDraweeView) convertView.findViewById(R.id.sdv_brand_pic);
            viewHolder.textViewName = (TextView) convertView.findViewById(R.id.tv_brand_name);
            viewHolder.textViewDiscount = (TextView) convertView.findViewById(R.id.tv_brand_discount);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textViewDiscount.setText(messages.get(position).getDiscount());
        viewHolder.textViewName.setText(messages.get(position).getName());
        viewHolder.sdv.setImageURI(Uri.parse(messages.get(position).getPicUrl()));
        return convertView;
    }

    private final class ViewHolder {
        SimpleDraweeView sdv;
        TextView textViewName;
        TextView textViewDiscount;
    }
}
