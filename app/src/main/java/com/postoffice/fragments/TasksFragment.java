package com.postoffice.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.postoffice.R;
import com.postoffice.adapters.TasksAdapter;
import com.postoffice.base.BaseFragment;
import com.postoffice.fragments.edit.EditAddressFragment;
import com.postoffice.model.Tasks.Task;
import com.postoffice.model.Tasks.TaskResult;
import com.postoffice.mvp.TasksClickListener;
import com.postoffice.network.Api;
import com.postoffice.network.OurApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TasksFragment extends BaseFragment implements TasksClickListener {

    RecyclerView recyclerView;
//    ProgressBar progressBar;
    TasksAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_tasks, container, false);
        recyclerView = v.findViewById(R.id.recyclerView);
//        progressBar = v.findViewById(R.id.progressBar);

        showProgress();
        adapter = new TasksAdapter(getContext(), null, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        OurApiClient.getApi().getAllTasks().enqueue(new Callback<TaskResult>() {
            @Override
            public void onResponse(Call<TaskResult> call, Response<TaskResult> response) {
               hideProgress();
                if (response.isSuccessful()){
                    if (response.body().getSuccess()){
                        adapter.updateData(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<TaskResult> call, Throwable t) {
                hideProgress();
                Toast.makeText(getContext(),t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void bindView(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        setToolbar(toolbar, "Задачи");
    }

    @Override
    public void onTaskSelected(Task task) {
        TaskViewFragment fragment = new TaskViewFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("task", task);
        fragment.setArguments(bundle);
        getNavigationPresenter().pushFragment(fragment, true);
    }
}
