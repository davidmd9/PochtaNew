package com.postoffice.fragments.edit;

import com.postoffice.mvp.BasePresenter;
import com.postoffice.mvp.BaseView;

public class EditFragmentContract {

    interface View extends BaseView {

        void showGoogleGeocoderResult(String latLng, String address);

        void showYandexGeocoderResult(String latLng, String address);

        void showOSMGeocoderResult(String latLng, String address);

        void showGoogleMarker(double lat, double lng);

        void showYandexMarker(double lat, double lng);

        void showOSMMarker(double lat, double lng);

        void showMap();

        void showResultsContainer();

    }

    abstract static class Presenter extends BasePresenter<View> {

        abstract void onSearch(String query);
    }
}
