package com.dt.baseapplication.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dt.baseapplication.R;
import com.dt.baseapplication.di.ActivityModule;
import com.dt.baseapplication.di.AppComponent;
import com.dt.baseapplication.di.DaggerActivityComponent;
import com.dt.baseapplication.presenter.LoginPresenter;
import com.dt.baseapplication.presenter.MainPresenter;
import com.dt.baseapplication.view.ILoginView;
import com.dt.baseapplication.view.IMainView;

import javax.inject.Inject;

import butterknife.BindView;
import dt.commons.utils.ToastUtils;
import dt.commons.utils.Validate;
import dt.commons.views.TypeFacedButton;
import dt.commons.views.TypeFacedEditText;

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
