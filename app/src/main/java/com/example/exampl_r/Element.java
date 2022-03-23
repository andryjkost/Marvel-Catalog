package com.example.exampl_r;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Element extends AppCompatActivity {
    ImageView card_hero;
    ImageButton first_icon;
    ImageButton second_icon;
    ImageButton third_icon;
    ImageButton go_home;
    LinearLayout linear_all;
    LinearLayout linear_butt;
    TextView name_hero;
    TextView powerstats_biography;
    TextView powerstats_biography_text;
    TextView appearance_work;
    TextView appearance_work_text;
    TextView physics_connections;
    TextView physics_connections_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.element);
        //красивое изображение экрана
        ConstraintLayout mainLayout = findViewById(R.id.second_layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) mainLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
        //находим текстовые поля
        name_hero = findViewById(R.id.name_hero);
        powerstats_biography = findViewById(R.id.powerstats_biography);
        powerstats_biography_text = findViewById(R.id.powerstats_biography_text);
        appearance_work = findViewById(R.id.appearance_work);
        appearance_work_text = findViewById(R.id.appearance_work_text);
        physics_connections = findViewById(R.id.physics_connections);
        physics_connections_text = findViewById(R.id.physics_connections_text);
        //получаем элемент
        Intent i = getIntent();
        first_icon = findViewById(R.id.first_icon);
        second_icon = findViewById(R.id.second_icon);
        third_icon = findViewById(R.id.third_icon);
        linear_all = findViewById(R.id.linear_all);
        linear_butt = findViewById(R.id.linear_butt);
        card_hero = findViewById(R.id.card_hero);
        go_home = findViewById(R.id.go_home);
        final String new_element = i.getStringExtra("new_element");
        //получаем данные о персонаже
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/akabab/superhero-api/master/api/").addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        MessagesApi messagesApi = retrofit.create(MessagesApi.class);
        Call<List<Message>> messages = messagesApi.messages();
        go_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Element.this, MainActivity.class);
                startActivity(intent1);
            }
        });
        messages.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, final Response<List<Message>> response) {
                linear_all.setVisibility(View.INVISIBLE);
                card_hero.setVisibility(View.INVISIBLE);
                name_hero.setText(new_element);
                first_icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        List<Message> message = response.body();
                        for (Message mes : message){
                            if (mes.getName().equals(new_element)){
                                linear_all.setVisibility(View.VISIBLE);
                                card_hero.setVisibility(View.INVISIBLE);
                                name_hero.setText(new_element);
                                powerstats_biography.setText("Powerstats");
                                powerstats_biography_text.setText("INTELLIGENCE: " + mes.getPowerstats().getIntelligence().toString() + "\n"
                                + "STRENGTH: " + mes.getPowerstats().getStrength().toString() + "\n"
                                + "SPEED: " + mes.getPowerstats().getSpeed().toString() + "\n"
                                + "DURABILITY: " + mes.getPowerstats().getDurability().toString() + "\n"
                                + "POWER: " + mes.getPowerstats().getPower().toString() + "\n"
                                + "COMBAT: " + mes.getPowerstats().getCombat());
                                appearance_work.setText("Appearance");
                                appearance_work_text.setText("GENDER: " + mes.getAppearance().getGender() + "\n"
                                + "RACE: " + mes.getAppearance().getRace() + "\n"
                                + "EYE COLOR: " + mes.getAppearance().getEyeColor() + "\n"
                                + "HAIR COLOR: " + mes.getAppearance().getHairColor());
                                physics_connections.setText("Physics");
                                physics_connections_text.setText("HEIGHT: " + mes.getAppearance().getHeight().toString() + "\n"
                                + "WEIGHT: " + mes.getAppearance().getWeight().toString());
                                break;

                            }
                        }

                    }
                });
                second_icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        List<Message> message = response.body();
                        for (Message mes : message){
                            if (mes.getName().equals(new_element)){
                                linear_all.setVisibility(View.VISIBLE);
                                card_hero.setVisibility(View.INVISIBLE);
                                powerstats_biography.setText("Biography");
                                powerstats_biography_text.setText("FULLNAME: " + mes.getBiography().getFullName() + "\n"
                                + "ALIASES: " + mes.getBiography().getAliases().toString() + "\n"
                                + "PLACE OF BIRTH: " + mes.getBiography().getPlaceOfBirth() + "\n"
                                + "FIRST APPEARANCE: " + mes.getBiography().getFirstAppearance() + "\n"
                                + "PUBLISHER: " + mes.getBiography().getPublisher() + "\n"
                                + "ALIGNMENT: " + mes.getBiography().getAlignment());
                                appearance_work.setText("Work");
                                appearance_work_text.setText("OCCUPATION: " + mes.getWork().getOccupation() + "\n"
                                + "BASE: " + mes.getWork().getBase());
                                physics_connections.setText("Connections");
                                physics_connections_text.setText("GROUPAFFILIATION: " + mes.getConnections().getGroupAffiliation() );
                                break;
                            }
                        }

                    }
                });
                third_icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        List<Message> message = response.body();
                        for (Message mes : message){
                            if (mes.getName().equals(new_element)){
                                linear_all.setVisibility(View.INVISIBLE);
                                card_hero.setVisibility(View.VISIBLE);
                                Picasso.get().load(mes.getImages().getMd()).into(card_hero);
                                break;

                            }
                        }

                    }
                });


            }
            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                System.out.println(t.getCause());

            }
        });









    }

}
