package com.example.travelapp.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelapp.R;
import com.example.travelapp.Utils.RealPathUtil;
import com.example.travelapp.model.Destination;
import com.example.travelapp.network.RestApiService;
import com.example.travelapp.network.RetrofitInstance;
import com.google.android.material.navigation.NavigationView;

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

public class AddDestination extends AppCompatActivity {

    ImageView btnArrowBack ;
    Button btnBack , buttonAddImageDestination;



    TextView editTextLocationName, textLocationDescription;

    Spinner  spinnerLocationType ,spinnerLocationProvince, spinnerLocationDistrict;

    ImageView imageViewLocation;
    ProgressDialog progressDialog;
    String name ,desc,district , province,type ,imageName ;
    Uri imageUri;
    private static final int PICK_IMAGE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editTextLocationName = findViewById(R.id.editTextLocationName);
        textLocationDescription = findViewById(R.id.editTextLocationDesc);
        spinnerLocationDistrict = findViewById(R.id.spinnerLocationDistrict);
        spinnerLocationProvince = findViewById(R.id.spinnerLocationProvince);
        spinnerLocationType = findViewById(R.id.spinnerLocationType);
        imageViewLocation = findViewById(R.id.imageViewLocation);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please await...");
        setContentView(R.layout.add_location);
//        onClickAddLocationBtn();
        innitSpinner();
        innitSpinnerDistrict();
        innitSpinnerProvince();
        onClickBackAddLocationArr();
        onClickBackAddLocationBtn();
        onClickAddImageLocationBtn();
    }

    public  void  saveData(View v){
        editTextLocationName = findViewById(R.id.editTextLocationName);
        textLocationDescription = findViewById(R.id.editTextLocationDesc);
        spinnerLocationDistrict = findViewById(R.id.spinnerLocationDistrict);
        spinnerLocationProvince = findViewById(R.id.spinnerLocationProvince);
        spinnerLocationType = findViewById(R.id.spinnerLocationType);
        imageViewLocation = findViewById(R.id.imageViewLocation);

        name =  editTextLocationName.getText().toString();
        desc =  textLocationDescription.getText().toString();
        district =  spinnerLocationDistrict.getSelectedItem().toString();
        province =  spinnerLocationProvince.getSelectedItem().toString();
        type =  spinnerLocationType.getSelectedItem().toString();


        if(!name.isEmpty()&&! desc.isEmpty()&&!district.isEmpty()&&!province.isEmpty()&&!type.isEmpty()&&!imageName.isEmpty()){
            RequestBody namePart = RequestBody.create(MediaType.parse("text/plain"), name);
            RequestBody descPart = RequestBody.create(MediaType.parse("text/plain"), desc);
            RequestBody provincePart = RequestBody.create(MediaType.parse("text/plain"), province);
            RequestBody districtPart = RequestBody.create(MediaType.parse("text/plain"), district);
            RequestBody typePart = RequestBody.create(MediaType.parse("text/plain"), type);
            String imageRealUri = RealPathUtil.getRealPath(this , imageUri);
            File avatarFile = new File(imageRealUri);
            RequestBody avatarRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), avatarFile);
            MultipartBody.Part imagePart = MultipartBody.Part.createFormData("image", avatarFile.getName(), avatarRequestBody);

            RestApiService apiService = RetrofitInstance.getApiService();
            Call<Destination.Data> call = apiService.createDestination(namePart,descPart, provincePart, districtPart,typePart,imagePart);

            call.enqueue(new Callback<Destination.Data>() {
                @Override
                public void onResponse(Call<Destination.Data> call, Response<Destination.Data> response) {
                    if ( response.body() != null) {
                        Toast.makeText(AddDestination.this, "Thêm địa điểm thành công", Toast.LENGTH_LONG).show();
                        closeActivity();
                    } else {
                        Toast.makeText(AddDestination.this, "Vui lòng điền đầy dủ thông tin", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Destination.Data> call, Throwable t) {
                    // Handle failure
                    Toast.makeText(AddDestination.this, "Vui lòng điền đầy dủ thông tin shfgdhs", Toast.LENGTH_LONG).show();

                }
            });

        }else {
            Toast.makeText(AddDestination.this, "Vui lòng điền đầy dủ thông tin", Toast.LENGTH_LONG).show();
        }
    }

    public void innitSpinner() {
        spinnerLocationType = findViewById(R.id.spinnerLocationType);
        List<String> spinnerData = new ArrayList<>();
        spinnerData.add("Chùa Dền");
        spinnerData.add("Phong cảnh");
        spinnerData.add("Khu vui chơi");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerData);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLocationType.setAdapter(adapter);

    }
    public void innitSpinnerProvince() {
        spinnerLocationProvince = findViewById(R.id.spinnerLocationProvince);
        List<String> provinceList = Arrays.asList(
                "Hà Nội", "Hồ Chí Minh", "Đà Nẵng", "Hải Phòng", "Quảng Ninh", "Thái Bình",
                "Bắc Ninh", "Hải Dương", "Hà Nam", "Nam Định", "Ninh Bình", "Thanh Hóa",
                "Nghệ An", "Hà Tĩnh", "Quảng Bình", "Quảng Trị", "Thừa Thiên Huế", "Quảng Nam",
                "Quảng Ngãi", "Bình Định", "Phú Yên", "Khánh Hòa", "Ninh Thuận", "Bình Thuận",
                "Kon Tum", "Gia Lai", "Đắk Lắk", "Đắk Nông", "Lâm Đồng", "Bà Rịa - Vũng Tàu",
                "Bình Phước", "Bình Dương", "Đồng Nai", "Tây Ninh", "Long An", "Tiền Giang",
                "Bến Tre", "Trà Vinh", "Vĩnh Long", "Đồng Tháp", "An Giang", "Kiên Giang",
                "Cần Thơ", "Hậu Giang", "Sóc Trăng", "Bạc Liêu", "Cà Mau", "Lào Cai",
                "Yên Bái", "Lạng Sơn", "Quảng Ninh", "Bắc Giang", "Phú Thọ", "Vĩnh Phúc",
                "Bắc Ninh", "Hải Dương", "Hưng Yên", "Thái Bình", "Hà Nam", "Nam Định",
                "Ninh Bình", "Thanh Hóa"
                // Thêm các tỉnh thành khác tương ứng với 64 tỉnh thành Việt Nam
        );

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, provinceList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLocationProvince.setAdapter(adapter);

    }


    public void innitSpinnerDistrict() {
        spinnerLocationDistrict = findViewById(R.id.spinnerLocationDistrict);
        List<String> districts = Arrays.asList(
                "Ba Đình", "Hoàn Kiếm", "Hai Bà Trưng", "Đống Đa", "Tây Hồ",
                "Cầu Giấy", "Thanh Xuân", "Hoàng Mai", "Long Biên", "Bắc Từ Liêm",
                "Nam Từ Liêm", "Hà Đông", "Thanh Trì", "Sơn Tây", "Ba Vì",
                "Phúc Thọ", "Thạch Thất", "Quốc Oai", "Chương Mỹ", "Đan Phượng",
                "Hoài Đức", "Thanh Oai", "Mỹ Đức", "Ứng Hòa", "Thường Tín", "Phú Xuyên",
                "Quận 1", "Quận 2", "Quận 3", "Quận 4", "Quận 5", "Quận 6", "Quận 7",
                "Quận 8", "Quận 9", "Quận 10", "Quận 11", "Quận 12", "Bình Tân",
                "Bình Thạnh", "Gò Vấp", "Phú Nhuận", "Tân Bình", "Tân Phú", "Thủ Đức",
                "Bình Chánh", "Cần Giờ", "Củ Chi", "Hóc Môn", "Nhà Bè"
        );
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, districts);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLocationDistrict.setAdapter(adapter);

    }

    private void onClickBackAddLocationArr() {
        btnArrowBack = findViewById(R.id.location_arrow_back);
        btnArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeActivity();
            }
        });
    }
    private void onClickBackAddLocationBtn() {
        btnBack = findViewById(R.id.backAddLocation);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeActivity();
            }
        });
    }

    private void onClickAddImageLocationBtn() {
        buttonAddImageDestination = findViewById(R.id.buttonAddImageDestination);
        buttonAddImageDestination.setOnClickListener(new View.OnClickListener() {
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
            imageViewLocation = findViewById(R.id.imageViewLocation);
            imageViewLocation.setImageURI(selectedImageUri);
        }
    }

    private  void closeActivity (){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}