package com.dt.datacollector.di;

import com.dt.datacollector.ui.LoginActivity;
import com.dt.datacollector.ui.MainActivity;

import dagger.Component;

/**
 * File Description
 * ------------------
 * Author : Feby Varghese
 * Email : feby@dt.ae
 * Date : 7/31/2019
 * Project : BaseApplication
 */
@PerActivity
@Component(
        dependencies = AppComponent.class,
        modules = ActivityModule.class
)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(LoginActivity loginActivity);

}
