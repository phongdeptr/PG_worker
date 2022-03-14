package com.phonghtse140633.linlus_pg.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.phonghtse140633.linlus_pg.R;

import java.util.ArrayList;
import java.util.List;

public class CalendarCellAdapter extends RecyclerView.Adapter<CalendarCellAdapter.ViewHolder> {
    List<Integer> days = new ArrayList<>();

    public CalendarCellAdapter(List<Integer> days) {
        this.days = days;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
//        if(days.size() > 15) //month view
//            layoutParams.height = (int) (parent.getHeight() * 0.166666666);
//        else // week view
//            layoutParams.height = parent.getHeight();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cellDayText.setText(days.get(position) + "");
        if (position == 2) {
            holder.parentView.setBackgroundColor(Color.LTGRAY);
        }
    }

    @Override
    public int getItemCount() {
        return days.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        View parentView;
        TextView cellDayText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cellDayText = itemView.findViewById(R.id.cellDayText);
            parentView = itemView.findViewById(R.id.parentView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    parentView.setBackgroundColor(Color.LTGRAY);
                }
            });
        }
    }
}
