package com.example.marvelapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance = null;

    private MyAPI myAPI;

    private RetrofitClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myAPI = retrofit.create(MyAPI.class);
    }

    public static synchronized RetrofitClient getInstance() {
        if(instance == null){
            instance = new RetrofitClient();
        }
        return instance;
    }

    public MyAPI getMyAPI() {return myAPI;}
}
