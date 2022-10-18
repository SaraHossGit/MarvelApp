package com.example.marvelapp.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.marvelapp.Repo.Remote.CharacterModel;
import com.example.marvelapp.Retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CharactersViewModel extends ViewModel {
    private MutableLiveData <List<CharacterModel>> charactersList;

    public MutableLiveData<List<CharacterModel>> getCharactersList() {
        if (charactersList==null){
            charactersList = new MutableLiveData<List<CharacterModel>>();
            loadCharacters();
        }
        return charactersList;
    }

    private void loadCharacters() {
        /// [Previous step in Retrofit Client Class]
        /// STEP(2): By using the Interface instance (RetrofitClient.getInstance().getMyAPI()) you can make the
        /// needed API call method through the interface methods ( getCharacters() for example)
        Call<List<CharacterModel>> call = RetrofitClient.getInstance().getMyAPI().getCharacters();

        /// STEP(3): Retrofit makes the network request and waits for the response on a background
        /// thread and delivers to onResponse() or onFailure()methods on the UI thread.
        call.enqueue(new Callback<List<CharacterModel>>() {
            @Override
            public void onResponse(Call<List<CharacterModel>> call, Response<List<CharacterModel>> response) {
                // CASE(2):Ra7 bs mrg34 b data
                if(!response.isSuccessful()){
//                    Toast.makeText(MainActivity.this, "Code is :" +String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                    return;
                }

                // CASE(3):Ra7 w rg3 b data ==> Response<List<CharacterModel>> response
                // Get the list of characters from the response object and save it in a list
                charactersList.setValue(response.body());

                /// STEP(4): Load Data into recycler view (in Main Activity)


            }

            // CASE(1):Mra74 asln (Probably bec. Internet Connection Failed)
            @Override
            public void onFailure(Call<List<CharacterModel>> call, Throwable t) {
//                Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
