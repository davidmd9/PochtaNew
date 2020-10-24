package com.postoffice.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.postoffice.R;
import com.postoffice.model.Tasks.Task;
import com.postoffice.model.Tasks.TaskResult;
import com.postoffice.mvp.TasksClickListener;

import java.util.zip.Inflater;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder> {

    Context context;
    TaskResult items;
    TasksClickListener listener;

    public TasksAdapter(Context context, TaskResult items, TasksClickListener listener){
        this.items = items;
        this.context = context;
        this.listener = listener;
    }

    public void updateData(TaskResult newItems){
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
            Task task = items.getTasks().get(position);
            holder.textView.setText(task.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        if(items==null){
            return 0;
        }
        return items.getTasks().size();
    }

    class TasksViewHolder extends RecyclerView.ViewHolder{

        public TextView textView;

        public TasksViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null) {
                        listener.onTaskSelected(items.getTasks().get(getPosition()));
                    }
                }
            });
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
