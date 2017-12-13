package com.wz.levelverticalviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wz.levelverticalviewpager.fragment.OnePageFragment;
import com.wz.levelverticalviewpager.model.AllDesignersEntity;
import com.wz.levelverticalviewpager.util.JsonUtils;
import com.wz.levelverticalviewpager.util.ObjectJudge;
import com.wz.levelverticalviewpager.util.RedirectUtils;
import com.wz.levelverticalviewpager.util.ScreenUtil;
import com.wz.levelverticalviewpager.util.SharedPrefUtils;
import com.wz.levelverticalviewpager.view.DesignersViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wz.levelverticalviewpager.util.SimulateNetAPI.getOriginalFundData;

/**
 * 设计师详情页面
 * Created by wz on 2017/7/18.
 */

public class DesignerDeatilListActivity extends FragmentActivity {

    private List<Fragment> fragmentContainter;
    private DesignersViewPager ultraViewPager;
    private int curIndex = 0;
    @BindView(R.id.iv_back_icon)
    ImageButton ivBackIcon;
    private RelativeLayout rlhead;
    private int pos;

    public interface changeVerticalPageListener {
        void onVerticalPageChanged(int position);
    }

    public interface scrollPagerListener {
        void onScrollPagerListener(int Pixels);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designer_deatil);
        ButterKnife.bind(this);
        ivBackIcon.setImageResource(R.drawable.back_npc);
        ivBackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RedirectUtils.finishActivity(DesignerDeatilListActivity.this);
            }
        });
        rlhead = (RelativeLayout) findViewById(R.id.rl_head);
        initView();
    }

    private void initView() {
        ultraViewPager = (DesignersViewPager) findViewById(R.id.ultra_viewpager);
        initVerticalPagers();
    }

    private void initVerticalPagers() {
        fragmentContainter = new ArrayList<>();
        String response = getOriginalFundData(getBaseContext(),"designlist.json");
        AllDesignersEntity allEntity = JsonUtils.parseT(response, AllDesignersEntity.class);
        List<AllDesignersEntity.DesignerListBean> mdesignlist = allEntity.getData().getDesigner_list();

        if (!ObjectJudge.isNullOrEmpty(mdesignlist)) {
            SharedPrefUtils.save(getBaseContext(), "designers", "designer", mdesignlist);
//        每个设计师创建一个竖滑的flagment
            for (int i = 0; i < mdesignlist.size(); i++) {
                OnePageFragment f = new OnePageFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("index", i);
                bundle.putString("designer_uid", mdesignlist.get(i).getDesigner_uid());
                bundle.putString("imgs", mdesignlist.get(i).getFull_body_shot_url());
                f.setArguments(bundle);
                //竖直滑动切换时响应的事件
                setPagerMargin();
                f.setScrollListener(new scrollPagerListener() {
                    @Override
                    public void onScrollPagerListener(int Pixels) {
                        int height = ScreenUtil.px2dip(DesignerDeatilListActivity.this, Pixels);
                        if (height > 10) {
                            rlhead.setVisibility(View.GONE);
                        }
                        if (pos == 0) {
                            if (height > 10) {
                                rlhead.setVisibility(View.GONE);
                            } else {
                                rlhead.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
                f.setChangeVerticalPageListener(new changeVerticalPageListener() {
                    @Override
                    public void onVerticalPageChanged(int position) {
                        pos = position;
                        ultraViewPager.setScanScroll((position == 0));//只有第一个页面能左右滑动
                        if (position == 0) {
                            rlhead.setVisibility(View.VISIBLE);
                            setPagerMargin();
                        } else {
                            rlhead.setVisibility(View.GONE);
                            resetPagerMargin();
                        }
                    }
                });
                fragmentContainter.add(f);
            }
        }
        //横向滑动的 adapter
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return fragmentContainter.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragmentContainter.get(position);
            }

        };
        ultraViewPager.setOffscreenPageLimit(1);
        ultraViewPager.setCurrentItem(0);
        ultraViewPager.setAdapter(adapter);
        ultraViewPager.setOffscreenPageLimit(mdesignlist.size());
        ultraViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
    }

    private void setPagerMargin() {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ultraViewPager.getLayoutParams());
        lp.setMargins(ScreenUtil.dip2px(this, 16), 0, ScreenUtil.dip2px(this, 16), 0);
        ultraViewPager.setLayoutParams(lp);
        ultraViewPager.setClipChildren(false);
    }

    private void resetPagerMargin() {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ultraViewPager.getLayoutParams());
        lp.setMargins(0, 0, 0, 0);
        ultraViewPager.setLayoutParams(lp);
        ultraViewPager.setClipChildren(true);
    }
}

