package com.ayfox.web.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Json工具类
 *
 * @author tenyon
 * @date 2024/11/22
 */
public class JsonUtils {
    private static final Gson gson = new Gson();

    /**
     * obj2jsonstr
     *
     * @param data
     * @return
     */
    public static String convertObj2Json(Object data) {
        return gson.toJson(data);
    }

    /**
     * jsonstr2obj
     *
     * @param jsonData
     * @param classz
     * @param <T>
     * @return
     */
    public static <T> T convertJson2Obj(String jsonData, Class<T> classz) {
        return gson.fromJson(jsonData, classz);
    }

    /**
     * jsonstr2array
     *
     * @param jsonData
     * @param classz
     * @param <T>
     * @return
     */
    public static <T> List<T> convertJson2List(String jsonData, Class<T> classz) {
        return gson.fromJson(jsonData, TypeToken.getParameterized(List.class, classz).getType());
    }
}
