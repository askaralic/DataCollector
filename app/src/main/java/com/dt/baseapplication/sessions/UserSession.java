package com.dt.baseapplication.sessions;

import static com.dt.baseapplication.utils.Constants.USER_PREFS;

import android.content.SharedPreferences;

import com.dt.baseapplication.di.AppComponent;
import com.dt.baseapplication.model.User;
import com.dt.baseapplication.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import dt.commons.utils.SharedPrefUtils;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * File Description
 * ------------------
 * Author : Feby Varghese
 * Email : feby@dt.ae
 * Date : 8/1/2019
 * Project : BaseApplication
 */
public class UserSession {
    SharedPreferences mPrefs;

    User user;
    Gson gson;
    AppComponent appComponent;


    public UserSession(SharedPreferences mPrefs, Gson _gson, AppComponent appComponent) {
        this.mPrefs = mPrefs;
        this.gson = _gson;
        this.appComponent = appComponent;
    }

    public User getLoggedInUser() {
        return user;
    }

    public void setLoggedInUser(User user) {
        this.user = user;
    }

    public String getUsername() {

        return SharedPrefUtils.getString(mPrefs, USER_PREFS, "");
    }

    public void setUsername(String username) {
        SharedPrefUtils.putString(mPrefs, USER_PREFS, username);
    }
    public boolean isLoggedIn() {
        return mPrefs.getBoolean(Constants.PREFERENCE_KEY_IS_LOGIN, false);
    }

    public void setIsLoggedIn(boolean status) {
        mPrefs.edit().putBoolean(Constants.PREFERENCE_KEY_IS_LOGIN, status).apply();
    }

    public RequestBody getRequestBody(JsonObject jsonObject) {
        return RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
    }

    public RequestBody getRequestBodyJsonArray(JsonArray jsonArray) {
        return RequestBody.create(MediaType.parse("application/json"), jsonArray.toString());
    }



}
