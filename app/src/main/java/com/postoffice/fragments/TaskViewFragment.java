package com.postoffice.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.postoffice.R;
import com.postoffice.adapters.TaskAddresesAdapter;
import com.postoffice.base.BaseFragment;
import com.postoffice.model.Tasks.Address;
import com.postoffice.model.Tasks.Task;
import com.postoffice.mvp.AdressClickListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaskViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskViewFragment extends BaseFragment implements AdressClickListener {

    Task task;

    RecyclerView recyclerView;
    TextView textView, tvHeader;
    TaskAddresesAdapter adapter;
    public TaskViewFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static TaskViewFragment newInstance(String param1, String param2) {
        TaskViewFragment fragment = new TaskViewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ((task = (Task) getArguments().getSerializable("task"))!=null){
            Toast.makeText(getContext(),task.getText(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(),"Error passing fragment task", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_task_view, container, false);
        recyclerView = v.findViewById(R.id.recyclerView);
        textView = v.findViewById(R.id.textView);
        tvHeader = v.findViewById(R.id.textViewHeader);
        adapter = new TaskAddresesAdapter(getContext(), task, this);
        textView.setText(task.getText());
        tvHeader.setText(task.getTitle());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return v;
    }

    @Override
    protected void bindView(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        setToolbar(toolbar, "Задачи");
    }

    @Override
    public void onAdressSelected(Address task) {
        Toast.makeText(getContext(),task.getAddress(),Toast.LENGTH_LONG).show();

        TaskAdressFragment fragment = new TaskAdressFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("addr", task);
        fragment.setArguments(bundle);
        getNavigationPresenter().pushFragment(fragment, true);

    }
}