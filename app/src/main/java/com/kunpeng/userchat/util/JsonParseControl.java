package com.kunpeng.userchat.util;

import android.content.Context;
import android.content.res.AssetManager;
import com.kunpeng.common.utils.json.GsonImplHelp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangminzheng on 2018/1/16 下午2:50.
 * Email:ahtchmz@gmail.com
 */
public class JsonParseControl {
    /**
     * 解析json
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String parseJson(Context context, String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bf = null;
        try {
            AssetManager assetManager = context.getAssets();
            bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bf != null) {
                    bf.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 字符串解析成集合
     *
     * @param data
     * @param clazz
     * @param <E>
     * @return
     */
    public static <E> ArrayList<E> parseArray(String data, Class<E> clazz) {
        List<E> list = GsonImplHelp.get().toList(data, clazz);
        return (ArrayList<E>) list;
    }
}
