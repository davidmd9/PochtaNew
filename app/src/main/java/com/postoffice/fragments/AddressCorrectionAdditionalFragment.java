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

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.postoffice.R;
import com.postoffice.base.BaseFragment;
import com.postoffice.model.AddressDBModel;

public class AddressCorrectionAdditionalFragment extends BaseFragment {

    private AddressDBModel model;
    private ImageButton buttonAddPhoto;
    private ImageView photo;
    private SupportMapFragment mapView;

    private EditText etNumber;

    private Button correctionButton;
    private Button addOrganitionButton;
    FirebaseDatabase database;

    public AddressCorrectionAdditionalFragment(AddressDBModel model) {
        this.model = model;
    }

    @Override
    protected void bindView(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        setToolbar(toolbar, "Коррекция адреса");

        database = FirebaseDatabase.getInstance();
        buttonAddPhoto = view.findViewById(R.id.addPhotoBtn);
        mapView = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapView);
        photo = view.findViewById(R.id.photo);

        etNumber = view.findViewById(R.id.etNumber);

        correctionButton = view.findViewById(R.id.btnChange);
        addOrganitionButton = view.findViewById(R.id.btnAddOrganization);

        buttonAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                CameraUpdate camUpdate = CameraUpdateFactory.newLatLng(new LatLng(44, 44));
                googleMap.moveCamera(camUpdate);
            }
        });

        DatabaseReference changes = database.getReference("changes");
//        changes.a



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address_correction_addition, container, false);

        return view;
    }
}
