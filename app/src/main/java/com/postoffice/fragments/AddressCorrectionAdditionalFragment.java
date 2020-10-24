package com.postoffice.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.postoffice.R;
import com.postoffice.base.BaseFragment;

public class AddressCorrectionAdditionalFragment extends BaseFragment {


    @Override
    protected void bindView(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        setToolbar(toolbar, "Коррекция адреса");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address_correction_addition, container, false);

        return view;
    }
}
