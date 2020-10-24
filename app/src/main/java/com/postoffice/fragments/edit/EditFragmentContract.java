package com.postoffice.fragments.edit;

import com.postoffice.mvp.BasePresenter;
import com.postoffice.mvp.BaseView;

public class EditFragmentContract {

    interface View extends BaseView {



    }

    abstract static class Presenter extends BasePresenter<View>{

        abstract void onSearchFieldChanged(String query);

        abstract void onGetGeocodeResults();

    }
}
