package com.wz.levelvertical.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wz.levelvertical.R;
import com.wz.levelvertical.model.DesignersCase;
import com.wz.levelvertical.model.DesignersCaseEntity;
import com.wz.levelvertical.util.JsonUtils;

import java.util.ArrayList;
import java.util.List;

import static com.wz.levelvertical.util.SimulateNetAPI.getOriginalFundData;

public class DeatilsFragment extends Fragment {

    private TextView tvTitleMiddle;
    private ViewPager mViewPager;
    private List<DesignersCase> mcaseList = new ArrayList<>();
    private int curIndex;
    private OnePageFragment.backOnePageListener backOnePageListener;
    private OnePageFragment.clicktoPageListener clicktoPageListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_designer_case, container, false);
        ImageButton ivBackIcon = (ImageButton) view.findViewById(R.id.iv_back_icon);
        ivBackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        ImageButton rightOneIcon = (ImageButton) view.findViewById(R.id.right_one_icon);
        rightOneIcon.setImageResource(R.drawable.back_top);
        tvTitleMiddle = (TextView) view.findViewById(R.id.tv_title_middle);
        tvTitleMiddle.setTextColor(R.color.black_color);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager_case);
        ImageView blackIndicator = (ImageView) view.findViewById(R.id.black_indicator);
        blackIndicator.setImageResource(R.drawable.up_black_indicator);
        // 1. 设置动画
        AnimationDrawable animationDrawable = (AnimationDrawable) blackIndicator.getDrawable();
        // 2. 获取动画对象
        animationDrawable.start();
        if (getArguments() != null) {
            loadData();
            ivBackIcon.setImageResource(R.drawable.back_npc);
            rightOneIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (backOnePageListener != null) {
                        backOnePageListener.onbackOnePageChanged();
                    }
                }
            });
        }
        return view;
    }

    private void loadData() {
        String response = getOriginalFundData(getActivity(), "caselist.json");
        DesignersCaseEntity caseEntity = JsonUtils.parseT(response, DesignersCaseEntity.class);
        mcaseList = caseEntity.getData().getList();
        initView();
    }

    private void initView() {
        CardPagerAdapter mPageAdapter = new CardPagerAdapter(mcaseList);
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setCurrentItem(0);
        mViewPager.setPageMargin(20);
        mViewPager.setAdapter(mPageAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                curIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mPageAdapter.notifyDataSetChanged();
    }

    public DesignersCase getCurIndexCase() {
        return mcaseList.get(curIndex);
    }

    private class CardPagerAdapter extends PagerAdapter {

        private List<DesignersCase> mData;

        CardPagerAdapter(List<DesignersCase> mData) {
            this.mData = mData;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(container.getContext())
                    .inflate(R.layout.item_viewpager_case_indesigner, container, false);
            container.addView(view);
            bind(mData.get(position), view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        private void bind(DesignersCase item, View view) {
            ImageView ivcase = (ImageView) view.findViewById(R.id.iv_case);
            ImageView ivlevel = (ImageView) view.findViewById(R.id.design_level);
            TextView tvcase = (TextView) view.findViewById(R.id.case_name);
            TextView tvdesigner = (TextView) view.findViewById(R.id.design_name);
            ivcase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (clicktoPageListener != null) {
                        clicktoPageListener.onclickToPageListener();
                    }
                }
            });
            Glide.with(getContext())
                    .load(item.getCase_image_url())
                    .placeholder(R.drawable.placeholder)
                    .into(ivcase);
            Glide.with(getContext())
                    .load(item.getDesigner_level())
                    .into(ivlevel);
            tvcase.setText(item.getCase_name());
            TextView tvtpe = (TextView) view.findViewById(R.id.type_name);
            tvtpe.setText(item.getHouse_area());
            tvdesigner.setText(getString(R.string.customer_satisfaction));
            tvTitleMiddle.setText(item.getDesigner_name() + getString(R.string.whose_case));
        }
    }

    public void setBackOnePageListener(OnePageFragment.backOnePageListener backOnePageListener) {
        this.backOnePageListener = backOnePageListener;
    }

    public void setClicktoPageListener(OnePageFragment.clicktoPageListener clicktoPageListener) {
        this.clicktoPageListener = clicktoPageListener;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mViewPager.setAdapter(null);
        ((ViewGroup) mViewPager.getParent()).removeView(mViewPager);
        mcaseList.clear();
    }
}

