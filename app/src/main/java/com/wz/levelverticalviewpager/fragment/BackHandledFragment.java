package com.wz.levelverticalviewpager.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

/**
 * Created by sj on 2017/8/2.
 */

public abstract class BackHandledFragment extends Fragment implements FragmentBackHandler{
    public BackHandledFragment() {
    }

    @Override
    public final boolean onBackPressed() {
        return interceptBackPressed()
                || (getBackHandleViewPager() == null
                ? BackHandlerHelper.handleBackPress(this)
                : BackHandlerHelper.handleBackPress(getBackHandleViewPager()));
    }

    public boolean interceptBackPressed() {
        return false;
    }

    /**
     * 2.1 版本已经不在需要单独对ViewPager处理
     *
     * @deprecated
     */
    @Deprecated
    public ViewPager getBackHandleViewPager() {
        return null;
    }
}
