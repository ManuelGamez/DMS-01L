package com.edu.sv.ejemplo_g9;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.edu.sv.ejemplo_g9.databinding.ActivityMainBinding;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements
        SearchView.OnQueryTextListener {
    ActivityMainBinding binding;
    DogAdapter dogAdapter;
    List<String> images = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initRecyclerView();
        binding.searchDogs.setOnQueryTextListener((SearchView.OnQueryTextListener) this);
    }
    private void initRecyclerView() {
        dogAdapter = new DogAdapter(images);
        binding.listDogs.setLayoutManager(new LinearLayoutManager(this));
        binding.listDogs.setAdapter(dogAdapter);
    }
    private ApiService getApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dog.ceo/api/breed/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService service = retrofit.create(ApiService.class);
        return service;
    }
    private void searchByName(String raza) {
        final Call<DogsResponse> batch = getApiService().getDogsByBreed(raza);
        batch.enqueue(new Callback<DogsResponse>() {
            @Override
            public void onResponse(@Nullable Call<DogsResponse> call, @Nullable
                    Response<DogsResponse> response) {
                if (response != null && response.body() !=null)
                {
                    List<String> responseImages = response.body().getImages();
                    images.clear();
                    images.addAll(responseImages);
                    dogAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(@Nullable Call<DogsResponse> call, @Nullable Throwable t) {
                if(t!=null)
                {
                    showError();
                }
            }
        });
    }

    private void showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        if(!query.isEmpty()){
            searchByName(query.toLowerCase());
        }
        return true;
    }
    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }
}

