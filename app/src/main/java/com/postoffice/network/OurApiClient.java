package com.postoffice.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class OurApiClient {
    private static final String URL = "https://msofter.com/pochta/public/";
    private static final String TAG = "CURL";

    public static Api getApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build();

        return retrofit.create(Api.class);
    }
}
