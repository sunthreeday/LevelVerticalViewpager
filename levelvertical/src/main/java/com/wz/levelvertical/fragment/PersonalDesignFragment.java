package com.wz.levelvertical.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wz.levelvertical.R;
import com.wz.levelvertical.model.AllDesignersEntity;
import com.wz.levelvertical.util.SharedPrefUtils;

import java.util.ArrayList;
import java.util.List;

public class PersonalDesignFragment extends Fragment {

    private ImageButton ivBackIcon;
    private ImageButton rightOneIcon;
    private ImageView designerImg;
    private List<AllDesignersEntity.DesignerListBean> mdesignlist = new ArrayList<>();
    private OnePageFragment.backOnePageListener backOnePageListener;
    private int index = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_personal_designer, container, false);
        designerImg = (ImageView) view.findViewById(R.id.designer_img);
        ivBackIcon = (ImageButton) view.findViewById(R.id.iv_back_icon);
        ivBackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        rightOneIcon = (ImageButton) view.findViewById(R.id.right_one_icon);
        rightOneIcon.setImageResource(R.drawable.back_top_white);
        ImageView whiteIndicator = (ImageView) view.findViewById(R.id.white_indicator);
        whiteIndicator.setImageResource(R.drawable.up_white_indicator);
        AnimationDrawable animationDrawable = (AnimationDrawable) whiteIndicator.getDrawable();
        animationDrawable.start();

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

}
