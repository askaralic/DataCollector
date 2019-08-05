package com.dt.baseapplication.di;

import com.dt.baseapplication.view.IBaseView;

/**
 * File Description
 * ------------------
 * Author : Feby Varghese
 * Email : feby@dt.ae
 * Date : 7/31/2019
 * Project : BaseApplication
 */
public abstract class BaseModule {

    protected IBaseView mIView;

    public BaseModule(IBaseView view) {
        this.mIView = view;
    }

}
