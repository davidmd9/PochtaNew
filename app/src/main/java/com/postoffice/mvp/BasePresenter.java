package com.postoffice.mvp;

import com.postoffice.mvp.BaseView;

public abstract class BasePresenter<V extends BaseView> {

    protected V view;
    public void attach(V view) {
        this.view = view;
    }

    public void detach() {
        view = null;
    }
}
