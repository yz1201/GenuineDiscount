package com.example.tyz1201.mytablayout.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;


import com.example.tyz1201.mytablayout.R;
import com.example.tyz1201.mytablayout.activity.LandingActivity;

/**
 * Created by tyz1201 on 2016/4/16.
 */
public class MyFragment extends Fragment {
    public static MyFragment newInstance() {

        Bundle args = new Bundle();

        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_landing, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_go_landing);
        TextView textView = (TextView) view.findViewById(R.id.tv_go_landing);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll_landing);

        imageView.setImageResource(R.mipmap.fragment_my_landing);
        textView.setText("去登陆");

        TextView textViewToolbar = (TextView) view.findViewById(R.id.tv_go_landing_toolbar);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LandingActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
