package com.example.tyz1201.mytablayout.commonadapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tyz1201.mytablayout.R;
import com.example.tyz1201.mytablayout.activity.BrandSingleGoodsActivity;
import com.example.tyz1201.mytablayout.activity.GoodsDetialActivity;
import com.example.tyz1201.mytablayout.commonmodel.SinglegoodsData;
import com.example.tyz1201.mytablayout.util.BrandInflateHandler;
import com.example.tyz1201.mytablayout.util.InterfaceSchdule;
import com.example.tyz1201.mytablayout.util.NetworkRequest;
import com.example.tyz1201.mytablayout.view.MyTextView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyz1201 on 2016/5/9.
 */
public class BrandSinglegoodsAdapter extends BaseAdapter {
    Context context;
    List<SinglegoodsData> messages = new ArrayList<>();

    public BrandSinglegoodsAdapter(Context context, List<SinglegoodsData> messages) {
        this.context = context;
        this.messages = messages;
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
        Log.d("ysjzytzzzzzzzzz", messages.size() + "");
        View view = LayoutInflater.from(context).inflate(R.layout.item_brand_singlegoods, parent, false);

        MyTextView myTextView = (MyTextView) view.findViewById(R.id.my_text_discount);
        SimpleDraweeView sdv = (SimpleDraweeView) view.findViewById(R.id.iv_brandsinglegoods_pic);
        TextView textViewName = (TextView) view.findViewById(R.id.tv_singlegoods_name);
        TextView textViewPrice = (TextView) view.findViewById(R.id.tv_price);
        TextView textViewOldPrice = (TextView) view.findViewById(R.id.tv_old_price);
        CardView cardView = (CardView) view.findViewById(R.id.cv_singoods);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GoodsDetialActivity.class);
                intent.putExtra("messages", messages.get(position).getId()+"/"+messages.get(position+1).getId());
                context.startActivity(intent);
            }
        });
        myTextView.setText(messages.get(position).getDiscount());
        myTextView.setTextColor(view.getResources().getColor(R.color.white));
        textViewName.setText(messages.get(position).getName());
        textViewPrice.setText(messages.get(position).getPrice());
        textViewPrice.setTextColor(0xfff6435c);
        textViewOldPrice.setText(messages.get(position).getOld_price());
        sdv.setImageURI(Uri.parse(messages.get(position).getImage_url()));
        return view;
    }


}
