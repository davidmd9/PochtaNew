package com.postoffice.fragments.edit;

import android.util.Log;

import com.postoffice.model.google.Geometry;
import com.postoffice.model.google.GoogleGeocoderModel;
import com.postoffice.model.google.Result;
import com.postoffice.model.osm.OSMResponse;
import com.postoffice.model.osm.OSMResponseModel;
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
import com.postoffice.network.OSMApiClient;
import com.postoffice.network.YandexApiClient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class EditFragmentPresenter extends EditFragmentContract.Presenter {

    @Override
    void onSearch(String query) {
        view.showProgress();
        view.hideKeyboard();
        subscribe(
                mergeRequests(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> {
                    view.hideProgress();
                    view.showMap();
                    view.showResultsContainer();
                }).subscribe(this::handleResult, this::handleError)
        );
    }

    private void handleError(Object o) {

    }

    private void handleResult(Object o) {
        if(o != null){
            if(o instanceof GoogleGeocoderModel){
                googleGeocoderResult((GoogleGeocoderModel)o);
            }

            if (o instanceof YaResponseModel){
                yandexGeocoderResult((YaResponseModel)o);
            }

            if(o instanceof List){
                OMSGeocoderResult((List<OSMResponse>)o);
            }
        }
    }

    private Observable mergeRequests(String query){
        return Observable.mergeDelayError(
                GoogleApiClient.getApi().getGoogleGeoCollection(query).subscribeOn(Schedulers.io()),
                YandexApiClient.getApi().getYandexGeoCollection(query).subscribeOn(Schedulers.io()),
                OSMApiClient.getApi().getOSMGeoCollection(query).subscribeOn(Schedulers.io())
        );
    }

    private void googleGeocoderResult(GoogleGeocoderModel response){
        List<Result> resultList = response.getResults();
        if (resultList != null && resultList.size() > 0) {
            Geometry geometry = resultList.get(0).getGeometry();
            view.showGoogleGeocoderResult(geometry.getLocation().getLat() + " " + geometry.getLocation().getLng(),
                    resultList.get(0).getFormatted_address());
            view.showGoogleMarker(geometry.getLocation().getLat(), geometry.getLocation().getLng());
        }
    }

    private void yandexGeocoderResult(YaResponseModel response){
        String latLng = "", address = "";
        YaResponse yaResponse = response.getResponse();
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
                                String[] separated = latLng.split(" ");
                                String lat = separated[0].trim();
                                String lng = separated[1].trim();
                                view.showYandexMarker(Double.parseDouble(lng), Double.parseDouble(lat));

                                view.showYandexGeocoderResult(lng + " " + lat, address);
                            }
                        }
                    }
                }
            }
        }

    }

    private void OMSGeocoderResult(List<OSMResponse> list){
        if (list != null && list.size() > 0) {
            OSMResponse osmResponse = list.get(0);
            if (osmResponse.getDisplay_name() != null
                    && osmResponse.getLat() != null
                    && osmResponse.getLon() != null) {
                view.showOSMGeocoderResult(osmResponse.getLat() + " " + osmResponse.getLon(), osmResponse.getDisplay_name());
                view.showOSMMarker(Double.parseDouble(osmResponse.getLat()), Double.parseDouble(osmResponse.getLon()));
            }
        }
    }
}
