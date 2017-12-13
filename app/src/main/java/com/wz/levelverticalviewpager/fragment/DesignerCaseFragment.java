package com.wz.levelverticalviewpager.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wz.levelverticalviewpager.R;
import com.wz.levelverticalviewpager.model.DesignersCase;
import com.wz.levelverticalviewpager.view.Html5Webview;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设计师详情第四个页面
 * Created by sj on 2017/9/13.
 */

public class DesignerCaseFragment extends Fragment {

    @BindView(R.id.iv_back_icon)
    ImageButton ivBackIcon;
    @BindView(R.id.right_one_icon)
    ImageButton rightOneIcon;
    @BindView(R.id.right_two_icon)
    ImageButton rightTwoIcon;
    @BindView(R.id.tv_title_middle)
    TextView tvTitleMiddle;
    @BindView(R.id.case_web)
    Html5Webview caseWeb;
    private OnePageFragment.backOnePageListener backOnePageListener;
    private DesignersCase designersCase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_designer_case_web, container, false);
        ButterKnife.bind(this, view);
        rightOneIcon.setImageResource(R.drawable.back_top);
        caseWeb.getSettings().setDomStorageEnabled(true);
        caseWeb.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        //允许混合内容 解决部分手机 加载不出https请求里面的http下的图片
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            caseWeb.getSettings().setMixedContentMode(caseWeb.getSettings().MIXED_CONTENT_ALWAYS_ALLOW);
        }
        return view;
    }

    public void setData(int curIndex, DesignersCase designersCase) {
        this.designersCase = designersCase;
        ivBackIcon.setImageResource(R.drawable.back_npc);
        rightOneIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (backOnePageListener != null) {
                    backOnePageListener.onbackOnePageChanged();
                }
            }
        });
        tvTitleMiddle.setText(designersCase.getCase_name());
        caseWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        caseWeb.loadUrl(designersCase.getCase_h5_url());
    }

    @OnClick({R.id.iv_back_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back_icon:
                getActivity().onBackPressed();
                break;
        }
    }

    public void setBackOnePageListener(OnePageFragment.backOnePageListener backOnePageListener) {
        this.backOnePageListener = backOnePageListener;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        synCookies();
    }

    private void synCookies() {
        /**
         * 清除webview中的cookie
         */
        CookieSyncManager.createInstance(getActivity());
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();//移除
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        try {
            if (caseWeb != null) {
                ((ViewGroup) caseWeb.getParent()).removeView(caseWeb);
                caseWeb.removeAllViews();
                caseWeb.stopLoading();
                // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
                caseWeb.getSettings().setJavaScriptEnabled(false);
                caseWeb.clearHistory();
                caseWeb.clearView();
                caseWeb.destroy();
                caseWeb = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
