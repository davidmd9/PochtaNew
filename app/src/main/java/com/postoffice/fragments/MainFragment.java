package com.postoffice.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.postoffice.R;
import com.postoffice.base.BaseFragment;
import com.postoffice.fragments.edit.EditAddressFragment;
import com.postoffice.model.AddressDBModel;
import com.postoffice.model.OrganizationDBModel;

import java.util.ArrayList;
import java.util.List;

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

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference rootRef = database.getReference();

        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot i: snapshot.child("changes").getChildren()) {
                    AddressDBModel model = new AddressDBModel();
                    model.setLat("44");
                    model.setLng("45");
                    model.setAdditionalInfo("add info");
                    model.setPorchNumber(4);
                    ArrayList<OrganizationDBModel> org = new ArrayList();
                    OrganizationDBModel orgModel = new OrganizationDBModel();
                    orgModel.setOrganizationName("Org");
                    orgModel.setWorkFrom("1");
                    orgModel.setWorkTo("10");
                    org.add(orgModel);
                    model.setAddress(org);
                    i.getRef().setValue(model);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("1", "msg");
            }
        });


        Log.i("12", "msg");
//        rootRef.setValue("changes");

    }
}
