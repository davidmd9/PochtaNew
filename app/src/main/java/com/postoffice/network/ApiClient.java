package com.postoffice.network;

import com.grapesnberries.curllogger.CurlLoggerInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String URL = "";
    private static final String TAG = "CURL";

    public static Api getApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build();

        return retrofit.create(Api.class);
    }

}
