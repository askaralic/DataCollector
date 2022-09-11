package com.dt.datacollector.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.dt.datacollector.App;
import com.dt.datacollector.io.AppApiService;
import com.dt.datacollector.utils.UserUtils;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import com.commons.utils.LocationPoller;

/**
 * File Description
 * ------------------
 * Author : Feby Varghese
 * Email : feby@dt.ae
 * Date : 7/31/2019
 * Project : BaseApplication
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(App mApp);

    Context getContext();

    AppApiService getAppApiService();

    LocationPoller getLocationPoller();

    SharedPreferences getSharedPreferences();


    boolean isOnline();

    UserUtils getUserUtils();

    Gson getGson();


}
