package com.wz.levelvertical.fragment;

//承载VerticalViewPager的 fragment,一个设计师一个

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wz.levelvertical.DesignerDeatilListActivity;
import com.wz.levelvertical.R;
import com.wz.levelvertical.model.DesignersCase;
import com.wz.levelvertical.view.VerticalPagerAdapter;
import com.wz.levelvertical.view.VerticalViewPager;

import java.util.ArrayList;
import java.util.List;

public class OnePageFragment extends Fragment {

    private List<DesignersCase> mcaseList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private String designer_uid;
    private int curIndex;
    private DesignerDeatilListActivity.changeVerticalPageListener changeListener;
    private DesignerDeatilListActivity.scrollPagerListener scrollListener;
    private VerticalViewPager checkOne;

    public interface backOnePageListener {
        void onbackOnePageChanged();
    }

    public interface clicktoPageListener {
        void onclickToPageListener();
    }

    private List<Fragment> listFragments;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.check_one, container, false);
        checkOne = (VerticalViewPager) view.findViewById(R.id.check_one);
        checkOne.setOffscreenPageLimit(1);

        if (getArguments() != null) {
            curIndex = getArguments().getInt("index");
            designer_uid = getArguments().getString("designer_uid");
            listFragments = new ArrayList<>();
            DesignersFragment fragments = new DesignersFragment();
            Bundle bundle = new Bundle();
            bundle.putString("imgs", getArguments().getString("imgs"));
            fragments.setArguments(bundle);
            listFragments.add(fragments);
        }

//		添加第二个视频页面
        PersonalDesignFragment personalFragment = new PersonalDesignFragment();
        personalFragment.setBackOnePageListener(new backOnePageListener() {
            @Override
            public void onbackOnePageChanged() {
                checkOne.setCurrentItem(0);
            }
        });
        listFragments.add(personalFragment);

//      添加第三个案例封面切换页面
        DeatilsFragment t = new DeatilsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("designer_uid", designer_uid);
        t.setArguments(bundle);
        t.setBackOnePageListener(new backOnePageListener() {
            @Override
            public void onbackOnePageChanged() {
                checkOne.setCurrentItem(0);
            }
        });
        t.setClicktoPageListener(new clicktoPageListener() {
            @Override
            public void onclickToPageListener() {
                checkOne.setCurrentItem(3);
            }
        });

        listFragments.add(t);

//        添加第四个案例详情webview页面
        DesignerCaseFragment caseFragment = new DesignerCaseFragment();
        Bundle tbundle = new Bundle();
        tbundle.putString("designer_name", getArguments().getString("name"));
        caseFragment.setArguments(tbundle);

        caseFragment.setBackOnePageListener(new backOnePageListener() {
            @Override
            public void onbackOnePageChanged() {
                checkOne.setCurrentItem(2);
            }
        });
        listFragments.add(caseFragment);
        VerticalPagerAdapter fragmentAdapter = new VerticalPagerAdapter(
                getChildFragmentManager(), listFragments);
        checkOne.setOnPageChangeListener(new VerticalViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (scrollListener != null) {
                    scrollListener.onScrollPagerListener(positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 1) {
                    startSecondVideo();
                } else if (position == 3) {
                    loadCaseUrl();
                }
                if (changeListener != null) {
                    changeListener.onVerticalPageChanged(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        checkOne.setAdapter(fragmentAdapter);
        return view;
    }

    private void startSecondVideo() {
        PersonalDesignFragment d = (PersonalDesignFragment) listFragments.get(1);
        d.startPlayer(curIndex);
    }

    private void loadCaseUrl() {
        DeatilsFragment t = (DeatilsFragment) listFragments.get(2);
        DesignerCaseFragment d = (DesignerCaseFragment) listFragments.get(3);
        d.setData(curIndex, t.getCurIndexCase());
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        checkOne.setAdapter(null);
        ((ViewGroup) checkOne.getParent()).removeView(checkOne);
        listFragments.clear();

    }

    public void setScrollListener(DesignerDeatilListActivity.scrollPagerListener scrollListener) {
        this.scrollListener = scrollListener;
    }

    public void setChangeVerticalPageListener(DesignerDeatilListActivity.changeVerticalPageListener changeListener) {
        this.changeListener = changeListener;
    }

}
