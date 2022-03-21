package com.example.exampl_r;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.exampl_r.H2SO4.H2SO4_1_1;
import com.example.exampl_r.H2SO4.H2SO4_1_2;
import com.example.exampl_r.H2SO4.H2SO4_2_1;
import com.example.exampl_r.H2SO4.H2SO4_2_2;

public class Element extends AppCompatActivity {
    ImageButton first_icon;
    ImageButton second_icon;
    ImageButton last_fragment_b;
    ImageButton next_fragment_b;
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
        //получаем элемент
        Intent i = getIntent();
        first_icon = findViewById(R.id.first_icon);
        second_icon = findViewById(R.id.second_icon);
        String new_element = i.getStringExtra("new_element");
        //создаем кнопки перехода фрагментов
        last_fragment_b = findViewById(R.id.last_fragment_b);
        next_fragment_b = findViewById(R.id.next_fragment_b);
        //реализуем переход из recycleview в окно элемента
        if (new_element.equals("s")){
            first_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    replaceFragment(new fragment1());
                }
            });
            second_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    replaceFragment(new fragment2());
                }
            });
        }
        else if (new_element.equals("H2SO4")){
            final Integer[] count1 = {0};
            final Integer[] count2 = {0};
            final Integer[] count3 = {0};
            first_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    count1[0] = 1;
                    count2[0] = 0;
                    count3[0] = 0;
                    replaceFragment(new H2SO4_1_1());
                }
            });
            second_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    count1[0] = 0;
                    count2[0] = 1;
                    count3[0] = 0;
                    replaceFragment(new H2SO4_2_1());

                }
            });
            next_fragment_b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (count1[0].equals(1)){
                        count1[0] = 2;
                        replaceFragment(new H2SO4_1_2());
                    }
                    else if(count2[0].equals(1)){
                        count2[0] = 2;
                        replaceFragment(new H2SO4_2_2());
                    }
                }
            });
            last_fragment_b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (count1[0].equals(2)){
                        count1[0] = 1;
                        replaceFragment(new H2SO4_1_1());
                    }
                    else if(count2[0].equals(2)){
                        count2[0] = 1;
                        replaceFragment(new H2SO4_2_1());
                    }
                }
            });



        }



    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();

    }
}
