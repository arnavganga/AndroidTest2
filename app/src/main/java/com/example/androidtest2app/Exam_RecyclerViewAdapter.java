package com.example.androidtest2app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Exam_RecyclerViewAdapter extends RecyclerView.Adapter<Exam_RecyclerViewAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<examsModel> examsModels;

    public Exam_RecyclerViewAdapter(Context context, ArrayList<examsModel> examsModels,
                                    RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.examsModels = examsModels;
        this.recyclerViewInterface = recyclerViewInterface;
    }
    @NonNull
    @Override
    public Exam_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);

        return new Exam_RecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull Exam_RecyclerViewAdapter.MyViewHolder holder, int position) {
        //Assigning values to recycler view

        holder.tvExamName.setText(examsModels.get(position).getExamName());
        holder.tvExamDate.setText(examsModels.get(position).getExamDate());
        holder.tvExamTime.setText(examsModels.get(position).getExamTime());
        holder.tvExamLoc.setText(examsModels.get(position).getExamLoc());

    }

    @Override
    public int getItemCount() {
        return examsModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //Grab views from recycler_view_row

        TextView tvExamName, tvExamDate, tvExamTime, tvExamLoc;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            tvExamName = itemView.findViewById(R.id.examName);
            tvExamDate = itemView.findViewById(R.id.examDate);
            tvExamTime = itemView.findViewById(R.id.examTime);
            tvExamLoc = itemView.findViewById(R.id.examLoc);

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
