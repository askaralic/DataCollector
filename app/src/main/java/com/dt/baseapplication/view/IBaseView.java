package com.dt.baseapplication.view;

import android.content.DialogInterface;

/**
 * File Description
 * ------------------
 * Author : Feby Varghese
 * Email : feby@dt.ae
 * Date : 7/31/2019
 * Project : BaseApplication
 */
public interface IBaseView {

    void showMessage(String s);
    void onInvalidSession();

    void showAlertMessage(String title, String message, DialogInterface.OnClickListener onClickListener);

    void showProgressDialog(String dialogText);

    void hideProgressDialog();

}
