package com.tanlifei.support.utils;

import android.text.TextUtils;
import android.util.Log;

import com.tanlifei.support.constants.fixed.OnOffConstants;
import com.tanlifei.support.constants.level.OnOffLevel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * 但更漂亮,简单的和强大的日志的包装
 *
 * @author tanlifei
 * @date 2015年8月13日 上午11:30:51
 */
public final class Logger {


    /**
     * 　日志条目 Android的最大限制是~4076字节,
     * 　4000个字节作为块大小因为默认的字符集
     * 　是utf-8
     */
    private static final int CHUNK_SIZE = 4000;

    /**
     * 它用于json漂亮的打印
     */
    private static final int JSON_INDENT = 4;

    /**
     * 为了防止可读性,马克斯方法计数限制5
     */
    private static final int MAX_METHOD_COUNT = 5;

    /**
     * 最小的堆栈跟踪指数,从这个类开始后两个本地调用。
     */
    private static final int MIN_STACK_OFFSET = 3;

    /**
     * 它是用来确定日志设置计数等方法,线程信息的可见性
     */
    private static final Settings settings = new Settings();

    /**
     * 绘图工具箱
     */
    private static final char TOP_LEFT_CORNER = '╔';
    private static final char BOTTOM_LEFT_CORNER = '╚';
    private static final char MIDDLE_CORNER = '╟';
    private static final char HORIZONTAL_DOUBLE_LINE = '║';
    private static final String DOUBLE_DIVIDER = "════════════════════════════════════════════════════════════════════";
    private static final String SINGLE_DIVIDER = "────────────────────────────────────────────────────────────────────";
    private static final String TOP_BORDER = TOP_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private static final String BOTTOM_BORDER = BOTTOM_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private static final String MIDDLE_BORDER = MIDDLE_CORNER + SINGLE_DIVIDER + SINGLE_DIVIDER;

    /**
     * 标签是用于日志,这个名字有点不同
     * 为了区分日志容易过滤
     */
    private static String TAG = "PRETTYLOGGER";

    //no instance
    private Logger() {
    }

    /**
     * 它是用来设置对象为了改变设置
     *
     * @return the settings object
     */
    public static Settings init() {
        return settings;
    }

    /**
     * 它是用来改变标签
     *
     * @param tag 标签是给定的字符串将被用于日志记录器
     */
    public static Settings init(String tag) {
        if (tag == null) {
            throw new NullPointerException("tag may not be null");
        }
        if (tag.trim().length() == 0) {
            throw new IllegalStateException("tag may not be empty");
        }
        Logger.TAG = tag;
        return settings;
    }

    public static void d(String message) {
        d(TAG, message);
    }

    public static void d(String tag, String message) {
        d(tag, message, settings.methodCount);
    }

    public static void d(String message, int methodCount) {
        d(TAG, message, methodCount);
    }

    public static void d(String tag, String message, int methodCount) {
        validateMethodCount(methodCount);
        log(Log.DEBUG, tag, message, methodCount);
    }

    public static void e(String message) {
        e(TAG, message);
    }

    public static void e(String tag, String message) {
        e(tag, message, null, settings.methodCount);
    }

    public static void e(Exception e) {
        e(TAG, null, e, settings.methodCount);
    }

    public static void e(String tag, Exception e) {
        e(tag, null, e, settings.methodCount);
    }

    public static void e(String message, int methodCount) {
        validateMethodCount(methodCount);
        e(TAG, message, methodCount);
    }

    public static void e(String tag, String message, int methodCount) {
        validateMethodCount(methodCount);
        e(tag, message, null, methodCount);
    }

    public static void e(String tag, String message, Exception e) {
        e(tag, message, e, settings.methodCount);
    }

    public static void e(String tag, String message, Exception e, int methodCount) {
        validateMethodCount(methodCount);
        if (e != null && message != null) {
            message += " : " + e.toString();
        }
        if (e != null && message == null) {
            message = e.toString();
        }
        if (message == null) {
            message = "No message/exception is set";
        }
        log(Log.ERROR, tag, message, methodCount);
    }

    public static void w(String message) {
        w(TAG, message);
    }

    public static void w(String tag, String message) {
        w(tag, message, settings.methodCount);
    }

    public static void w(String message, int methodCount) {
        w(TAG, message, methodCount);
    }

    public static void w(String tag, String message, int methodCount) {
        validateMethodCount(methodCount);
        log(Log.WARN, tag, message, methodCount);
    }

    public static void i(String message) {
        i(TAG, message);
    }

    public static void i(String tag, String message) {
        i(tag, message, settings.methodCount);
    }

    public static void i(String message, int methodCount) {
        i(TAG, message, methodCount);
    }

    public static void i(String tag, String message, int methodCount) {
        validateMethodCount(methodCount);
        log(Log.INFO, tag, message, methodCount);
    }

    public static void v(String message) {
        v(TAG, message);
    }

    public static void v(String tag, String message) {
        v(tag, message, settings.methodCount);
    }

    public static void v(String message, int methodCount) {
        v(TAG, message, methodCount);
    }

    public static void v(String tag, String message, int methodCount) {
        validateMethodCount(methodCount);
        log(Log.VERBOSE, tag, message, methodCount);
    }

    public static void wtf(String message) {
        wtf(TAG, message);
    }

    public static void wtf(String tag, String message) {
        wtf(tag, message, settings.methodCount);
    }

    public static void wtf(String message, int methodCount) {
        wtf(TAG, message, methodCount);
    }

    public static void wtf(String tag, String message, int methodCount) {
        validateMethodCount(methodCount);
        log(Log.ASSERT, tag, message, methodCount);
    }

    /**
     * json格式内容和打印
     *
     * @param json json内容
     */
    public static void json(String json) {
        json(TAG, json);
    }

    public static void json(String tag, String json) {
        json(tag, json, settings.methodCount);
    }

    public static void json(String json, int methodCount) {
        json(TAG, json, methodCount);
    }

    /**
     * json格式内容和打印
     *
     * @param json        json内容
     * @param methodCount 将打印的方法
     */
    public static void json(String tag, String json, int methodCount) {
        validateMethodCount(methodCount);
        if (TextUtils.isEmpty(json)) {
            d(tag, "Empty/Null json content", methodCount);
            return;
        }
        try {
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                String message = jsonObject.toString(JSON_INDENT);
                d(tag, message, methodCount);
                return;
            }
            if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                String message = jsonArray.toString(JSON_INDENT);
                d(tag, message, methodCount);
            }
        } catch (JSONException e) {
            d(tag, e.getCause().getMessage() + "\n" + json, methodCount);
        }
    }

    /**
     * json格式内容和打印
     *
     * @param xml json内容
     */
    public static void xml(String xml) {
        xml(TAG, xml);
    }

    public static void xml(String tag, String xml) {
        xml(tag, xml, settings.methodCount);
    }

    public static void xml(String xml, int methodCount) {
        xml(TAG, xml, methodCount);
    }

    /**
     * json格式内容和打印
     *
     * @param xml         json内容
     * @param methodCount 将打印的方法
     */
    public static void xml(String tag, String xml, int methodCount) {
        validateMethodCount(methodCount);
        if (TextUtils.isEmpty(xml)) {
            d(tag, "Empty/Null xml content", methodCount);
            return;
        }

        try {
            Source xmlInput = new StreamSource(new StringReader(xml));
            StreamResult xmlOutput = new StreamResult(new StringWriter());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(xmlInput, xmlOutput);
            d(tag, xmlOutput.getWriter().toString().replaceFirst(">", ">\n"), methodCount);
        } catch (TransformerException e) {
            d(tag, e.getCause().getMessage() + "\n" + xml, methodCount);
        }
    }

    /**
     * 这个方法是同步,以避免混乱的日志的秩序。
     */
    private synchronized static void log(int logType, String tag, String message, int methodCount) {
        if (settings.logLevel == OnOffLevel.OFF) {
            return;
        }
        logTopBorder(logType, tag);
        logHeaderContent(logType, tag, methodCount);

        //get bytes of message with system's default charset (which is UTF-8 for Android)
        byte[] bytes = message.getBytes();
        int length = bytes.length;
        if (length <= CHUNK_SIZE) {
            if (methodCount > 0) {
                logDivider(logType, tag);
            }
            logContent(logType, tag, message);
            logBottomBorder(logType, tag);
            return;
        }
        if (methodCount > 0) {
            logDivider(logType, tag);
        }
        for (int i = 0; i < length; i += CHUNK_SIZE) {
            int count = Math.min(length - i, CHUNK_SIZE);
            //createDialog a new String with system's default charset (which is UTF-8 for Android)
            logContent(logType, tag, new String(bytes, i, count));
        }
        logBottomBorder(logType, tag);
    }

    private static void logTopBorder(int logType, String tag) {
        logChunk(logType, tag, TOP_BORDER);
    }

    private static void logHeaderContent(int logType, String tag, int methodCount) {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        if (settings.showThreadInfo) {
            logChunk(logType, tag, HORIZONTAL_DOUBLE_LINE + " Thread: " + Thread.currentThread().getName());
            logDivider(logType, tag);
        }
        String level = "";

        int stackOffset = getStackOffset(trace);

        for (int i = methodCount; i > 0; i--) {
            int stackIndex = i + stackOffset;
            StringBuilder builder = new StringBuilder();
            builder.append("║ ")
                    .append(level)
                    .append(getSimpleClassName(trace[stackIndex].getClassName()))
                    .append(".")
                    .append(trace[stackIndex].getMethodName())
                    .append(" ")
                    .append(" (")
                    .append(trace[stackIndex].getFileName())
                    .append(":")
                    .append(trace[stackIndex].getLineNumber())
                    .append(")");
            level += "   ";
            logChunk(logType, tag, builder.toString());
        }
    }

    private static void logBottomBorder(int logType, String tag) {
        logChunk(logType, tag, BOTTOM_BORDER);
    }

    private static void logDivider(int logType, String tag) {
        logChunk(logType, tag, MIDDLE_BORDER);
    }

    private static void logContent(int logType, String tag, String chunk) {
        String[] lines = chunk.split(System.getProperty("line.separator"));
        for (String line : lines) {
            logChunk(logType, tag, HORIZONTAL_DOUBLE_LINE + " " + line);
        }
    }

    private static void logChunk(int logType, String tag, String chunk) {
        String finalTag = formatTag(tag);
        switch (logType) {
            case Log.ERROR:
                Log.e(finalTag, chunk);
                break;
            case Log.INFO:
                Log.i(finalTag, chunk);
                break;
            case Log.VERBOSE:
                Log.v(finalTag, chunk);
                break;
            case Log.WARN:
                Log.w(finalTag, chunk);
                break;
            case Log.ASSERT:
                Log.wtf(finalTag, chunk);
                break;
            case Log.DEBUG:
                // Fall through, log debug by default
            default:
                Log.d(finalTag, chunk);
                break;
        }
    }

    private static String getSimpleClassName(String name) {
        int lastIndex = name.lastIndexOf(".");
        return name.substring(lastIndex + 1);
    }

    private static void validateMethodCount(int methodCount) {
        if (methodCount < 0 || methodCount > MAX_METHOD_COUNT) {
            throw new IllegalStateException("methodCount must be > 0 and < 5");
        }
    }

    private static String formatTag(String tag) {
        if (!TextUtils.isEmpty(tag) && !TextUtils.equals(TAG, tag)) {
            return TAG + "-" + tag;
        }
        return TAG;
    }

    /**
     * 确定的起始索引堆栈跟踪,在方法调用这个类。
     *
     * @param trace 堆栈跟踪
     * @return 堆栈抵消
     */
    private static int getStackOffset(StackTraceElement[] trace) {
        for (int i = MIN_STACK_OFFSET; i < trace.length; i++) {
            StackTraceElement e = trace[i];
            if (!e.getClassName().equals(Logger.class.getName())) {
                return --i;
            }
        }
        return -1;
    }

    public static class Settings {
        int methodCount = 2;
        boolean showThreadInfo = true;

        /**
         * 决定日志打印出来
         */
        OnOffLevel logLevel = OnOffConstants.LOG_LEVEL;

        public Settings hideThreadInfo() {
            showThreadInfo = false;
            return this;
        }

        public Settings setMethodCount(int methodCount) {
            validateMethodCount(methodCount);
            this.methodCount = methodCount;
            return this;
        }

        public Settings setLogLevel(OnOffLevel logLevel) {
            this.logLevel = logLevel;
            return this;
        }
    }

}