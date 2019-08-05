package com.dt.baseapplication.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.dt.baseapplication.App;
import com.dt.baseapplication.io.AppApiService;
import com.dt.baseapplication.utils.DeviceInfo;
import com.dt.baseapplication.utils.UserUtils;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import dt.commons.utils.LocationPoller;

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

    DeviceInfo getDeviceInfo();

    boolean isOnline();

    UserUtils getUserUtils();

    Gson getGson();


}
