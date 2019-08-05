package com.dt.baseapplication.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.dt.baseapplication.App;
import com.dt.baseapplication.R;
import com.dt.baseapplication.io.AppApiService;
import com.dt.baseapplication.io.HeaderInterceptor;
import com.dt.baseapplication.utils.DeviceInfo;
import com.dt.baseapplication.utils.UserUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dt.commons.utils.GenUtils;
import dt.commons.utils.LocationPoller;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * File Description
 * ------------------
 * Author : Feby Varghese
 * Email : feby@dt.ae
 * Date : 7/31/2019
 * Project : BaseApplication
 */
@Module
public class AppModule {
    private final App mApp;
    SharedPreferences sharedPreferences;
    Gson gson;

    private AppApiService appApiService;
    private DeviceInfo deviceInfo;
    private LocationPoller locationPoller;
    private ConnectivityManager cm;
    private UserUtils userUtils;

    public AppModule(App app) {
        mApp = app;
        sharedPreferences = mApp.getSharedPreferences(mApp.getString(R.string.app_name), Context.MODE_PRIVATE);
        GsonBuilder gsonBuilder = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .serializeNulls();
        gson = gsonBuilder.create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().
                addInterceptor(new HeaderInterceptor(mApp))
                .addInterceptor(interceptor)
                .connectTimeout(80, TimeUnit.SECONDS)
                .writeTimeout(80, TimeUnit.SECONDS)
                .readTimeout(80, TimeUnit.SECONDS)
                //  .addNetworkInterceptor(new StethoInterceptor())
                .build();
        Retrofit chfretrofit = new Retrofit.Builder()
                .baseUrl(mApp.getString(R.string.base_url))
                .client(client)

                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        appApiService = chfretrofit.create(AppApiService.class);

        locationPoller = new LocationPoller(mApp);
        cm = (ConnectivityManager) mApp.getSystemService(Context.CONNECTIVITY_SERVICE);

        userUtils = new UserUtils(sharedPreferences, gson, app.getComponent());

    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPrefs() {
        return sharedPreferences;
    }

    @Provides
    @Singleton
    AppApiService getApiService(App mApp) {
        return appApiService;
    }

    @Provides
    @Singleton
    LocationPoller getLocationPoller() {
        return locationPoller;
    }

    @Provides
    @Singleton
    Context provideAppContext() {
        return mApp;
    }

    @Provides
    @Singleton
    App provideApp() {
        return mApp;
    }

    @Provides
    @Singleton
    DeviceInfo provideDeviceInfo() {

        deviceInfo = new DeviceInfo(GenUtils.getDeviceIMEI(mApp),
                GenUtils.getDeviceVersionNumber(mApp), GenUtils.getDeviceVersionCode(mApp));
        return deviceInfo;

    }

    @Provides
    @Singleton
    UserUtils provideUserUtils(Gson gson, App app) {
        return userUtils;
    }


    @Provides
    boolean provideIsOnline() {
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork == null) {
            return false;
        }

        return activeNetwork.isConnectedOrConnecting();

    }

    @Provides
    @Singleton
    Gson provideGson() {
        return gson;
    }

}
