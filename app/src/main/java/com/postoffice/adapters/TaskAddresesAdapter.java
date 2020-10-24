package com.postoffice.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.postoffice.R;
import com.postoffice.model.Tasks.Address;
import com.postoffice.model.Tasks.Task;
import com.postoffice.model.Tasks.TaskResult;
import com.postoffice.mvp.AdressClickListener;
import com.postoffice.mvp.TasksClickListener;

public class TaskAddresesAdapter extends RecyclerView.Adapter<TaskAddresesAdapter.TasksViewHolder> {

    Context context;
    Task items;
    AdressClickListener listener;

    public TaskAddresesAdapter(Context context, Task items, AdressClickListener listener){
        this.items = items;
        this.context = context;
        this.listener = listener;
    }

    public void updateData(Task newItems){
        items = newItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.task_item,null);
        TasksViewHolder viewHolder = new TasksViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TasksViewHolder holder, int position) {
        if(items!=null) {
            Address task = items.getAddresses().get(position);
            holder.textView.setText(task.getAddress()+" "+task.getLat()+" "+task.getLng());
        }
    }

    @Override
    public int getItemCount() {
        if(items==null){
            return 0;
        }
        return items.getAddresses().size();
    }

    class TasksViewHolder extends RecyclerView.ViewHolder{

        public TextView textView;

        public TasksViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null) {
                        listener.onAdressSelected(items.getAddresses().get(getPosition()));
                    }
                }
            });
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
