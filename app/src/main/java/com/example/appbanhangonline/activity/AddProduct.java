package com.example.appbanhangonline.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.adapter.ImageAdapter;

import java.util.ArrayList;

public class AddProduct extends AppCompatActivity {

    private TextView txt_title;
    private ImageView img_back;

    private ImageView btnImage;
    private RecyclerView recyclerView;
    private ArrayList<String> listImage;
    private ImageAdapter imageAdapter;
     private String[] projection = {MediaStore.MediaColumns.DATA};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_product);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        init();
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txt_title.setText("Thêm sản phẩm");
        //
        if(ContextCompat.checkSelfPermission(AddProduct.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(AddProduct.this, new String[]{
                    Manifest.permission.CAMERA
            },0);
        }

        recyclerView = findViewById(R.id.recyclerView);
        btnImage = findViewById(R.id.add_image);
        listImage = new ArrayList<>();


        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        setAdapter();
        Spinner spinner = (Spinner) findViewById(R.id.spnNganh);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
    }
    private void setAdapter(){


        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        imageAdapter = new ImageAdapter(this,listImage);

        imageAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(imageAdapter);
    }
    private void selectImage(){
        final CharSequence[] options = { "Chụp ảnh", "Thư viện","Hủy" };

        AlertDialog.Builder builder = new AlertDialog.Builder(AddProduct.this);
        builder.setTitle("Vui lòng chọn hình ảnh");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Chụp ảnh")) {
                    Intent intent;
                    intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 0);


                } else if (options[item].equals("Thư viện")) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);

                    startActivityForResult(Intent.createChooser(intent,"Chọn hình ảnh"),1);

                } else if (options[item].equals("Hủy bỏ")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    public String getRealPathFromURI (Uri contentUri) {
        String path = null;
        String[] proj = { MediaStore.MediaColumns.DATA };
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode){
                case 0:
                    Uri  imageUri  = data.getData();
                    getImageFilePath(imageUri);
                    break;
                case 1:
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                        for (int i = 0; i < mClipData.getItemCount(); i++) {
                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            getImageFilePath(uri);
                        }
                    } else if (data.getData() != null) {
                        Uri uri = data.getData();
                        getImageFilePath(uri);
                    }
                    break;

            }
        }
    }
    private void addImage(String filePath){
        listImage.add(filePath);
        imageAdapter.notifyDataSetChanged();
    }
    public void getImageFilePath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String absolutePathOfImage = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA));
                if (absolutePathOfImage != null) {
                    addImage(absolutePathOfImage);
                } else {
                    addImage(String.valueOf(uri));
                }
            }
        }
    }
    public void checkImage(String filePath) {
        addImage(filePath);
    }
    private void init(){
        txt_title = findViewById(R.id.title);
        img_back = findViewById(R.id.icon_back);
    }

}