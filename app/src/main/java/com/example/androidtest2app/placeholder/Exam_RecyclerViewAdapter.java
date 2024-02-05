package com.example.androidtest2app.placeholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidtest2app.R;
import com.example.androidtest2app.examsModel;

import java.util.ArrayList;

public class Exam_RecyclerViewAdapter extends RecyclerView.Adapter<Exam_RecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<examsModel> examsModels;

    public Exam_RecyclerViewAdapter(Context context, ArrayList<examsModel> examsModels) {
        this.context = context;
        this.examsModels = examsModels;
    }
    @NonNull
    @Override
    public Exam_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);

        return new Exam_RecyclerViewAdapter.MyViewHolder(view);
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

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvExamName = itemView.findViewById(R.id.examName);
            tvExamDate = itemView.findViewById(R.id.examDate);
            tvExamTime = itemView.findViewById(R.id.examTime);
            tvExamLoc = itemView.findViewById(R.id.examLoc);
        }
    }
}
