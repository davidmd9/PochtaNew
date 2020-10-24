package com.postoffice;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.postoffice.base.BaseFragment;

public class NavigationManager {

    private static FragmentTransaction transaction;
    private static BaseFragment currentFragment = null;

    public static BaseFragment getCurrentFragment() {
        return currentFragment;
    }

    public static void setCurrentFragment(BaseFragment currentFragment) {
        NavigationManager.currentFragment = currentFragment;
    }

    private static FragmentTransaction getTransaction(Activity activity) {
        return getFragmentManager(activity).beginTransaction();
    }

    private static FragmentManager getFragmentManager(Activity activity) {
        return ((AppCompatActivity) activity).getSupportFragmentManager();
    }

    public static void replaceFragment(Activity activity, BaseFragment fragment, int containerId, boolean isAdd) {
        Bundle bundle = fragment.getArguments();
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putBoolean("is_add", isAdd);
        fragment.setArguments(bundle);

        transaction = getTransaction(activity);
        if (isAdd) {
            transaction.replace(containerId, fragment, fragment.getClass().getSimpleName());
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        } else {
            transaction.replace(containerId, fragment, fragment.getClass().getSimpleName());
        }

        currentFragment = fragment;
        transaction.commitAllowingStateLoss();
    }

    public static void replaceFragment2(Activity activity, Fragment fragment, int containerId, boolean isAdd) {
        Bundle bundle = fragment.getArguments();
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putBoolean("is_add", isAdd);
        fragment.setArguments(bundle);

        transaction = getTransaction(activity);
        if (isAdd) {
            transaction.replace(containerId, fragment, fragment.getClass().getSimpleName());
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        } else {
            transaction.replace(containerId, fragment, fragment.getClass().getSimpleName());
        }
        transaction.commitAllowingStateLoss();
    }
}
