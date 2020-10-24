package com.postoffice.fragments.edit;

import com.postoffice.model.google.Geometry;
import com.postoffice.model.google.GoogleGeocoderModel;
import com.postoffice.model.google.Result;
import com.postoffice.model.yandex.Address;
import com.postoffice.model.yandex.FeatureMember;
import com.postoffice.model.yandex.GeoObject;
import com.postoffice.model.yandex.GeoObjectCollection;
import com.postoffice.model.yandex.GeocoderMetaData;
import com.postoffice.model.yandex.MetaDataProperty;
import com.postoffice.model.yandex.Point;
import com.postoffice.model.yandex.YaResponse;
import com.postoffice.model.yandex.YaResponseModel;
import com.postoffice.network.GoogleApiClient;
import com.postoffice.network.YandexApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditFragmentPresenter extends EditFragmentContract.Presenter {

    @Override
    void onSearchFieldChanged(String query) {
        getGoogleGeocoderResult(query);
        getYandexGeocoderResult(query);
    }


    private void getGoogleGeocoderResult(String address) {
        GoogleApiClient.getApi().getGoogleGeoCollection(address).enqueue(new Callback<GoogleGeocoderModel>() {
            @Override
            public void onResponse(Call<GoogleGeocoderModel> call, Response<GoogleGeocoderModel> response) {
                if (response.code() == 200 && response.body() != null) {
                    GoogleGeocoderModel model = response.body();
                    List<Result> resultList = model.getResults();
                    if (resultList != null && resultList.size() > 0) {
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

    private void getYandexGeocoderResult(String address) {
        YandexApiClient.getApi().getYandexGeoCollection(address).enqueue(new Callback<YaResponseModel>() {
            @Override
            public void onResponse(Call<YaResponseModel> call, Response<YaResponseModel> response) {
                String latLng = "", address = "";
                if (response.code() == 200 && response.body() != null) {
                    YaResponse yaResponse = response.body().getResponse();
                    if (yaResponse != null) {
                        GeoObjectCollection geoObjectCollection = yaResponse.getGeoObjectCollection();
                        if (geoObjectCollection != null) {
                            List<FeatureMember> featureMembers = geoObjectCollection.getFeatureMember();
                            if (featureMembers != null && featureMembers.size() > 0) {
                                FeatureMember member = featureMembers.get(0);
                                if (member != null) {
                                    GeoObject geoObject = member.getGeoObject();
                                    if (geoObject != null) {
                                        MetaDataProperty metaDataProperty = geoObject.getMetaDataProperty();
                                        if (metaDataProperty != null) {
                                            GeocoderMetaData geocoderMetaData = metaDataProperty.getGeocoderMetaData();
                                            if (geocoderMetaData != null) {
                                                Address addr = geocoderMetaData.getAddress();
                                                if (addr != null) {
                                                    address = addr.getFormatted();
                                                }
                                            }
                                        }
                                        Point point = geoObject.getPoint();
                                        if (point != null) {
                                            latLng = point.getPos();

                                        }
                                    }
                                }
                            }
                        }
                    }

                    view.showYandexGeocoderResult(latLng, address);
                }
            }

            @Override
            public void onFailure(Call<YaResponseModel> call, Throwable t) {
                String ss = "";
            }
        });
    }
}
