package com.dt.datacollector.di;

import android.content.Context;

import com.dt.datacollector.App;
import com.dt.datacollector.presenter.LoginPresenter;
import com.dt.datacollector.presenter.MainPresenter;
import com.dt.datacollector.view.IBaseView;
import com.dt.datacollector.view.ILoginView;
import com.dt.datacollector.view.IMainView;

import dagger.Module;
import dagger.Provides;

/**
 * File Description
 * ------------------
 * Author : Feby Varghese
 * Email : feby@dt.ae
 * Date : 7/31/2019
 * Project : BaseApplication
 */
@Module
public class ActivityModule extends BaseModule {


    public ActivityModule(IBaseView view) {
        super(view);
    }

    @Provides
    @PerActivity
    MainPresenter getMainPresenter(Context ctx) {
        return new MainPresenter((IMainView) mIView,
                App.from(ctx).getComponent());
    }

    @Provides
    @PerActivity
    LoginPresenter getLoginPresenter(Context ctx) {
        return new LoginPresenter((ILoginView) mIView,
                App.from(ctx).getComponent());
    }

}
