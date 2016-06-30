package com.tanlifei.support.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * CommonParserHandler <br>统一解析Json
 * <li>
 * <Strong> Method </Strong>getData
 * <ul> {@link #getKeyResult(String, String)} 根据key拿vulue数据的内容</ul>
 * <ul>{@link #parseToObjectBean(String, Class)} json字符串转化为实体bean</ul>
 * <ul>{@link #parseToObjectList(String, Class)} json字符串转化为实体bean集合</ul>
 * <ul>{@link #parseToObjectList(JSONObject, Class, String)} 获取为key字段json的内容,并转化为实体bean集合</ul>
 * </li>
 *
 * @author tanlifei
 * @version 2015-01-26
 */
public class JsonUtils {



    /**
     * 拿到字段的值
     *
     * @param json
     * @param key  要解析的字段名
     * @return
     * @throws JSONException
     */
    public static String getKeyResult(String json, String key) {
        try {
            JSONObject objJson = JSON
                    .parseObject(json);
            return objJson.getString(key);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * json字符串转化为实体bean
     *
     * @param json
     * @param clazz 实体bean名
     * @return
     * @throws JSONException
     */
    public static <T> T parseToObjectBean(String json, Class<T> clazz) {
        try {
            return JSON.parseObject(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * json字符串转化为实体bean集合
     *
     * @param json
     * @param clazz 实体bean名
     * @return
     * @throws JSONException
     */
    public static <T> List<T> parseToObjectList(String json, Class<T> clazz) {
        try {
            JSONArray jsonArray = JSON.parseArray(json);
            Iterator<Object> iterator = jsonArray.iterator();
            List<T> container = new ArrayList<T>();
            while (iterator.hasNext())
                container.add(JSON.parseObject(iterator.next().toString(), clazz));
            return container;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取为key字段json的内容,并转化为实体bean集合
     *
     * @param json
     * @param clazz 实体bean名
     * @param key   要解析的字段名
     * @return
     * @throws JSONException
     */
    public static <T> List<T> parseToObjectList(JSONObject json, Class<T> clazz, String key) {
        try {
            List<T> container = new ArrayList<T>();
            container = JSON.parseArray(json.getString(key), clazz);
            return container;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 将一个Map类型的对象转化为JSON
     * @Title: mapToJson
     * @param map 集合对象
     * @return String 返回类型
     */
    public static String mapToJson(Map<?, ?> map) {
        StringBuilder json = new StringBuilder();
        Iterator it = map.keySet().iterator();
        json.append("{");
        if (map != null && map.size() > 0) {
            while (it.hasNext()) {
                Object object = it.next();
                json.append(objectToJson(object));
                json.append(":");
                json.append(objectToJson(map.get(object)));
                json.append(",");

            }
            json.setCharAt(json.length() - 1, '}');// 将最后一个逗号改为"}"
        } else {
            json.append("}");
        }
        return json.toString();
    }


    /**
     * Object类型的对象转化为JSON
     * @Title: objectToJson
     * @return String 返回类型
     */
    public static String objectToJson(Object object) {
        StringBuilder json = new StringBuilder();
        if (object == null) {
            json.append("\"\"");
        } else if (object instanceof String) {
            json.append("\"").append(stringToJson(object.toString())).append("\"");
        } else if (object instanceof Integer || object instanceof Character || object instanceof Float
                || object instanceof Boolean || object instanceof Short || object instanceof Double
                || object instanceof Long || object instanceof Byte) {
            json.append(stringToJson(object.toString()));
        } else if (object instanceof Map) {
            json.append(mapToJson((Map<?, ?>) object));
        } else if (object instanceof Object[]) {
            json.append(arrayToJson((Object[]) object));
        } else if (object instanceof Collection<?>) {
            json.append(collectionToJson((Collection<?>) object));
        } else {
//            json.append(beanToJson(object));
        }
        return json.toString();
    }


    /**
     *
     * @Title: stringToJson
     * @Description: 将String类型的对象转化为JSON
     * @param str 源数据
     * @return String 返回类型
     */
    public static String stringToJson(String str) {
        StringBuilder json = new StringBuilder();
        if (str == null) {
            return "";
        }
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            switch (ch) {
                case '\\':
                    json.append("\\\\");
                    break;
                case '\b':
                    json.append("\\b");
                    break;
                case '\f':
                    json.append("\\f");
                    break;
                case '\n':
                    json.append("\\n");
                    break;
                case '\r':
                    json.append("\\r");
                    break;
                case '\t':
                    json.append("\\t");
                    break;
                case '\"':
                    json.append("\\\"");
                    break;
                case '\'':
                    json.append("\\\'");
                    break;
                default:
                    if (ch >= '\u0000' && ch <= '\u001F') {
                        String ss = Integer.toHexString(ch);
                        json.append("\\u");
                        for (int k = 0; k < 4 - ss.length(); k++) {
                            json.append('0');
                        }
                        json.append(ss.toUpperCase());
                    } else {
                        json.append(ch);
                    }
            }
        }
        return json.toString();
    }

    /**
     * 将集合类型的对象转化为JSON
     * @Title: collectionToJson
     * @param collection
     * @return String 返回类型
     */
    public static String collectionToJson(Collection<?> collection) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (collection != null && collection.size() > 0) {
            for (Object object : collection) {
                json.append(objectToJson(object));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }

    /**
     * 将数组转化为JSON
     * @Title: arrayToJson
     * @param array 源数据
     * @return String 返回类型
     */
    public static String arrayToJson(Object[] array) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (array != null && array.length > 0) {
            for (Object object : array) {
                json.append(objectToJson(object));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }

//    /**
//     * 将一个Bean类型的对象转化为JSON
//     * @Title: beanToJson
//     * @param bean 实体对象
//     * @return String 返回类型
//     */
//    public static String beanToJson(Object bean) {
//        StringBuilder json = new StringBuilder();
//        Class cl = bean.getClass();
//        json.append("{");
//        Field[] fields = cl.getDeclaredFields();
//        for (Field f : fields) {
//            try {
//                StringBuilder fieldName = new StringBuilder(f.getName());
//                fieldName.setCharAt(0, Character.toUpperCase(fieldName.charAt(0)));
//                Method method = cl.getDeclaredMethod("get" + fieldName.toString(), null);// 获取类中的get方法
//                json.append(objectToJson(method.invoke(bean, null)));
//                String value = objectToJson(method.invoke(bean, "nu"));
//                if (value != null && !value.equals("")) {
//                }
//            } catch (SecurityException e) {
//                e.printStackTrace();
//
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//
//            } catch (IllegalArgumentException e) {
//                e.printStackTrace();
//
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//
//            }
//        }
//        json.setCharAt(json.length() - 1, '}');// 将最后一个逗号改为"}"
//        return json.toString();
//    }
}
