package com.dt.baseapplication.utils;

import android.content.Context;

import dt.commons.utils.GenUtils;

/**
 * File Description
 * ------------------
 * Author : Feby Varghese
 * Email : feby@dt.ae
 * Date : 8/1/2019
 * Project : BaseApplication
 */
public class DeviceInfo {

    private String DeviceID;
    private int DeviceVersionCode = 0;
    private String DeviceVersionNumber;

    public DeviceInfo(String deviceID) {
        DeviceID = deviceID;
    }

    public DeviceInfo(String imei, String deviceVersionNumber, int deviceVersionCode) {
        this.DeviceID = imei;
        this.DeviceVersionNumber = deviceVersionNumber;
        this.DeviceVersionCode = deviceVersionCode;
    }


    public String getDeviceId(Context context) {
        this.DeviceID = GenUtils.getDeviceIMEI(context);
        return this.DeviceID;
    }

}
