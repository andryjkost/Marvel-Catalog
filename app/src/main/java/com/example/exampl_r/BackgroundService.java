package com.example.exampl_r;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.JobIntentService;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BackgroundService extends JobIntentService {


    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/akabab/superhero-api/master/api/").addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        MessagesApi messagesApi = retrofit.create(MessagesApi.class);
        Call<List<Message>> messages = messagesApi.messages();
        try {
            List<Message> sync = messages.execute().body();
            ArrayList<String> itog = new ArrayList<>();
            for (Message mes : sync){
                itog.add(mes.getName());
            }
            Intent intent1 = new Intent(BackgroundService.this, MainActivity.class);
            intent1.putStringArrayListExtra("new_mass", itog);
            sendBroadcast(intent1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
