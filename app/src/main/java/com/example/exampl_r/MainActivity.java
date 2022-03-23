package com.example.exampl_r;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.app.SearchManager;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView numberList;
    NumbersAdapter numbersAdapter;
    public ArrayList<String> mass;
    public androidx.appcompat.widget.SearchView searchView;
    LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //создание анимации заднего фона
        mainLayout= findViewById(R.id.mainLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) mainLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
        //создание ресайкла
        numberList = findViewById(R.id.rv_numbers);
        searchView = findViewById(R.id.search_view1);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //numberList.setLayoutManager(layoutManager);
        //numberList.setHasFixedSize(true);
        //саоздание массива данных

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/akabab/superhero-api/master/api/").addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        MessagesApi messagesApi = retrofit.create(MessagesApi.class);
        Call<List<Message>> messages = messagesApi.messages();
        messages.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                mass = new ArrayList<String>();
                List<Message> message = response.body();
                for(Message mes:message){
                    mass.add(mes.getName());
                }
                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                numberList.setLayoutManager(layoutManager);
                numberList.setHasFixedSize(true);
                numbersAdapter = new NumbersAdapter(getApplicationContext(), mass);
                numberList.setAdapter(numbersAdapter);
                searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        filter(newText);
                        return true;
                    }
                });



            }
            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                System.out.println(t.getCause());

            }
        });



        //обработка поиска формул
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });

        //использование ресайкла

        //numbersAdapter = new NumbersAdapter(this, mass);
        //numberList.setAdapter(numbersAdapter);
        //System.out.println(mass);

    }

    private void filter(String newText) {
        ArrayList<String> moment_list = new ArrayList<String>();
        for (int i = 0; i < mass.size(); i ++){
            String tx = mass.get(i).toLowerCase();
            if (tx.contains(newText.toLowerCase())){
                moment_list.add(mass.get(i));
            }

        }
        numbersAdapter.filterList(moment_list);
    }



}