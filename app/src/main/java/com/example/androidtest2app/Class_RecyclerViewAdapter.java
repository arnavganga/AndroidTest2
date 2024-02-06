package com.example.androidtest2app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Class_RecyclerViewAdapter extends RecyclerView.Adapter<Class_RecyclerViewAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<classModel> classModels;

    public Class_RecyclerViewAdapter(Context context, ArrayList<classModel> classModels,
                                    RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.classModels = classModels;
        this.recyclerViewInterface = recyclerViewInterface;
    }
    @NonNull
    @Override
    public Class_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.class_recycler_view_row, parent, false);

        return new Class_RecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }
    

    @Override
    public void onBindViewHolder(@NonNull Class_RecyclerViewAdapter.MyViewHolder holder, int position) {
        //Assigning values to recycler view

        holder.tvClassName.setText(classModels.get(position).getClassName());
        holder.tvClassTime.setText(classModels.get(position).getClassTime());
        holder.tvClassProf.setText(classModels.get(position).getClassProf());

    }

    @Override
    public int getItemCount() {
        return classModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //Grab views from recycler_view_row

        TextView tvClassName, tvClassTime, tvClassProf;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            tvClassName = itemView.findViewById(R.id.className);
            tvClassTime = itemView.findViewById(R.id.classTime);
            tvClassProf = itemView.findViewById(R.id.classProf);

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
