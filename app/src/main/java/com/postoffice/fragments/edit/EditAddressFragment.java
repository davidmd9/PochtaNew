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
    private TextView tvGoogleCoordinates;
    private TextView tvGoogleFormattedAddress;

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

        ImageButton btnSearch = view.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        EditText etSearch = view.findViewById(R.id.etSearch);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length() > 3){
                    presenter.onSearchFieldChanged(s.toString());
                }
            }
        });

        tvGoogleCoordinates = view.findViewById(R.id.tvGoogleCoordinates);
        tvGoogleFormattedAddress = view.findViewById(R.id.tvGoogleAddress);
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
    }

    @Override
    public void showYandexGeocoderResult(String latLng, String address) {

    }

    @Override
    public void showOSMGeocoderResult(String latLng, String address) {

    }
}
