package com.example.travelapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.travelapp.R;
import com.example.travelapp.adapter.OnItemClickListener;
import com.example.travelapp.model.User;
import com.example.travelapp.network.RestApiService;
import com.example.travelapp.network.RetrofitInstance;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity  {


    EditText txtUsername , txtPass;

    TextView signUp;
    Button signIn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

    }

    public  void  init(){
        txtUsername = findViewById(R.id.txtUsername);
        txtPass = findViewById(R.id.txtPass);
        signUp = findViewById(R.id.signUp);
        signIn = findViewById(R.id.signIn);

        login();
        register();
    }


    public  void login(){
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUsername.getText().toString();
                String pass = txtPass.getText().toString();
                validate(username , pass);
                saveData(username, pass);
            }
        });
    }


    public  void  saveData(String username , String pass){

        User user = new User(username, pass);
        RestApiService apiService = RetrofitInstance.getApiService();
        Call<User> call = apiService.login(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body()==null){
                    Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Lỗi Server", Toast.LENGTH_LONG).show();
            }
        });

    }


    public  void validate(String username ,String pass){
        if( username.isEmpty()==true|| pass.isEmpty()==true){
            Toast.makeText(LoginActivity.this, "Vui lòng điền đầy dủ thông tin", Toast.LENGTH_LONG).show();
        }
    }


    public  void register(){
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegister();
            }
        });
    }



    public void openRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}