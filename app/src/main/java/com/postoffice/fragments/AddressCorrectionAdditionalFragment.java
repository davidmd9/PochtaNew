package com.postoffice.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.postoffice.R;
import com.postoffice.base.BaseFragment;

public class AddressCorrectionAdditionalFragment extends BaseFragment {

    private ImageButton buttonAddPhoto;
    private View mapsView;
    private ImageView photo;

    private EditText etNumber;

    private Button correctionButton;
    private Button addOrganitionButton;

    @Override
    protected void bindView(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        setToolbar(toolbar, "Коррекция адреса");

        buttonAddPhoto = view.findViewById(R.id.addPhotoBtn);
        mapsView = view.findViewById(R.id.mapView);
        photo = view.findViewById(R.id.photo);

        etNumber = view.findViewById(R.id.etNumber);

        correctionButton = view.findViewById(R.id.btnChange);
        addOrganitionButton = view.findViewById(R.id.btnAddOrganization);

        buttonAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address_correction_addition, container, false);

        return view;
    }
}
