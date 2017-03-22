package com.baoxin.bx_framework.util;

/**
 * Created by Admin on 2016/8/12.
 */
public class Log {
    /**
     * VERBOSE
     *
     * @param tag
     * @param text
     */
    public static void v(String tag, String text) {
        android.util.Log.v(tag, text);
    }

    /**
     * DEBUG
     *
     * @param tag
     * @param text
     */
    public static void d(String tag, String text) {
        android.util.Log.d(tag, text);
    }

    /**
     * INFO
     *
     * @param tag
     * @param text
     */
    public static void i(String tag, String text) {
        android.util.Log.i(tag, text);
    }

    /**
     * WARN
     *
     * @param tag
     * @param text
     */
    public static void w(String tag, String text) {
        android.util.Log.w(tag, text);
    }

    /**
     * ERROR
     *
     * @param tag
     * @param text
     */
    public static void e(String tag, String text) {
        android.util.Log.e(tag, text);
    }


}
