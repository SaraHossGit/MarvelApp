package com.example.marvelapp.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance = null;

    private MyAPI myAPI;

    private RetrofitClient(){
        /// Build Retrofit Object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        /// Steps to make a Request:
        /// STEP(1): For sending a request, we have to obtain an instance of the interface
        /// by making a call to create() on Retrofit instance
        /// [Next step in Main Activity]
        myAPI = retrofit.create(MyAPI.class);
    }

    public static synchronized RetrofitClient getInstance() {
        /// Singleton Pattern for taking instance from retrofit
        if(instance == null){
            instance = new RetrofitClient();
        }
        return instance;
    }

    public MyAPI getMyAPI() {return myAPI;}
}
