package com.dt.baseapplication.di;

import android.content.Context;

import com.dt.baseapplication.App;
import com.dt.baseapplication.presenter.MainPresenter;
import com.dt.baseapplication.view.IBaseView;
import com.dt.baseapplication.view.IMainView;

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
    MainPresenter getPreLoginPresenter(Context ctx) {
        return new MainPresenter((IMainView) mIView,
                App.from(ctx).getComponent());
    }
}
