package com.phonghtse140633.linlus_pg.fragments;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phonghtse140633.linlus_pg.R;
import com.phonghtse140633.linlus_pg.adapters.ServiceAdapter;
import com.phonghtse140633.linlus_pg.model.PhotoService;
import com.phonghtse140633.linlus_pg.utils.FileUtils;
import com.phonghtse140633.linlus_pg.utils.Utils;

import java.util.List;

public class FragmentService extends Fragment {

    ImageButton btnAddService;
    Button addDialogButton;
    Button chooseThumb;
    RecyclerView lvServices;
    List<PhotoService> serviceList;
    ImageView view;
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
        chooseThumb = dialog.findViewById(R.id.btnChooseThumbnails);
        view = dialog.findViewById(R.id.ivThumb);
        Button btnCancelAddService = dialog.findViewById(R.id.btnCancelAddService);
        // Create array adapter for spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this.getContext(), R.array.unit_options, android.R.layout.simple_spinner_item);
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spUnit.setAdapter(staticAdapter);
        chooseThumb.setOnClickListener((view -> {
            ActivityCompat
                    .requestPermissions(
                            (Activity) this.getContext(),
                            new String[] { Manifest.permission.READ_EXTERNAL_STORAGE }, 1000);
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 100);
        }));
        btnCancelAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String path = FileUtils.getPath(this.getContext(), data.getData());
        view.setImageBitmap(BitmapFactory.decodeFile(path));
    }
}
