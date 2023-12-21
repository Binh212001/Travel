package com.example.travelapp.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.travelapp.R;
import com.example.travelapp.Utils.RealPathUtil;
import com.example.travelapp.model.Destination;
import com.example.travelapp.model.Restaurant;
import com.example.travelapp.network.RestApiService;
import com.example.travelapp.network.RetrofitInstance;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddRst extends AppCompatActivity {

    ImageView btnArrowBack ;
    Button btnBack , buttonAddImageRst;



    TextView editTextRstName, textRstDescription, editTextRstAddress ;


    ImageView imageViewRst;
    String name ,desc,address , imageName ;
    Uri imageUri;
    private static final int PICK_IMAGE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_restaurant);
        onClickBackAddLocationArr();
        onClickBackAddLocationBtn();
        onClickAddImageLocationBtn();
    }

    public  void  saveDataRst(View v){
        editTextRstName = findViewById(R.id.editTextRstName);
        textRstDescription = findViewById(R.id.editTextRstDesc);
        editTextRstAddress = findViewById(R.id.editTextRstAddress);

        imageViewRst = findViewById(R.id.imageViewRst);

        name =  editTextRstName.getText().toString();
        desc =  textRstDescription.getText().toString();
        address =  editTextRstAddress.getText().toString();



        if(!name.isEmpty()&&! desc.isEmpty()&&!address.isEmpty()){
            RequestBody namePart = RequestBody.create(MediaType.parse("text/plain"), name);
            RequestBody descPart = RequestBody.create(MediaType.parse("text/plain"), desc);
            RequestBody addressPart = RequestBody.create(MediaType.parse("text/plain"), address);

            String imageRealUri = RealPathUtil.getRealPath(this , imageUri);
            File avatarFile = new File(imageRealUri);
            RequestBody avatarRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), avatarFile);
            MultipartBody.Part imagePart = MultipartBody.Part.createFormData("image", avatarFile.getName(), avatarRequestBody);

            RestApiService apiService = RetrofitInstance.getApiService();
            Call<Restaurant.Data> call = apiService.createRst(namePart,descPart,addressPart,imagePart);

            call.enqueue(new Callback<Restaurant.Data>() {
                @Override
                public void onResponse(Call<Restaurant.Data> call, Response<Restaurant.Data> response) {
                    if ( response.body() != null) {
                        Toast.makeText(AddRst.this, "Thêm địa điểm thành công", Toast.LENGTH_LONG).show();
                        closeActivity();
                    } else {
                        Toast.makeText(AddRst.this, "Vui lòng điền đầy dủ thông tin", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Restaurant.Data> call, Throwable t) {
                    // Handle failure
                    Toast.makeText(AddRst.this, "Vui lòng điền đầy dủ thông tin shfgdhs", Toast.LENGTH_LONG).show();

                }
            });

        }else {
            Toast.makeText(AddRst.this, "Vui lòng điền đầy dủ thông tin", Toast.LENGTH_LONG).show();
        }
    }



    private void onClickBackAddLocationArr() {
        btnArrowBack = findViewById(R.id.RstArrowBack);
        btnArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeActivity();
            }
        });
    }
    private void onClickBackAddLocationBtn() {
        btnBack = findViewById(R.id.backAddRst);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeActivity();
            }
        });
    }

    private void onClickAddImageLocationBtn() {
        buttonAddImageRst = findViewById(R.id.buttonAddImageRst);
        buttonAddImageRst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImagePicker();
            }
        });
    }

    private String getFileNameFromUri(Uri uri) {
        String fileName = null;
        String[] projection = {MediaStore.Images.Media.DISPLAY_NAME};

        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME);
            fileName = cursor.getString(columnIndex);
            cursor.close();
        }
        return fileName;
    }


    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            imageUri =selectedImageUri;
            imageName = getFileNameFromUri(selectedImageUri);
            imageViewRst = findViewById(R.id.imageViewRst);
            imageViewRst.setImageURI(selectedImageUri);
        }
    }

    private  void closeActivity (){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}