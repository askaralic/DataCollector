package com.dt.baseapplication.presenter;

import com.dt.baseapplication.di.AppComponent;
import com.dt.baseapplication.view.IMainView;

/**
 * File Description
 * ------------------
 * Author : Feby Varghese
 * Email : feby@dt.ae
 * Date : 8/4/2019
 * Project : BaseApplication
 */
public class MainPresenter extends BasePresenter<IMainView> {
    public MainPresenter(IMainView _view, AppComponent appComponent) {
        super(_view, appComponent);
    }
}
