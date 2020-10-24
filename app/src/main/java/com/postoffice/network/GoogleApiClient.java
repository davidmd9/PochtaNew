package com.postoffice.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GoogleApiClient {

    private static final String URL = "https://maps.googleapis.com/maps/api/";
    private static final String TAG = "CURL";

    public static Api getApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build();

        return retrofit.create(Api.class);
    }

}
