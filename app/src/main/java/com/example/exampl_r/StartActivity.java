package com.example.exampl_r;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.squareup.picasso.Picasso;

public class StartActivity extends AppCompatActivity {
    ImageView Marvel;
    Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        //создание анимации заднего фона
        ConstraintLayout mainLayout1 = findViewById(R.id.main_layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) mainLayout1.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
        //загрузка фото
        Marvel = findViewById(R.id.Marvel);
        Picasso.get().load("https://i.playground.ru/p/HrQWbghLL7Z6fVZ59Vg9ww.png").into(Marvel);
        //кнопка начало
        start = findViewById(R.id.button_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
