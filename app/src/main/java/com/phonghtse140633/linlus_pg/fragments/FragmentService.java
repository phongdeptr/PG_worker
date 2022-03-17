package com.phonghtse140633.linlus_pg.fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phonghtse140633.linlus_pg.R;
import com.phonghtse140633.linlus_pg.adapters.ServiceAdapter;
import com.phonghtse140633.linlus_pg.model.PhotoService;
import com.phonghtse140633.linlus_pg.utils.Utils;

import java.util.List;

public class FragmentService extends Fragment {

    ImageButton btnAddService;
    RecyclerView lvServices;
    List<PhotoService> serviceList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, container, false);
        serviceList = Utils.getServices();
        lvServices = view.findViewById(R.id.lvServices);
        btnAddService = view.findViewById(R.id.btnAddService);
        ServiceAdapter adapter = new ServiceAdapter(view.getContext(), serviceList);
        lvServices.setAdapter(adapter);
        lvServices.setLayoutManager(new LinearLayoutManager(this.getContext()));

        btnAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddDialog();
            }
        });
        return view;
    }


    public void openAddDialog() {
        Dialog dialog = new Dialog(this.getContext());
        dialog.setContentView(R.layout.dialog_add_service);

        EditText etServiceName = dialog.findViewById(R.id.etServiceName);
        EditText etPrice = dialog.findViewById(R.id.etPrice);
        Spinner spUnit = dialog.findViewById(R.id.spUnit);
        Button btnChooseThumbnails = dialog.findViewById(R.id.btnChooseThumbnails);
        Button btnAddService = dialog.findViewById(R.id.btnAddService);
        Button btnCancelAddService = dialog.findViewById(R.id.btnCancelAddService);

        // Create array adapter for spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this.getContext(), R.array.unit_options, android.R.layout.simple_spinner_item);
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spUnit.setAdapter(staticAdapter);

        btnCancelAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


}
