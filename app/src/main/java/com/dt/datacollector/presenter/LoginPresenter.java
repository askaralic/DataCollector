package com.dt.datacollector.presenter;

import com.dt.datacollector.di.AppComponent;
import com.dt.datacollector.view.ILoginView;

import com.commons.utils.Validate;

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
