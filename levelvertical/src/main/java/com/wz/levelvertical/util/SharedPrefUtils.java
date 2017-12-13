/**
 * @Title: SharedPrefUtils.java
 * @Description: SharedPreferences 工具类
 * @author: lijinghuan
 * @data: 2015-1-22 下午4:21:00
 */
package com.wz.levelvertical.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SharedPrefUtils {

    private static SharedPreferences getSharedPref(Context context) {
        SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        return settings;
    }

    public static boolean contains(Context context, String key) {
        SharedPreferences spf = getSharedPref(context);
        return spf.contains(key);
    }

    // [start]******************int类型****************

    private static Editor setBasePrefInt(Context context, String key, int value) {
        SharedPreferences spf = getSharedPref(context);
        Editor edit = spf.edit();
        edit.putInt(key, value);
        return edit;
    }

    public static boolean setPrefInt(Context context, String key, int value) {
        boolean flag = false;
        Editor edit = setBasePrefInt(context, key, value);
        synchronized (edit) {
            flag = edit.commit();
        }
        return flag;
    }

    public static void setPrefIntApply(Context context, String key, int value) {
        SharedPreferences spf = getSharedPref(context);
        Editor edit = spf.edit();
        edit.putInt(key, value);
        edit.apply();
    }

    public static int getPrefInt(Context context, String key, int defaultValue) {
        SharedPreferences spf = getSharedPref(context);
        if (spf.contains(key)) {
            return spf.getInt(key, defaultValue);
        } else {
            return defaultValue;
        }
    }

    public static int getPrefInt(Context context, String key) {
        return getPrefInt(context, key, 0);
    }

    // [end]******************************************

    // [start]******************long类型****************

    private static Editor setBasePrefLong(Context context, String key,
                                          long value) {
        SharedPreferences spf = getSharedPref(context);
        Editor edit = spf.edit();
        edit.putLong(key, value);
        return edit;
    }

    public static boolean setPrefLong(Context context, String key, long value) {
        boolean flag = false;
        Editor edit = setBasePrefLong(context, key, value);
        synchronized (edit) {
            flag = edit.commit();
        }
        return flag;
    }

    public static void setPrefLongApply(Context context, String key, long value) {
        SharedPreferences spf = getSharedPref(context);
        Editor edit = spf.edit();
        edit.putLong(key, value);
        edit.apply();
    }

    public static long getPrefLong(Context context, String key,
                                   long defaultValue) {
        SharedPreferences spf = getSharedPref(context);
        if (spf.contains(key)) {
            return spf.getLong(key, defaultValue);
        } else {
            return defaultValue;
        }
    }

    public static long getPrefLong(Context context, String key) {
        return getPrefLong(context, key, 0);
    }

    // [end]******************************************

    // [start]*******************String类型************

    private static Editor setBasePrefString(Context context, String key,
                                            String value) {
        SharedPreferences spf = getSharedPref(context);
        Editor edit = spf.edit();
        edit.putString(key, value);
        return edit;
    }

    public static boolean setPrefString(Context context, String key,
                                        String value) {
        boolean flag = false;
        Editor edit = setBasePrefString(context, key, value);
        synchronized (edit) {
            flag = edit.commit();
        }
        return flag;
    }

    public static void setPrefStringApply(Context context, String key,
                                          String value) {
        SharedPreferences spf = getSharedPref(context);
        Editor edit = spf.edit();
        edit.putString(key, value);
        edit.apply();
    }

    public static String getPrefString(Context context, String key,
                                       String defaultValue) {
        SharedPreferences spf = getSharedPref(context);
        if (spf.contains(key)) {
            return spf.getString(key, defaultValue);
        } else {
            return defaultValue;
        }
    }

    public static String getPrefString(Context context, String key) {
        return getPrefString(context, key, "");
    }

    // [end]******************************************

    // [start]****************boolean类型**************

    private static Editor setBasePrefBoolean(Context context, String key,
                                             boolean value) {
        SharedPreferences spf = getSharedPref(context);
        Editor edit = spf.edit();
        edit.putBoolean(key, value);
        return edit;
    }

    public static boolean setPrefBoolean(Context context, String key,
                                         boolean value) {
        boolean flag = false;
        Editor edit = setBasePrefBoolean(context, key, value);
        synchronized (edit) {
            flag = edit.commit();
        }
        return flag;
    }

    public static void setPrefBooleanApply(Context context, String key,
                                           boolean value) {
        SharedPreferences spf = getSharedPref(context);
        Editor edit = spf.edit();
        edit.putBoolean(key, value);
        edit.apply();
    }

    public static boolean getPrefBoolean(Context context, String key,
                                         boolean defaultValue) {
        SharedPreferences spf = getSharedPref(context);
        if (spf.contains(key)) {
            return spf.getBoolean(key, defaultValue);
        } else {
            return defaultValue;
        }
    }

    public static boolean getPrefBoolean(Context context, String key) {
        return getPrefBoolean(context, key, false);
    }

    // [end]******************************************

    /**
     * writeObject 方法负责写入特定类的对象的状态，以便相应的 readObject 方法可以还原它
     * 最后，用Base64.encode将字节文件转换成Base64编码保存在String中
     *
     * @param object 待加密的转换为String的对象
     * @return String   加密后的String
     */
    private static String Object2String(Object object) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            String string = new String(Base64.encode(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
            objectOutputStream.close();
            return string;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用Base64解密String，返回Object对象
     *
     * @param objectString 待解密的String
     * @return object      解密后的object
     */
    private static Object String2Object(String objectString) {
        byte[] mobileBytes = Base64.decode(objectString.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(mobileBytes);
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object object = objectInputStream.readObject();
            objectInputStream.close();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 使用SharedPreference保存对象
     *
     * @param fileKey    储存文件的key
     * @param key        储存对象的key
     * @param saveObject 储存的对象
     */
    public static void save(Context context, String fileKey, String key, Object saveObject) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileKey, Activity.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        String string = Object2String(saveObject);
        editor.putString(key, string);
        editor.commit();
    }

    /**
     * 获取SharedPreference保存的对象
     *
     * @param fileKey 储存文件的key
     * @param key     储存对象的key
     * @return object 返回根据key得到的对象
     */
    public static Object get(Context context, String fileKey, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileKey, Activity.MODE_PRIVATE);
        String string = sharedPreferences.getString(key, null);
        if (string != null) {
            return String2Object(string);
        } else {
            return null;
        }
    }
}
