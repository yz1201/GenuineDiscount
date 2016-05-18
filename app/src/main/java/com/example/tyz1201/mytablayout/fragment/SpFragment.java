package com.example.tyz1201.mytablayout.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tyz1201.mytablayout.R;

/**
 * Created by tyz1201 on 2016/4/16.
 */
public class SpFragment extends Fragment {
    public static SpFragment newInstance() {

        Bundle args = new Bundle();
        SpFragment fragment = new SpFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, null);
        return view;
    }
}
