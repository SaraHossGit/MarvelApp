package com.example.marvelapp.Retrofit;

import com.example.marvelapp.Repo.Remote.CharacterModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyAPI {
    String BASE_URL = "https://www.simplifiedcoding.net/demos/";

    @GET("marvel") // @TypeOfRequest(EndPoint)

    // The return value wraps the response in a Call object with the type of the expected result (DataModel)
    Call<List<CharacterModel>> getCharacters(); // Call <List <DataModel> > method
}
