package com.dt.baseapplication.presenter;

import com.dt.baseapplication.di.AppComponent;
import com.dt.baseapplication.view.ILoginView;
import com.dt.baseapplication.view.IMainView;

import java.util.Locale;

import dt.commons.utils.Validate;

public class LoginPresenter extends BasePresenter<ILoginView> {
    public LoginPresenter(ILoginView _view, AppComponent appComponent) {
        super(_view, appComponent);
    }

    public void onLogin(String userName, String password) {
        if(Validate.isEqual(userName,"user1") && Validate.isEqual(password,"user1")) {
            mView.onLoginSuccess();
        }else{
            mView.showMessage("Invalid Credential");
        }
    }
}
