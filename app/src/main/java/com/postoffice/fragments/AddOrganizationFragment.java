package com.postoffice.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.postoffice.R;
import com.postoffice.base.BaseFragment;
import com.postoffice.model.OrganizationDBModel;

public class AddOrganizationFragment extends BaseFragment {

    private EditText etName;
    private EditText timeFrom;
    private EditText timeTo;

    private Button btnSave;
    private OnSaveOrganization listener;

    public AddOrganizationFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_organization, container, false);

        return  view;
    }

    @Override
    protected void bindView(View view) {
        etName = view.findViewById(R.id.nameET);
        timeFrom = view.findViewById(R.id.time1);
        timeTo = view.findViewById(R.id.time2);
        btnSave = view.findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {

            if (listener != null) {
                OrganizationDBModel model = new OrganizationDBModel();
                model.setOrganizationName(etName.getText().toString());
                model.setWorkFrom(timeFrom.getText().toString());
                model.setWorkTo(timeTo.getText().toString());

                listener.onSave(model);
                getMainActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    public void setListener(OnSaveOrganization listener) {
        this.listener = listener;
    }

    interface OnSaveOrganization {
        public void onSave(OrganizationDBModel model);
    }

}
