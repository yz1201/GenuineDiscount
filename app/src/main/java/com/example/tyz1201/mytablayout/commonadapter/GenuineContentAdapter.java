package com.example.tyz1201.mytablayout.commonadapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tyz1201.mytablayout.R;
import com.example.tyz1201.mytablayout.activity.GenuineDetailsActivity;
import com.example.tyz1201.mytablayout.commonmodel.GenuineContentWedDataModel;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyz1201 on 2016/5/11.
 */
public class GenuineContentAdapter extends BaseAdapter {
    Context context;
    List<GenuineContentWedDataModel> models = new ArrayList<>();

    public GenuineContentAdapter(Context context, List<GenuineContentWedDataModel> models) {
        this.context = context;
        this.models = models;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int position) {
        return models.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_genuine, null);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.sdv_genuine_pic);
        TextView textViewName = (TextView) view.findViewById(R.id.tv_genuine_name);
        TextView textViewPrice = (TextView) view.findViewById(R.id.tv_genuine_price);
        TextView textViewOldPrice = (TextView) view.findViewById(R.id.tv_genuine_old_price);

        simpleDraweeView.setImageURI(Uri.parse(models.get(position).getImage_url()));
        textViewName.setText(models.get(position).getName());
        textViewPrice.setText("¥" + models.get(position).getPrice());
        textViewPrice.setTextColor(view.getResources().getColor(R.color.text_color_red));
        double dis = Double.parseDouble(models.get(position).getOld_price()) - Double.parseDouble(models.get(position).getPrice());

        textViewOldPrice.setText("" + (int) dis);
        textViewOldPrice.setTextColor(view.getResources().getColor(R.color.text_color_red));

        CardView cardView = (CardView) view.findViewById(R.id.cv_gc_details);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GenuineDetailsActivity.class);
                intent.putExtra("messages", models.get(position));
                context.startActivity(intent);
            }
        });
        return view;
    }
}
