package com.example.androidtest2app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ToDoList_RecyclerViewAdapter extends RecyclerView.Adapter<ToDoList_RecyclerViewAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<toDoListModel> toDoListModels;

    public ToDoList_RecyclerViewAdapter(Context context, ArrayList<toDoListModel> toDoListModels,
                                          RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.toDoListModels = toDoListModels;
        this.recyclerViewInterface = recyclerViewInterface;
    }
    @NonNull
    @Override
    public ToDoList_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.todolist_recycler_view_row, parent, false);

        return new ToDoList_RecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }


    @Override
    public void onBindViewHolder(@NonNull ToDoList_RecyclerViewAdapter.MyViewHolder holder, int position) {
        //Assigning values to recycler view
        holder.eventName.setText(toDoListModels.get(position).getEventName());
        holder.eventDate.setText(toDoListModels.get(position).getEventDate());
    }

    @Override
    public int getItemCount() {
        return toDoListModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //Grab views from recycler_view_row
        TextView eventName, eventDate;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            eventName = itemView.findViewById(R.id.eventName);
            eventDate = itemView.findViewById(R.id.eventDate);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (recyclerViewInterface != null) {
                        int pos = getBindingAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemLongClick(pos);
                        }
                    }

                    return true;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null) {
                        int pos = getBindingAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
