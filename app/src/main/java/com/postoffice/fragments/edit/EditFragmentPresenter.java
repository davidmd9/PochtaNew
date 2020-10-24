package com.postoffice.fragments.edit;

import com.postoffice.model.google.Geometry;
import com.postoffice.model.google.GoogleGeocoderModel;
import com.postoffice.model.google.Result;
import com.postoffice.model.yandex.YaResponse;
import com.postoffice.network.GoogleApiClient;
import com.postoffice.network.YandexApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditFragmentPresenter extends EditFragmentContract.Presenter{

    @Override
    void onSearchFieldChanged(String query) {
        getGoogleGeocoderResult(query);
        getYandexGeocoderResult(query);
    }


    private void getGoogleGeocoderResult(String address){
        GoogleApiClient.getApi().getGoogleGeoCollection(address).enqueue(new Callback<GoogleGeocoderModel>() {
            @Override
            public void onResponse(Call<GoogleGeocoderModel> call, Response<GoogleGeocoderModel> response) {
                if(response.code() == 200 && response.body() != null){
                    GoogleGeocoderModel model = response.body();
                    List<Result> resultList = model.getResults();
                    if(resultList != null && resultList.size() > 0){
                        Geometry geometry = resultList.get(0).getGeometry();
                        view.showGoogleGeocoderResult(geometry.getLocation().getLat() + " " + geometry.getLocation().getLng(),
                                resultList.get(0).getFormatted_address());
                    }
                }
            }

            @Override
            public void onFailure(Call<GoogleGeocoderModel> call, Throwable t) {
                String ss = "";
            }
        });
    }

    private void getYandexGeocoderResult(String address){
        YandexApiClient.getApi().getYandexGeoCollection(address).enqueue(new Callback<YaResponse>() {
            @Override
            public void onResponse(Call<YaResponse> call, Response<YaResponse> response) {
                String ss = "";
            }

            @Override
            public void onFailure(Call<YaResponse> call, Throwable t) {
                String ss = "";
            }
        });
    }
}
