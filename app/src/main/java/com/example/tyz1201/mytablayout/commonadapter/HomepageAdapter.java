package com.example.tyz1201.mytablayout.commonadapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tyz1201.mytablayout.R;
import com.example.tyz1201.mytablayout.commonmodel.HotBrands;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyz1201 on 2016/5/4.
 */
public class HomepageAdapter extends BaseAdapter {
    private List<HotBrands> homepageMessages = new ArrayList<>();
    private Context context;

    public HomepageAdapter(List<HotBrands> homepageMessages, Context context) {
        this.homepageMessages = homepageMessages;
        this.context = context;
    }

    @Override
    public int getCount() {
        return homepageMessages.size();
    }

    @Override
    public Object getItem(int position) {
        return homepageMessages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.homepage_listview, parent, false);

        final SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.image_homepage);
        simpleDraweeView.setImageURI(Uri.parse(homepageMessages.get(position).getImg_url()));

        TextView textViewName = (TextView) view.findViewById(R.id.tv_homepage_name);
        textViewName.setText(homepageMessages.get(position).getName());
        textViewName.setTextColor(view.getResources().getColor(R.color.black));
        TextView textViewDiscount = (TextView) view.findViewById(R.id.tv_homepage_discount);
        textViewDiscount.setText(homepageMessages.get(position).getDiscount() + "折起");
        textViewDiscount.setTextColor(view.getResources().getColor(R.color.white));
        return view;
    }
}
