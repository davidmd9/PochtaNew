package com.postoffice.network;

import com.postoffice.model.yandex.YaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @GET("https://geocode-maps.yandex.ru/1.x/?apikey=52fc5c56-f7e0-4d8d-81e4-37f8c7501e78&format=json")
    Call<YaResponse> getYandexGeoCollection(@Query("geocode") String geocode);
}
