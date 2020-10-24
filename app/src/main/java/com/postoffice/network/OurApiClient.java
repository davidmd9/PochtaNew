package com.postoffice.network;

import com.grapesnberries.curllogger.CurlLoggerInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OurApiClient {
    private static final String URL = "https://msofter.com/pochta/public/";
    private static final String TAG = "CURL";

    public static Api getApi() {
        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(new CurlLoggerInterceptor(TAG)).build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .baseUrl(URL)
                .build();

        return retrofit.create(Api.class);
    }
}


