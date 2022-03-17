package com.phonghtse140633.linlus_pg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.phonghtse140633.linlus_pg.utils.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class DeliveryConfirm extends AppCompatActivity {
    private final int MY_REQUEST_CODE_PERMISSION = 1000;
    private final int MY_RESULT_CODE_FILECHOOSER = 2000;
    private TextView textView;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_confirm);
        textView = findViewById(R.id.tvImage);
        imageView = findViewById(R.id.iv);
        askPermission();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == AppCompatActivity.RESULT_OK) {
            if (requestCode == MY_RESULT_CODE_FILECHOOSER) {
                String filepath=  FileUtils.getPath(this,data.getData());
                textView.setText("fileName: " + filepath);
                    imageView.setImageBitmap(BitmapFactory.decodeFile(filepath));
                    Log.i("Image: ", BitmapFactory.decodeFile(filepath)+"");
                Log.w("URI: ", data.toString());
            }
        }
    }

    private void askPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permission = ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            );
            if (permission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_REQUEST_CODE_PERMISSION);
                return;
            }else{
                showFileChooser();
            }
        }
    }

    private void showFileChooser() {
        String[] mimeTypes = (String[]) Arrays.asList("image/jpeg", "image/png", "image/gif", "application/pdf").toArray();
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            intent.setType(mimeTypes.length == 1 ? mimeTypes[0] : "*/*");
            if (mimeTypes.length == 0) {
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
            }
        } else {
            String mimeTypesStr = "";
            for (int i = 0; i < mimeTypes.length; i++) {
                mimeTypesStr += mimeTypes[i];
            }
            intent.setType(mimeTypesStr.substring(0, mimeTypesStr.length() - 1));
        }
        startActivityForResult(Intent.createChooser(intent, "choose file"), MY_RESULT_CODE_FILECHOOSER);
    }

}