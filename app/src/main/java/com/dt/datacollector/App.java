package com.dt.datacollector;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import com.dt.datacollector.di.AppComponent;
import com.dt.datacollector.di.AppModule;
import com.dt.datacollector.di.DaggerAppComponent;

import timber.log.Timber;

/**
 * File Description
 * ------------------
 * Author : Feby Varghese
 * Email : feby@dt.ae
 * Date : 7/31/2019
 * Project : BaseApplication
 */
public class App extends Application {

    AppComponent appComponent;

    public static App from(@NonNull Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder().
                appModule(new AppModule(this))
                .build();

        //Timber
        Timber.plant(new Timber.DebugTree());
    }


    public AppComponent getComponent() {
        return appComponent;
    }
}
