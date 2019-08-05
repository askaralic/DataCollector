package com.dt.baseapplication.presenter;

import android.content.Context;
import android.location.Location;

import com.dt.baseapplication.R;
import com.dt.baseapplication.di.AppComponent;
import com.dt.baseapplication.io.AppApiService;
import com.dt.baseapplication.utils.UserUtils;
import com.dt.baseapplication.view.IBaseView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import dt.commons.utils.LocationPoller;
import okhttp3.RequestBody;
import retrofit2.Response;

/**
 * File Description
 * ------------------
 * Author : Feby Varghese
 * Email : feby@dt.ae
 * Date : 8/1/2019
 * Project : BaseApplication
 */
public class BasePresenter<GV extends IBaseView> {

    GV mView;
    AppComponent appComponent;

    public BasePresenter(GV _view, AppComponent appComponent) {
        mView = _view;
        this.appComponent = appComponent;
    }

    public void onCreate() {

    }

    public void onViewCreated() {

    }

    public void onStart() {

    }

    public void onStop() {
    }

    public void onResume() {
    }

    public void onPause() {
    }

    public void onDestroy() {
        mView = null;
    }

    public Context getApplicationContext() {
        return appComponent.getContext();
    }

    public boolean isDeviceOnline() {

        if (appComponent.isOnline())
            return true;
        else {
            if (mView != null && appComponent.getContext() != null)
                mView.showMessage(appComponent.getContext().getString(R.string.check_internet_conn));
            return false;
        }
    }

    public boolean isValidResponse(Response<?> response) {
        if (response != null) {
            if (response.code() == 401) {
                mView.onInvalidSession();
                return false;
            } else if (!response.isSuccessful() || response.body() == null) {
                onError();
                return false;
            }
        }
        return true;
    }

    public AppApiService getApiService() {
        return appComponent.getAppApiService();
    }

    public void onError() {
        if (mView == null)
            return;
        mView.hideProgressDialog();
        mView.showMessage(getApplicationContext().getString(R.string.msg_error_occured));
    }

    public RequestBody getRequestBody(JsonObject jsonObject) {
        return appComponent.getUserUtils().getRequestBody(jsonObject);
    }

//    public int getMobileApplicationUno() {
//        return getApplicationContext().getResources().getInteger(R.integer.mobile_application_uno);
//
//    }

    public RequestBody getRequestBodyForArray(JsonArray jsonArray) {
        return appComponent.getUserUtils().getRequestBodyJsonArray(jsonArray);
    }

    public LocationPoller getLocationPoller() {
        return appComponent.getLocationPoller();
    }


    public UserUtils getUserUtils() {
        return appComponent.getUserUtils();
    }

    public Location getDeviceLocation() {

        return getUserUtils().getLastLocation();
    }

    public double getDeviceLatitude() {
        return (getDeviceLocation() != null) ? getDeviceLocation().getLatitude() : 0;
    }

    public double getDeviceLongitude() {
        return (getDeviceLocation() != null) ? getDeviceLocation().getLongitude() : 0;
    }

    public double getDeviceSpeed() {
        return (getDeviceLocation() != null) ? getDeviceLocation().getSpeed() : 0;
    }

    public double getDeviceGPSAccuracy() {
        return (getDeviceLocation() != null) ? getDeviceLocation().getAccuracy() : 0;
    }

}
