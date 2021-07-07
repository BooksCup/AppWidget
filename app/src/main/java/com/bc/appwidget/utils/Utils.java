package com.bc.appwidget.utils;

import android.content.Context;
import android.content.SharedPreferences;

public final class Utils {

    public static final String PREFERENCES_FILE_NAME = "schedule_settings";

    public static long getLong(Context context, String key, long defValue) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        return prefs.getLong(key, defValue);
    }

    public static boolean putLong(Context context, String key, long value) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        return prefs.edit().putLong(key, value).commit();
    }

}
