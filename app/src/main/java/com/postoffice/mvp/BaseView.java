package com.postoffice.mvp;

public interface BaseView {
    void showProgress();

    void hideProgress();

    void hideKeyboard();

    void showMessage(String message);

    void showMessage(int resId);

    void showErrorMessage(String message);

    void showErrorMessage(int resId);
}
