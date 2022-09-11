package com.dt.datacollector.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.dt.datacollector.R;
import com.dt.datacollector.di.ActivityModule;
import com.dt.datacollector.di.AppComponent;
import com.dt.datacollector.di.DaggerActivityComponent;
import com.dt.datacollector.presenter.LoginPresenter;
import com.dt.datacollector.view.ILoginView;

import javax.inject.Inject;

import butterknife.BindView;

import com.commons.utils.Validate;
import com.commons.views.TypeFacedButton;
import com.commons.views.TypeFacedEditText;

/**
 * File Description
 * ------------------
 * Author : Feby Varghese
 * Email : feby@dt.ae
 * Date : 7/31/2019
 * Project : BaseApplication
 */
public class LoginActivity extends BaseActivity implements ILoginView {

    @Inject
    LoginPresenter mPresenter;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btnLogin)
    TypeFacedButton btnLogin;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edtUserName)
    TypeFacedEditText edtUserName;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edtPassword)
    TypeFacedEditText edtPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected LoginPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void initViewsAndListeners() {

        btnLogin.setOnClickListener(view -> {
            if(Validate.isEmpty(edtUserName)){
                showMessage(getString(R.string.message_empty_username));
                return;
            }
            if(Validate.isEmpty(edtPassword)){
                showMessage(getString(R.string.message_empty_password));
                return;
            }
            getPresenter().onLogin(edtUserName.getText().toString(),edtPassword.getText().toString());
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void injectActivity(AppComponent component) {
        DaggerActivityComponent.builder().appComponent(component).activityModule(new ActivityModule(this)).build().inject(this);
    }

    @Override
    public void onLoginSuccess() {
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        finish();
    }
}
