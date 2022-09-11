package com.dt.datacollector.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dt.datacollector.App;
import com.dt.datacollector.R;
import com.dt.datacollector.di.AppComponent;
import com.dt.datacollector.presenter.BasePresenter;
import com.dt.datacollector.view.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.commons.utils.ToastUtils;
import com.commons.utils.Validate;
import com.commons.views.TypefaceCache;
import com.commons.views.TypefaceSpan;

/**
 * File Description
 * ------------------
 * Author : Feby Varghese
 * Email : feby@dt.ae
 * Date : 7/31/2019
 * Project : BaseApplication
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    private static final String TAG = BaseActivity.class.getSimpleName();
    AppComponent component;
    ProgressDialog progressDialog;
    ProgressDialog updateProgressDialog;

    Unbinder unbinder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        injectViews();
        component = App.from(this).getComponent();
        injectActivity(component);
        checkPresenterIsNull();
        initViewsAndListeners();
        if (getPresenter() != null)
            getPresenter().onCreate();


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getPresenter() != null)
            getPresenter().onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (getPresenter() != null)
            getPresenter().onStop();

    }

    private void setToolBarTitle(Toolbar mToolbar, String title) {
        SpannableString spannableString = new SpannableString(title);
        spannableString.setSpan(new TypefaceSpan(this, getString(R.string.app_font)),
                0, spannableString.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mToolbar.setTitle(spannableString);
    }

    public void setupToolBar(Toolbar mToolBar, String title) {
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setToolBarTitle(mToolBar, title);

    }


    @Override
    protected void onResume() {

        super.onResume();
        if (getPresenter() != null)
            getPresenter().onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (getPresenter() != null)
            getPresenter().onPause();
    }

    @Override
    protected void onDestroy() {
        if (getPresenter() != null)
            getPresenter().onDestroy();

        if(unbinder!=null){
            unbinder.unbind();
        }

        super.onDestroy();
    }

    private void injectViews() {
        unbinder = ButterKnife.bind(this);
    }


    public void showWaitDialog() {
        String dialogText = "";
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(this, "", (Validate.isEmpty(dialogText)) ? getString(R.string.please_wait_msg) : dialogText, false, true);
            ((TextView) progressDialog.getWindow().findViewById(android.R.id.message)).setTypeface(
                    TypefaceCache.get(this.getAssets(), this
                            .getResources().getString(R.string.app_font)), Typeface.BOLD
            );
        } else {
            progressDialog.show();
        }


        progressDialog.setCanceledOnTouchOutside(false);
    }

    public void showUpdateProgressDialog() {

        updateProgressDialog
                = ProgressDialog.show(this, "Update", "Downloading Please wait..", false, true);
        ((TextView) updateProgressDialog.getWindow().findViewById(android.R.id.message)).setTypeface(
                TypefaceCache.get(this.getAssets(), this
                        .getResources().getString(R.string.app_font)), Typeface.BOLD
        );
        updateProgressDialog.setCanceledOnTouchOutside(false);
    }

    public void showWaitDialog(String dialogText) {

    }


    protected abstract BasePresenter getPresenter();

    protected abstract void initViewsAndListeners();


    private void checkPresenterIsNull() {
        if (getPresenter() == null) {
            throw new IllegalStateException("please init mPresenter in initPresenter() method ");
        }
    }

    protected abstract int getLayout();

    protected abstract void injectActivity(AppComponent component);

    public void closeActivity() {
        this.finish();
    }

    public void replaceFragment(FragmentManager fragmentManager, Fragment fragment, int id, boolean shouldAddToBackStack) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (shouldAddToBackStack)
            ft.addToBackStack(fragment.getTag());
        ft.replace(id, fragment).commit();
    }

    @Override
    public void showAlertMessage(String title, String message, DialogInterface.OnClickListener onClickListener) {
        ToastUtils.showAlert(getApplicationContext(), message, title, onClickListener);
    }

    @Override
    public void showMessage(String s) {
        ToastUtils.cancelShortMessage();
        ToastUtils.showShortMessage(s, this);
    }

    @Override
    public void showProgressDialog(String dialogText) {
        progressDialog = ProgressDialog.show(this, "", (Validate.isEmpty(dialogText)) ? getString(R.string.please_wait_msg) : dialogText, false, true);
        ((TextView) progressDialog.getWindow().findViewById(android.R.id.message)).setTypeface(
                TypefaceCache.get(this.getAssets(), this
                        .getResources().getString(R.string.app_font)), Typeface.BOLD
        );
        progressDialog.setCanceledOnTouchOutside(false);

    }

    @Override
    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
            progressDialog.dismiss();
        }
    }

    @Override
    public void onInvalidSession() {

    }
}
