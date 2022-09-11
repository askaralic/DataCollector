package com.dt.datacollector.utils;

import android.content.SharedPreferences;
import android.location.Location;

import com.dt.datacollector.di.AppComponent;
import com.dt.datacollector.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import com.commons.utils.SharedPrefUtils;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.dt.datacollector.utils.Constants.USER_PREFS;

/**
 * File Description
 * ------------------
 * Author : Feby Varghese
 * Email : feby@dt.ae
 * Date : 8/1/2019
 * Project : BaseApplication
 */
public class UserUtils {
    SharedPreferences mPrefs;

    User user;
    Gson gson;
    Location location;
    AppComponent appComponent;


    public UserUtils(SharedPreferences mPrefs, Gson _gson, AppComponent appComponent) {
        this.mPrefs = mPrefs;
        this.gson = _gson;
        this.appComponent = appComponent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {

        return SharedPrefUtils.getString(mPrefs, USER_PREFS, "");
    }

    public void setUsername(String username) {
        SharedPrefUtils.putString(mPrefs, USER_PREFS, username);

    }

    public RequestBody getRequestBody(JsonObject jsonObject) {
        return RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
    }

    public RequestBody getRequestBodyJsonArray(JsonArray jsonArray) {
        return RequestBody.create(MediaType.parse("application/json"), jsonArray.toString());
    }

    public Location getLastLocation() {
        return location;
    }

    public void setLastLocation(Location location) {
        this.location = location;
    }

}
