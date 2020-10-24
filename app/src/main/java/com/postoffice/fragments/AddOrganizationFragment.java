package com.postoffice.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.postoffice.R;
import com.postoffice.base.BaseFragment;

public class AddOrganizationFragment extends BaseFragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_organization, container, false);

        return  view;
    }

    @Override
    protected void bindView(View view) {

    }


}
