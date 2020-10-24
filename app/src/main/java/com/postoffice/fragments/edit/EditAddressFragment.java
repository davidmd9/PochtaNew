package com.postoffice.fragments.edit;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import com.postoffice.R;
import com.postoffice.base.BaseFragment;

public class EditAddressFragment extends BaseFragment implements EditFragmentContract.View {

    private EditFragmentPresenter presenter = new EditFragmentPresenter();

    private LinearLayout llResult;
    private LinearLayout llYandex;
    private LinearLayout llGoogle;

    private TextView tvGoogleCoordinates;
    private TextView tvGoogleFormattedAddress;

    private TextView tvYaCoordinate;
    private TextView tvYaAddress;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_addresss, container, false);
    }

    @Override
    protected void bindView(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        setToolbar(toolbar, "Коррекиця адреса");

        llResult = view.findViewById(R.id.llResults);

        EditText etSearch = view.findViewById(R.id.etSearch);
        ImageButton btnSearch = view.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etSearch.getText().length() > 3){
                    presenter.onSearchFieldChanged(etSearch.getText().toString());
                }
            }
        });


        tvGoogleCoordinates = view.findViewById(R.id.tvGoogleCoordinates);
        tvGoogleFormattedAddress = view.findViewById(R.id.tvGoogleAddress);

        tvYaCoordinate = view.findViewById(R.id.tvYaCoordinate);
        tvYaAddress = view.findViewById(R.id.tvYaAddress);

        llYandex = view.findViewById(R.id.llYandex);
        llGoogle = view.findViewById(R.id.llGoogle);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attach(this);
    }

    @Override
    public void onStop() {
        presenter.detach();
        super.onStop();
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

    }
}
