package com.postoffice.main;

import com.postoffice.FragmentNavigation;
import com.postoffice.base.BaseFragment;
import com.postoffice.mvp.BasePresenter;

public class MainActivityPresenter extends BasePresenter<MainActivityContract.View> implements FragmentNavigation.Presenter {

    @Override
    public void pushFragment(BaseFragment fragment, boolean isAdd) {
        view.pushFragment(fragment, isAdd);
    }
}