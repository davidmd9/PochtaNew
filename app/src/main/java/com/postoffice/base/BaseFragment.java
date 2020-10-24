package com.postoffice.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.postoffice.mvp.BaseView;
import com.postoffice.FragmentNavigation;

public abstract class BaseFragment extends Fragment implements BaseView, FragmentNavigation.View {

    private BaseActivity mainActivity;
    private FragmentNavigation.Presenter navigationPresenter;

    protected abstract void bindView(View view);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView(view);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.mainActivity = (BaseActivity) context;
        }
    }

    @Override
    public void attachNavigationPresenter(FragmentNavigation.Presenter presenter) {
        navigationPresenter = presenter;
    }

    @Override
    public void showProgress() {
        mainActivity.showProgress();
    }

    @Override
    public void hideProgress() {
        mainActivity.hideProgress();
    }

    @Override
    public void hideKeyboard() {
        mainActivity.hideKeyboard();
    }

    @Override
    public void showMessage(String message) {
        mainActivity.showMessage(message);
    }

    @Override
    public void showMessage(int resId) {
        mainActivity.showMessage(resId);
    }

    @Override
    public void showErrorMessage(String message) {
        mainActivity.showErrorMessage(message);
    }

    @Override
    public void showErrorMessage(int resId) {
        mainActivity.showErrorMessage(resId);
    }

    public BaseActivity getMainActivity() {
        return mainActivity;
    }

    public FragmentNavigation.Presenter getNavigationPresenter() {
        return navigationPresenter;
    }

}
