package com.postoffice.network;

import com.postoffice.model.Tasks.TaskResult;
import com.postoffice.model.google.GoogleGeocoderModel;
import com.postoffice.model.yandex.YaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @GET("?apikey=52fc5c56-f7e0-4d8d-81e4-37f8c7501e78&format=json")
    Call<YaResponse> getYandexGeoCollection(@Query("geocode") String geocode);

    @GET("geocode/json?key=AIzaSyB8TSCnDOoUBEVyB-mN2VVrMl_WHxr59Qk")
    Call<GoogleGeocoderModel> getGoogleGeoCollection(@Query("address") String address);

    @GET("api/tasks")
    Call<TaskResult> getAllTasks();
}
