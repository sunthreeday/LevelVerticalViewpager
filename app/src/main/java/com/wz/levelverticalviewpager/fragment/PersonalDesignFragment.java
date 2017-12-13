package com.wz.levelverticalviewpager.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wz.levelverticalviewpager.R;
import com.wz.levelverticalviewpager.model.AllDesignersEntity;
import com.wz.levelverticalviewpager.util.SharedPrefUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 设计师详情第二个页面
 * Created by wz on 2017/7/18.
 */

public class PersonalDesignFragment extends Fragment {

    @BindView(R.id.iv_back_icon)
    ImageButton ivBackIcon;
    @BindView(R.id.right_one_icon)
    ImageButton rightOneIcon;
    @BindView(R.id.right_two_icon)
    ImageButton rightTwoIcon;
    @BindView(R.id.white_indicator)
    ImageView whiteIndicator;
    @BindView(R.id.designer_img)
    ImageView designerImg;
    private List<AllDesignersEntity.DesignerListBean> mdesignlist = new ArrayList<>();
    private OnePageFragment.backOnePageListener backOnePageListener;
    private int index = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_personal_designer, container, false);
        ButterKnife.bind(this, view);
        rightOneIcon.setImageResource(R.drawable.back_top_white);
        whiteIndicator.setImageResource(R.drawable.up_white_indicator);
        AnimationDrawable animationDrawable = (AnimationDrawable) whiteIndicator.getDrawable();
        animationDrawable.start();
        ivBackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        mdesignlist = (List<AllDesignersEntity.DesignerListBean>) SharedPrefUtils.get(getContext(), "designers", "designer");
        return view;
    }

    public void startPlayer(final int curIndex) {
        index = curIndex;
        ivBackIcon.setImageResource(R.drawable.back_npc_white);
        rightOneIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (backOnePageListener != null) {
                    backOnePageListener.onbackOnePageChanged();
                }
            }
        });
        Glide.with(getActivity())
                .load(mdesignlist.get(curIndex).getPersonality_photo_url())
                .placeholder(R.drawable.placeholder)
                .into(designerImg);
    }

    public void setBackOnePageListener(OnePageFragment.backOnePageListener backOnePageListener) {
        this.backOnePageListener = backOnePageListener;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        designerImg = null;
    }
}
