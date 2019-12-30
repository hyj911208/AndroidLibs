package com.kunpeng.common.utils.json;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by xuqm on 2016/6/3.
 */
public abstract class Json {
    private static Json json;

    Json() {
    }

    public static Json get() {
        if (json == null) {
            json = new GsonImplHelp();
        }
        return json;
    }

    public abstract String toJson(Object src);

    public abstract <T> T toObject(String json, Class<T> claxx);

    public abstract <T> T toObject(byte[] bytes, Class<T> claxx);

    public abstract <T> List<T> toList(String json, Class<T> claxx);

    public abstract <T> T toObject(String json, Type type);

}
