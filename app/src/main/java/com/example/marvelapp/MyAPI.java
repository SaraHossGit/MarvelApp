package com.example.marvelapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyAPI {
    String BASE_URL = "https://www.simplifiedcoding.net/demos/";

    @GET("marvel") // @TypeOfRequest(EndPoint)

    // The return value wraps the response in a Call object with the type of the expected result (DataModel)
    Call<List<CharacterModel>> getUsers(); // Call <List <DataModel> > method
}
