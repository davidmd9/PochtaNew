package com.postoffice.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.postoffice.R;
import com.postoffice.base.BaseFragment;

public class MainFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    protected void bindView(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        setToolbar(toolbar, "Главное меню");

        LinearLayout llEdit = view.findViewById(R.id.llEditAddress);
        llEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNavigationPresenter().pushFragment(new EditAddressFragment(), true);
            }
        });

        LinearLayout llTasks = view.findViewById(R.id.llTasks);
        llTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNavigationPresenter().pushFragment(new TasksFragment(), true);
            }
        });
    }
}
