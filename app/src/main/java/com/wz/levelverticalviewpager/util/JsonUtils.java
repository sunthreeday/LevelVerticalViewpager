package com.wz.levelverticalviewpager.util;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class JsonUtils {

    public static String toStr(Object object) {
        if (object == null) {
            return "";
        }
        return JSON.toJSONString(object);
    }

    public static <T> T parseT(HashMap map, Class<T> clz) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry e = (Map.Entry) iterator.next();
            sb.append("'").append(e.getKey()).append("':");
            sb.append("'").append(e.getValue()).append("',");
        }
        String build = sb.substring(0, sb.lastIndexOf(","));
        build += "}";
        return parseT(build, clz);
    }

    public static <T> T parseT(String json, Class<T> clz) {
        try {
            return JSON.parseObject(json, clz);
        } catch (JSONException e) {
            Log.i("parseT error: " ,json);
            return newNull(clz);
        }
    }

    public static <T> List<T> parseArray(String json, Class<T> clz) {
        try {
            return JSON.parseArray(json.trim(), clz);
        } catch (JSONException e) {
            Log.i("parse array error: ", json);
            return new ArrayList<T>(0);
        }
    }

    public static <T> T newNull(Class<T> clz) {
        try {
            T t = clz.newInstance();
            return t;
        } catch (InstantiationException e) {
            // json parase error
        } catch (IllegalAccessException e) {
            // json parase error
        }
        return null;
    }
}
