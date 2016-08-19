package com.support.localinterfacetest;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tanlifei on 16/1/28.
 */
public class JsonReader {

    //本地自定义json常量
    //public static final String CUSTOM_JSON_FOLDER = "customjson";//asset子文件夹名
    private static volatile JsonReader instance = null;
    private static Map<String, JsonReaderBean> jsonReader = new HashMap<String, JsonReaderBean>();

    private JsonReader() {
    }

    public static JsonReader getInstance() {
        if (instance == null) {
            synchronized (JsonReader.class) {
                if (instance == null) {
                    instance = new JsonReader();
                    addJsonReader(jsonReader);
                }
            }
        }
        return instance;
    }

    /**
     * 读取本地文件开关,上线时注释下面的代码即可
     */
    private static void addJsonReader(Map<String, JsonReaderBean> jsonReader) {
        JsonReaderCommon.addTestUrl(jsonReader);//公用模块
    }


    /**
     * 读取本地文件内容
     *
     * @param context
     * @param url
     * @return
     */
    public String getJsonReaderFileContent(Context context, String url) {
        return getFileFromRaw(context, jsonReader.get(url).getFileId());
    }

    /**
     * 是不读取本地数据
     *
     * @param url
     * @return true 时表示读取本地数据，false 请求网络接口
     */
    public boolean getJsonReader(String url) {
        if (null == jsonReader.get(url)) {
            return false;
        } else {
            return jsonReader.get(url).isReader();
        }
    }

    /**
     * 读取本地文件数据
     *
     * @param context
     * @param rawId
     * @return
     */
    public static String getFileFromRaw(Context context,int rawId) {
        try {
            InputStream inputStream = context.getResources().openRawResource(rawId);
            InputStreamReader inputStreamReader = new InputStreamReader(
                    inputStream, "utf-8");
            BufferedReader reader = new BufferedReader(inputStreamReader);
            StringBuffer sb = new StringBuffer("");
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
