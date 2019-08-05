package com.dt.baseapplication.io;

import com.dt.baseapplication.App;
import com.dt.baseapplication.BuildConfig;
import com.dt.baseapplication.utils.UserUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * File Description
 * ------------------
 * Author : Feby Varghese
 * Email : feby@dt.ae
 * Date : 8/4/2019
 * Project : BaseApplication
 */
public class HeaderInterceptor implements Interceptor {

    App mApp;

    public HeaderInterceptor(App mApp) {
        this.mApp = mApp;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        try {

            UserUtils userUtils = mApp.getComponent().getUserUtils();
            String authKey = (userUtils.getUser() != null) ? userUtils.getUser().AuthKey : "";
            if (authKey != null)
                if (authKey.isEmpty())
                    authKey = BuildConfig.DTProductsMobApiKey;
            request = request.newBuilder()
                    .addHeader("Authorization", authKey)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Response response = chain.proceed(request);
        return response;

    }
}
