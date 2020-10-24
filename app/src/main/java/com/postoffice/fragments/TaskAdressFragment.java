package com.postoffice.fragments;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.postoffice.R;
import com.postoffice.base.BaseFragment;
import com.postoffice.model.Tasks.Address;
import com.postoffice.model.Tasks.Task;
import com.postoffice.model.Tasks.TaskResult;
import com.postoffice.network.ApiClient;
import com.postoffice.network.OurApiClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class TaskAdressFragment extends BaseFragment implements OnMapReadyCallback {

    Address address;
    Task task;
    TextView txLatLng;
    ImageButton btnPhoto;
    ImageView ivImage;
    EditText etText;
    Button btnSend;
    GoogleMap googleMap;

    String currentPhotoPath;
    public static final int REQUEST_IMAGE_CAPTURE = 1213;
    public static final int REQUEST_TAKE_PHOTO = 1212;

    public TaskAdressFragment() {
        // Required empty public constructor
    }



    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
            // display error state to the user
        }
    }
    Bitmap imageBitmap;
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            ivImage.setImageBitmap(imageBitmap);
        }
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            address = (Address) getArguments().getSerializable("addr");
            task = (Task) getArguments().getSerializable("task");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_task_adress, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        txLatLng = v.findViewById(R.id.txLatLng);
        ivImage = v.findViewById(R.id.ivImage);
        btnPhoto = v.findViewById(R.id.btnPhoto);
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(getContext(),
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CAMERA},
                            REQUEST_TAKE_PHOTO);
                } else {
                    dispatchTakePictureIntent();
                }

            }
        });
        etText = v.findViewById(R.id.etText);
        btnSend = v.findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri outputFileUri = null;
                if(imageBitmap!=null) {
                    File img = new File(getContext().getFilesDir(), "image_" + Math.round(Math.random() * 1000) + ".jpg");
                    outputFileUri = Uri.fromFile(img);
                    try {
                        FileOutputStream fOut = new FileOutputStream(outputFileUri.getPath());
                        imageBitmap.compress(Bitmap.CompressFormat.PNG, 80, fOut);
                        fOut.flush();
                        fOut.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                MultipartBody.Part imagenPerfil = null;
                if(outputFileUri.getPath()!=null){
                    File file = new File(outputFileUri.getPath());
                    RequestBody requestFile =
                            RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    imagenPerfil = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
                }
                RequestBody requestLat =
                        RequestBody.create(
                                MediaType.parse("multipart/form-data"), String.format("%.5f", googleMap.getCameraPosition().target.latitude));
                RequestBody requestLom =
                        RequestBody.create(
                                MediaType.parse("multipart/form-data"), String.format("%.5f", googleMap.getCameraPosition().target.longitude));
                RequestBody task_id =
                        RequestBody.create(
                                MediaType.parse("multipart/form-data"), task.getID()+"");
                RequestBody addr_id =
                        RequestBody.create(
                                MediaType.parse("multipart/form-data"), address.getID()+"");

                RequestBody text =
                        RequestBody.create(
                                MediaType.parse("multipart/form-data"), etText.getText().toString());



                OurApiClient.getApi().sendTask(requestLat, requestLom,task_id,addr_id,text,imagenPerfil).enqueue(new Callback<TaskResult>() {
                    @Override
                    public void onResponse(Call<TaskResult> call, Response<TaskResult> response) {
                        if (response.isSuccessful()){
                            getActivity().onBackPressed();
                            Toast.makeText(getContext(),"Отправлено!!!", Toast.LENGTH_LONG).show();
                        } else {
                            String s = null;
                            try {
                                s = new String(response.errorBody().bytes());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Log.d("Error", "onResponse: "+s);
                        }
                    }

                    @Override
                    public void onFailure(Call<TaskResult> call, Throwable t) {
                        Toast.makeText(getContext(),t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setAllGesturesEnabled(true);
        googleMap.setMinZoomPreference(14.0f);
        googleMap.setMaxZoomPreference(20.0f);
        googleMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {

                txLatLng.setText( String.format("%.5f", googleMap.getCameraPosition().target.latitude) +" "+String.format("%.5f", googleMap.getCameraPosition().target.longitude));
            }
        });
        LatLng latlng = new LatLng(Double.parseDouble(address.getLat()), Double.parseDouble(address.getLng()));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 16));
    }

    @Override
    protected void bindView(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        setToolbar(toolbar, "");
    }
}