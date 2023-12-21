package com.example.travelapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelapp.R;
import com.example.travelapp.adapter.DestinationAdapter;
import com.example.travelapp.adapter.OnItemClickListener;
import com.example.travelapp.model.Destination;
import com.example.travelapp.network.RestApiService;
import com.example.travelapp.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationFragment extends Fragment {

    RecyclerView recyclerView;
    DestinationAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_location, container, false);

        recyclerView = view.findViewById(R.id.data_location);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        fetchListDestination();

        Button openAddLocationView = view.findViewById(R.id.openAddLocationView);

        // Now you can work with the Button as needed
        openAddLocationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open SecondActivity when the button is clicked
                openNewActivity();
            }
        });

        return view;
    }

    private void openNewActivity() {
        Intent intent = new Intent(requireActivity(), AddDestination.class);
        startActivity(intent);
    }

    private  void  fetchListDestination(){
        RestApiService apiService = RetrofitInstance.getApiService();
        Call<Destination> call = apiService.getAllDestination();
        call.enqueue(new Callback<Destination>() {
            @Override
            public void onResponse(Call<Destination> call, Response<Destination> response) {
                adapter = new DestinationAdapter(getActivity(),response.body().getData(),(OnItemClickListener) requireActivity());
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<Destination> call, Throwable t) {
            }
        });

    }
}
