package com.edu.sv.ejerciciog9;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.edu.sv.ejerciciog9.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity  {
    ListView repoListView;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        repoListView = findViewById(R.id.repo_list);
        progressBar = findViewById(R.id.pb_main);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create());


        Retrofit retrofit = builder.build();
        GithubAPI gitHubUser = retrofit.create(GithubAPI.class);
        Call<List<GithubRepo>> call =  gitHubUser.repoForUser("LisbethRmrz");
        call.enqueue(new Callback<List<GithubRepo>>() {
            @Override
            public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {

                progressBar.setVisibility(View.INVISIBLE);
                List<GithubRepo> list = response.body();
                Adapter arrayAdapter = new Adapter(MainActivity.this,list);
                repoListView.setAdapter(arrayAdapter);
            }

            @Override
            public void onFailure(Call<List<GithubRepo>> call, Throwable t) {

                progressBar.setVisibility(View.INVISIBLE);

            }
        });

    }

}

