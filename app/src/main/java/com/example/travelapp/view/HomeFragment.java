package com.example.travelapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelapp.R;
import com.example.travelapp.adapter.DestinationAdapter;

import com.example.travelapp.model.Destination;
import com.example.travelapp.network.RestApiService;
import com.example.travelapp.network.RetrofitInstance;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    LinearLayout linearLocation , linearHotel , linearRes;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        linearLocation = view.findViewById(R.id.linearLocation);
        linearHotel = view.findViewById(R.id.linearHotel);
        linearRes = view.findViewById(R.id.linearRes);

        linearLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = requireFragmentManager().beginTransaction();
                fragmentTransaction.remove(getParentFragment());
                fragmentTransaction.add(R.id.content_frame, new LocationFragment());
                fragmentTransaction.addToBackStack(null).commit();
            }
        });

        linearHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = requireFragmentManager().beginTransaction();
                fragmentTransaction.remove(getParentFragment());
                fragmentTransaction.add(R.id.content_frame, new HotelFragment());
                fragmentTransaction.addToBackStack(null).commit();
            }
        });
        linearRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = requireFragmentManager().beginTransaction();
                fragmentTransaction.remove(getParentFragment());
                fragmentTransaction.add(R.id.content_frame, new RestaurantFragment());
                fragmentTransaction.addToBackStack(null).commit();
            }
        });

        return view;
    }









}
