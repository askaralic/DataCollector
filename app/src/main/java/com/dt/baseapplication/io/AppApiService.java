package com.dt.baseapplication.io;

import com.google.gson.JsonObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * File Description
 * ------------------
 * Author : Feby Varghese
 * Email : feby@dt.ae
 * Date : 8/1/2019
 * Project : BaseApplication
 */
public interface AppApiService {

    @POST("ValidateUser")
    Call<JsonObject> ValidateUser(@Body RequestBody body);
}
