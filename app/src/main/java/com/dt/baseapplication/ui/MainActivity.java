package com.dt.baseapplication.ui;

import android.os.Bundle;

import com.dt.baseapplication.R;
import com.dt.baseapplication.di.ActivityModule;
import com.dt.baseapplication.di.AppComponent;
import com.dt.baseapplication.di.DaggerActivityComponent;
import com.dt.baseapplication.presenter.MainPresenter;
import com.dt.baseapplication.view.IMainView;

import javax.inject.Inject;

/**
 * File Description
 * ------------------
 * Author : Feby Varghese
 * Email : feby@dt.ae
 * Date : 7/31/2019
 * Project : BaseApplication
 */
public class MainActivity extends BaseActivity implements IMainView {

    @Inject
    MainPresenter mainPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected MainPresenter getPresenter() {
        return mainPresenter;
    }

    @Override
    protected void initViewsAndListeners() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void injectActivity(AppComponent component) {

        DaggerActivityComponent.builder().appComponent(component).activityModule(new ActivityModule(this)).build().inject(this);

    }

}
