package com.example.exampl_r;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MessagesApi {
    @GET("messages1.json")
    Call<List<Message>> messages();
}
