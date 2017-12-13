/**
 * @Title: BaseRedirectUtils.java
 * @Description:
 * @author: lijinghuan
 * @data: 2015年5月4日 下午8:31:13
 */
package com.wz.levelverticalviewpager.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.text.TextUtils;

public class BaseRedirectUtils {

    public static void startService(Context context, Class<?> cls, String action, Bundle bundle) {
        Intent _intent = new Intent();
        if (!TextUtils.isEmpty(action)) {
            _intent.setAction(action);
        }
        _intent.setClass(context, cls);
        if (bundle != null) {
            _intent.putExtras(bundle);
        }
        context.startService(_intent);
    }

    public static void startService(Context context, Class<?> cls, String action) {
        startService(context, cls, action, null);
    }

    public static void startService(Context context, Class<?> cls, Bundle bundle) {
        startService(context, cls, "", bundle);
    }

    public static void startService(Context context, Class<?> cls) {
        startService(context, cls, "", null);
    }

    public static void stopService(Context context, Class<?> cls) {
        Intent _intent = new Intent();
        _intent.setClass(context, cls);
        context.stopService(_intent);
    }

    public static void bindService(Context context, ServiceConnection conn,
                                   String action) {
        Intent _intent = new Intent(action);
        context.bindService(_intent, conn, Context.BIND_AUTO_CREATE);
    }

    public static void unbindService(Context context, ServiceConnection conn) {
        context.unbindService(conn);
    }

    public static void startActivity(Activity activity, Class<?> cls,
                                     Bundle bundle, int enterAnim, int exitAnim) {
        Intent _intent = new Intent();
        _intent.setClass(activity, cls);
        if (bundle != null) {
            _intent.putExtras(bundle);
        }
        activity.startActivity(_intent);
        activity.overridePendingTransition(enterAnim != 0 ? enterAnim : 0, exitAnim != 0 ? exitAnim : 0);
    }

    /**
     * 启动Activity
     *
     * @param activity         当前Activity
     * @param classPackageName 需要启动类对应的包名
     * @param classFullName    需要启动类全称
     * @param bundle
     * @param enterAnim        进入动画
     * @param exitAnim         退出动画
     */
    public static void startActivity(Activity activity, String classPackageName, String classFullName, Bundle bundle, int enterAnim, int exitAnim) {
        if (activity == null || TextUtils.isEmpty(classPackageName) || TextUtils.isEmpty(classFullName)) {
            return;
        }
        Intent _intent = new Intent();
        _intent.setClassName(classPackageName, classFullName);
        if (bundle != null) {
            _intent.putExtras(bundle);
        }
        activity.startActivity(_intent);
        activity.overridePendingTransition(enterAnim != 0 ? enterAnim : 0, exitAnim != 0 ? exitAnim : 0);
    }

    public static void finishActivity(Activity activity, int enterAnim, int exitAnim) {
        activity.finish();
        activity.overridePendingTransition(enterAnim != 0 ? enterAnim : 0, exitAnim != 0 ? exitAnim : 0);
    }

    public static void startActivityForResult(Activity activity, Class<?> cls, int requestcode,
                                              Bundle bundle, int enterAnim, int exitAnim) {
        Intent _intent = new Intent();
        _intent.setClass(activity, cls);
        if (bundle != null) {
            _intent.putExtras(bundle);
        }
        activity.startActivityForResult(_intent, requestcode);
        activity.overridePendingTransition(enterAnim != 0 ? enterAnim : 0, exitAnim != 0 ? exitAnim : 0);
    }

    public static void setResult(Activity activity, int resultcode,
                                 Bundle bundle, int enterAnim, int exitAnim) {
        Intent _intent = new Intent();
        if (bundle != null) {
            _intent.putExtras(bundle);
        }
        activity.setResult(resultcode, _intent);
        activity.overridePendingTransition(enterAnim != 0 ? enterAnim : 0, exitAnim != 0 ? exitAnim : 0);
    }


    public static void sendBroadcast(Context context, String action,
                                     String permission, Intent intent) {
        if (intent == null) {
            intent = new Intent();
        }
        intent.setAction(action);

        if (TextUtils.isEmpty(permission)) {
            context.sendBroadcast(intent);
        } else {
            context.sendBroadcast(intent, permission);
        }
    }

//    public static void sendBroadcast(Context context, String action,
//                                     String permission, Bundle bundle) {
//        Intent intent = new Intent(action);
//        if (bundle != null) {
//            intent.putExtras(bundle);
//        }
//        if (TextUtils.isEmpty(permission)) {
//            context.sendBroadcast(intent);
//        } else {
//            context.sendBroadcast(intent, permission);
//        }
//    }

    public static void sendBroadcast(Context context, String action, Intent intent) {
        sendBroadcast(context, action, "", intent);
    }

//    public static <T> void sendBroadcast(Context context, String action,
//                                         T... params) {
//        if (ObjectJudge.isNullOrEmpty(params)) {
//            return;
//        }
//        Intent intent = new Intent();
//        String jsondata = JsonUtils.toStr(params);
//        intent.putExtra("RECEIVE_DATA", jsondata);
//        sendBroadcast(context, action, "", intent);
//    }
//
//    public static <T> void sendBroadcast(Context context, T... params) {
//        // ReceiverActions.TNT_RECEIVE_ACTION("1825261495")
//        sendBroadcast(context, "1825261495", params);
//    }

}
