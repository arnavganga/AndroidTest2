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

        holder.tvAssignmentName.setText(assignmentModels.get(position).getAssignmentName());
        holder.tvAssignmentTime.setText(assignmentModels.get(position).getAssignmentTime());
        holder.tvAssignmentProf.setText(assignmentModels.get(position).getAssignmentProf());

    }

    @Override
    public int getItemCount() {
        return assignmentModels.size();
    }

    public static assignment MyViewHolder extends RecyclerView.ViewHolder {
        //Grab views from recycler_view_row

        TextView tvAssignmentName, tvAssignmentTime, tvAssignmentProf;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            tvAssignmentName = itemView.findViewById(R.id.assignmentName);
            tvAssignmentTime = itemView.findViewById(R.id.assignmentTime);
            tvAssignmentProf = itemView.findViewById(R.id.assignmentProf);

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
