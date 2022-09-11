package com.commons.utils;

import android.content.SharedPreferences;

/**
 * Created by Wajid on 29-03-2016.
 */
public class SharedPrefUtils {

    public static void putString(SharedPreferences mPrefs, String key, String value) {
        mPrefs.edit().putString(key, value).apply();
    }

    public static void putInteger(SharedPreferences mPrefs, String key, int value) {
        mPrefs.edit().putInt(key, value).apply();
    }

    public static String getString(SharedPreferences mPrefs, String key, String defValue) {
        return mPrefs.getString(key, defValue);
    }

    public static void putLong(SharedPreferences mPrefs, String key, long value) {
        mPrefs.edit().putLong(key, value).apply();
    }

    public static long getLong(SharedPreferences mPrefs, String key, long defValue) {
        return mPrefs.getLong(key, defValue);
    }

    public static float getFloat(SharedPreferences mPrefs, String key, float defValue) {
        return mPrefs.getFloat(key, defValue);
    }

    public static void putBoolean(SharedPreferences mPrefs, String key, boolean value) {
        mPrefs.edit().putBoolean(key, value).apply();
    }

    public static void putFloat(SharedPreferences mPrefs, String key, float value) {
        mPrefs.edit().putFloat(key, value).apply();
    }

    public static boolean getBoolean(SharedPreferences mPrefs, String key, boolean defValue) {
        return mPrefs.getBoolean(key, defValue);
    }

    public static int getInteger(SharedPreferences mPrefs, String key, int defValue) {
        return mPrefs.getInt(key, defValue);
    }

}
