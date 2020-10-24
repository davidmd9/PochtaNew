package com.postoffice.fragments.edit;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.postoffice.R;
import com.postoffice.base.BaseFragment;

public class EditAddressFragment extends BaseFragment implements EditFragmentContract.View {

    private EditFragmentPresenter presenter = new EditFragmentPresenter();

    private LinearLayout llYandex;
    private LinearLayout llGoogle;
    private LinearLayout llOSM;

    private TextView tvGoogleCoordinates;
    private TextView tvGoogleFormattedAddress;

    private TextView tvYaCoordinate;
    private TextView tvYaAddress;

    private TextView tvOSMCoordinates;
    private TextView tvOSMAddress;

    private GoogleMap map;
    private SupportMapFragment mapFragment;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_addresss, container, false);
    }

    @Override
    protected void bindView(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        setToolbar(toolbar, "Коррекция адреса");

        EditText etSearch = view.findViewById(R.id.etSearch);
        ImageButton btnSearch = view.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etSearch.getText().length() > 3) {
                    presenter.onSearch(etSearch.getText().toString());
                }
            }
        });


        tvGoogleCoordinates = view.findViewById(R.id.tvGoogleCoordinates);
        tvGoogleFormattedAddress = view.findViewById(R.id.tvGoogleAddress);

        tvYaCoordinate = view.findViewById(R.id.tvYaCoordinate);
        tvYaAddress = view.findViewById(R.id.tvYaAddress);

        tvOSMCoordinates = view.findViewById(R.id.tvOSMCoordinate);
        tvOSMAddress = view.findViewById(R.id.tvOSMAddress);

        llYandex = view.findViewById(R.id.llYandex);
        llGoogle = view.findViewById(R.id.llGoogle);
        llOSM = view.findViewById(R.id.llOSM);

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getView().setVisibility(View.GONE);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mapFragment.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attach(this);
    }

    private BitmapDescriptor bitmapDescriptorFromVector(int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(getMainActivity(), vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    @Override
    public void onStop() {
        presenter.detach();
        super.onStop();
        mapFragment.onStop();
    }

    @Override
    public void showGoogleGeocoderResult(String latLng, String address) {
        tvGoogleCoordinates.setText(latLng);
        tvGoogleFormattedAddress.setText(address);

        llGoogle.setVisibility(View.VISIBLE);
        llGoogle.animate()
                .alpha(1f)
                .setDuration(300)
                .setListener(null);
    }

    @Override
    public void showYandexGeocoderResult(String latLng, String address) {
        tvYaCoordinate.setText(latLng);
        tvYaAddress.setText(address);

        llYandex.setVisibility(View.VISIBLE);
        llYandex.animate()
                .alpha(1f)
                .setDuration(300)
                .setListener(null);
    }

    @Override
    public void showOSMGeocoderResult(String latLng, String address) {
        tvOSMCoordinates.setText(latLng);
        tvOSMAddress.setText(address);

        llOSM.setVisibility(View.VISIBLE);
        llOSM.animate()
                .alpha(1f)
                .setDuration(300)
                .setListener(null);
    }

    @Override
    public void showGoogleMarker(double lat, double lng) {
        if (map != null) {
            map.addMarker(new MarkerOptions()
                    .icon(bitmapDescriptorFromVector(R.mipmap.marker_google))
                    .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                    .position(new LatLng(lat, lng)));
        }
    }

    @Override
    public void showYandexMarker(double lat, double lng) {
        if (map != null)
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(lat, lng), 20));
            map.addMarker(new MarkerOptions()
                    .icon(bitmapDescriptorFromVector(R.mipmap.marker_yandex))
                    .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                    .position(new LatLng(lat, lng)));
    }

    @Override
    public void showOSMMarker(double lat, double lng) {
        if (map != null)
            map.addMarker(new MarkerOptions()
                    .icon(bitmapDescriptorFromVector(R.mipmap.marker_osm))
                    .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                    .position(new LatLng(lat, lng)));
    }

    @Override
    public void showMap() {
        if(mapFragment != null && map != null){
            mapFragment.getView().setVisibility(View.VISIBLE);
            mapFragment.getView().animate()
                    .alpha(1f)
                    .setDuration(300)
                    .setListener(null);

        }
    }
}
