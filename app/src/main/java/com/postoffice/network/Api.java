package com.postoffice.network;

import com.postoffice.model.Tasks.TaskResult;
import com.postoffice.model.google.GoogleGeocoderModel;
import com.postoffice.model.osm.OSMResponse;
import com.postoffice.model.osm.OSMResponseModel;
import com.postoffice.model.yandex.YaResponse;
import com.postoffice.model.yandex.YaResponseModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @GET("/1.x/?apikey=52fc5c56-f7e0-4d8d-81e4-37f8c7501e78&format=json")
    Observable<YaResponseModel> getYandexGeoCollection(@Query("geocode") String geocode);

    @GET("geocode/json?key=AIzaSyB8TSCnDOoUBEVyB-mN2VVrMl_WHxr59Qk&language=ru")
    Observable<GoogleGeocoderModel> getGoogleGeoCollection(@Query("address") String address);

    @GET("search?&format=json")
    Observable<List<OSMResponse>> getOSMGeoCollection(@Query("q") String address);

    @GET("api/tasks")
    Call<TaskResult> getAllTasks();

    @Multipart
    @POST("api/result/store")
    Call<TaskResult> sendTask(
            @Part("lat") RequestBody lat,
            @Part("lng") RequestBody lng,
            @Part("task_id") RequestBody taks_id,
            @Part("address_id") RequestBody address_id,
            @Part("text") RequestBody text,
            @Part MultipartBody.Part image );

}
