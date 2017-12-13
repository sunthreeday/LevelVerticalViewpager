package com.wz.levelverticalviewpager.fragment;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wz.levelverticalviewpager.R;

/**
 * 设计师详情第一屏中的子页面
 * Created by wz on 2017/6/24.
 */

public class DesignersFragment extends BackHandledFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_designer, container, false);
        ImageView videoImg = (ImageView) view.findViewById(R.id.designer_img);
        ImageView blackIndic = (ImageView) view.findViewById(R.id.black_indicator);
        blackIndic.setImageResource(R.drawable.up_black_indicator);
        AnimationDrawable animationDrawable = (AnimationDrawable) blackIndic.getDrawable();
        animationDrawable.start();
        if (getArguments() != null) {
            String img = getArguments().getString("imgs");
            Glide.with(getActivity())
                    .load(img)
                    .placeholder(R.drawable.placeholder)
                    .into(videoImg);
        }
        return view;
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
    }

}
