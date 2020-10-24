package com.postoffice.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.postoffice.mvp.BaseView;

import cn.pedant.SweetAlert.SweetAlertDialog;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private SweetAlertDialog progressDialog;
    private SweetAlertDialog messageDialog;

    protected abstract void init(Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
    }

    @Override
    public void showProgress() {
        hideProgress();
        progressDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        progressDialog.getProgressHelper().setBarColor(Color.parseColor("#1C67AE"));
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null)
            progressDialog.hide();
    }

    @Override
    public void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void showMessage(String message) {
        if (messageDialog != null) messageDialog.dismiss();
        if (message != null) {
            messageDialog = new SweetAlertDialog(this);
            messageDialog.setContentText(message);
            messageDialog.show();
//            messageDialog.getButton(SweetAlertDialog.BUTTON_CONFIRM).setBackground(getDrawable(R.drawable.button_bg_dark_red));
        }
    }

    @Override
    public void showMessage(int resId) {
        if (messageDialog != null) messageDialog.dismiss();
        messageDialog = new SweetAlertDialog(this);
        messageDialog.setContentText(getString(resId));
        messageDialog.show();
//        messageDialog.getButton(SweetAlertDialog.BUTTON_CONFIRM).setBackground(getDrawable(R.drawable.button_bg_dark_red));
    }

    @Override
    public void showErrorMessage(String message) {
        if (messageDialog != null) messageDialog.dismiss();
        if (message != null) {
            messageDialog = new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE);
            messageDialog.setContentText(message);
            messageDialog.show();
//            messageDialog.getButton(SweetAlertDialog.BUTTON_CONFIRM).setBackground(getDrawable(R.drawable.button_bg_dark_red));
        }
    }

    @Override
    public void showErrorMessage(int resId) {
        if (messageDialog != null) messageDialog.dismiss();
        messageDialog = new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE);
        messageDialog.setContentText(getString(resId));
        messageDialog.show();
//        messageDialog.getButton(SweetAlertDialog.BUTTON_CONFIRM).setBackground(getDrawable(R.drawable.button_bg_dark_red));
    }

    @Override
    protected void onDestroy() {
        if (progressDialog != null) progressDialog.dismiss();
        if (messageDialog != null) messageDialog.dismiss();
        super.onDestroy();
    }
}
