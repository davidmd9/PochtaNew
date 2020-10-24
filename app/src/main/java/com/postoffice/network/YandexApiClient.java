package com.postoffice.network;

import com.grapesnberries.curllogger.CurlLoggerInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YandexApiClient {

    private static final String URL = "https://geocode-maps.yandex.ru";

    public static Api getApi() {
        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(new CurlLoggerInterceptor("CURL")).build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .client(httpClient)
                .build();

        return retrofit.create(Api.class);
    }

}
