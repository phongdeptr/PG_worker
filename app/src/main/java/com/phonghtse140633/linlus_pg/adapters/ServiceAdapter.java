package com.phonghtse140633.linlus_pg.adapters;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.phonghtse140633.linlus_pg.R;
import com.phonghtse140633.linlus_pg.model.PhotoService;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {

    private Context context;
    private List<PhotoService> serviceList;

    public ServiceAdapter(Context context, List<PhotoService> serviceList) {
        this.context = context;
        this.serviceList = serviceList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_service, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PhotoService service = serviceList.get(position);

        holder.ivServiceAvatar.setImageResource(service.getRepresentativeImg());
        holder.tvServiceName.setText(service.getName());
        holder.tvServicePrice.setText("Price: " + service.getPrice());
        holder.tvUnit.setText("Unit: " + "Hour");

    }

    @Override
    public int getItemCount() {
        return serviceList == null ? 0 : serviceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivServiceAvatar;
        public TextView tvServiceName;
        public TextView tvServicePrice;
        public TextView tvUnit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivServiceAvatar = itemView.findViewById(R.id.ivServiceAvatar);
            tvServiceName = itemView.findViewById(R.id.tvServiceName);
            tvServicePrice = itemView.findViewById(R.id.tvServicePrice);
            tvUnit = itemView.findViewById(R.id.tvUnit);

        }
    }
}
