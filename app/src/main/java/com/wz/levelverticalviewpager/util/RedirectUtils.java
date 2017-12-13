package com.wz.levelverticalviewpager.util;

import android.app.Activity;
import android.os.Bundle;

public class RedirectUtils extends BaseRedirectUtils {

    public static void startActivity(Activity activity, Class<?> cls,
                                     Bundle bundle) {
        // R.anim.zoomin, R.anim.zoomout
        startActivity(activity, cls, bundle, 0, 0);
    }

    public static void startActivity(Activity activity, Class<?> cls) {
        startActivity(activity, cls, null);
    }

    /**
     * 启动Activity
     *
     * @param activity         当前Activity
     * @param classPackageName 需要启动类对应的包名
     * @param classFullName    需要启动类全称
     * @param bundle
     */
    public static void startActivity(Activity activity, String classPackageName, String classFullName, Bundle bundle) {
        startActivity(activity, classPackageName, classFullName, bundle, 0, 0);
    }

    /**
     * 启动Activity
     *
     * @param activity         当前Activity
     * @param classPackageName 需要启动类对应的包名
     * @param classFullName    需要启动类全称
     */
    public static void startActivity(Activity activity, String classPackageName, String classFullName) {
        startActivity(activity, classPackageName, classFullName, null);
    }

    public static void finishActivity(Activity activity) {
        finishActivity(activity, 0, 0);
    }

    public static void startActiviryForResult(Activity activity, Class<?> cls, int requestcode, Bundle bundle) {
        startActivityForResult(activity, cls, requestcode, bundle, 0, 0);
    }

    public static void startActiviryForResult(Activity activity, Class<?> cls, int requestcode) {
        startActiviryForResult(activity, cls, requestcode, null);
    }

    public static void setResult(Activity activity, int resultcode) {
        setResult(activity, resultcode, null, 0, 0);
    }

    public static void setResult(Activity activity) {
        setResult(activity, Activity.RESULT_OK);
    }

    public static void setResult(Activity activity, Bundle bundle) {
        setResult(activity, Activity.RESULT_OK, bundle, 0, 0);
    }

}
