package com.postoffice.fragments;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.postoffice.R;
import com.postoffice.base.BaseFragment;
import com.postoffice.model.AddressDBModel;
import com.postoffice.model.OrganizationDBModel;

public class AddressCorrectionAdditionalFragment extends BaseFragment {

    private AddressDBModel model;
    private ImageButton buttonAddPhoto;
    private ImageView photo;
    private SupportMapFragment mapView;

    private EditText etNumber;

    private Button correctionButton;
    private Button addOrganitionButton;
    private Button btnChange;
    FirebaseDatabase database;

    private DataSnapshot snapshotChanges;

    public AddressCorrectionAdditionalFragment(AddressDBModel model) {
        this.model = model;
    }

    @Override
    protected void bindView(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        setToolbar(toolbar, "Коррекция адреса");
        FirebaseApp.initializeApp(getActivity());

        database = FirebaseDatabase.getInstance();
        mapView = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapView);
        photo = view.findViewById(R.id.photo);

        buttonAddPhoto = view.findViewById(R.id.addPhotoBtn);
        btnChange = view.findViewById(R.id.btnChange);

        etNumber = view.findViewById(R.id.etNumber);

        correctionButton = view.findViewById(R.id.btnChange);
        addOrganitionButton = view.findViewById(R.id.btnAddOrganization);

        database.getReference("changes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                snapshotChanges = snapshot;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        buttonAddPhoto.setOnClickListener(v -> {

        });

        btnChange.setOnClickListener(v -> {
            model.setPorchNumber(Integer.parseInt(etNumber.getText().toString()));
            String id = model.getLat() + " " + model.getLng();
            id = id.replace(".", ",");
            snapshotChanges.child(id).getRef().setValue(model);
            getMainActivity().getSupportFragmentManager().popBackStack();
            getMainActivity().getSupportFragmentManager().popBackStack();
        });

        addOrganitionButton.setOnClickListener(v -> {
            AddOrganizationFragment fragment = new AddOrganizationFragment();
            fragment.setListener(new AddOrganizationFragment.OnSaveOrganization() {
                @Override
                public void onSave(OrganizationDBModel model) {
                    AddressCorrectionAdditionalFragment.this.model.getAddress().add(model);
                }
            });

            getNavigationPresenter().pushFragment(fragment, true);
        });

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                LatLng newLatLng = new LatLng(Double.parseDouble(
                        model.getLat()), Double.parseDouble(model.getLng()));
                CameraUpdate camUpdate = CameraUpdateFactory.newLatLngZoom(newLatLng, 16);
                googleMap.moveCamera(camUpdate);

                googleMap.addMarker(new MarkerOptions()
                        .icon(bitmapDescriptorFromVector(R.drawable.ic_pin_icon))
                        .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                        .position(newLatLng));

            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address_correction_addition, container, false);

        return view;
    }


    private BitmapDescriptor bitmapDescriptorFromVector(int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(getMainActivity(), vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}
