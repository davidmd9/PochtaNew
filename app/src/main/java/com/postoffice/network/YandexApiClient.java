package com.postoffice.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YandexApiClient {

    private static final String URL = "https://geocode-maps.yandex.ru/1.x/";

    public static Api getApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build();

        return retrofit.create(Api.class);
    }

}
