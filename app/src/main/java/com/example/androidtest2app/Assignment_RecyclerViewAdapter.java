package com.example.androidtest2app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Assignment_RecyclerViewAdapter extends RecyclerView.Adapter<Assignment_RecyclerViewAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<assignmentModel> assignmentModels;

    public Assignment_RecyclerViewAdapter(Context context, ArrayList<assignmentModel> assignmentModels,
                                     RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.assignmentModels = assignmentModels;
        this.recyclerViewInterface = recyclerViewInterface;
    }
    @NonNull
    @Override
    public Assignment_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.assignment_recycler_view_row, parent, false);

        return new Assignment_RecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }


    @Override
    public void onBindViewHolder(@NonNull Assignment_RecyclerViewAdapter.MyViewHolder holder, int position) {
        //Assigning values to recycler view

        holder.tvAssignmentTitle.setText(assignmentModels.get(position).getAssignmentTitle());
        holder.tvAssignmentDueDate.setText(assignmentModels.get(position).getAssignmentDueDate());
        holder.tvAssignmentClass.setText(assignmentModels.get(position).getAssignmentClass());

    }

    @Override
    public int getItemCount() {
        return assignmentModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //Grab views from recycler_view_row

        TextView tvAssignmentTitle, tvAssignmentDueDate, tvAssignmentClass;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            tvAssignmentTitle = itemView.findViewById(R.id.assignmentTitle);
            tvAssignmentDueDate = itemView.findViewById(R.id.assignmentDueDate);
            tvAssignmentClass = itemView.findViewById(R.id.assignmentClass);

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
