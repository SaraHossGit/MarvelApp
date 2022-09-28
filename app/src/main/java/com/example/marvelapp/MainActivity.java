package com.example.marvelapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView1, recyclerView2;

//    Heróis_recycler_view
//    Vilões_recycler_view
//    Anti_heróis_recycler_view
//    Alienígenas_recycler_view
//    Humanos_recycler_view

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView1 = findViewById(R.id.Heróis_recycler_view);
        recyclerView2 = findViewById(R.id.Vilões_recycler_view);

        getData();
    }

    private void getData() {

        Call<List<CharacterModel>> call = RetrofitClient.getInstance().getMyAPI().getUsers();
        call.enqueue(new Callback<List<CharacterModel>>() {
            @Override
            public void onResponse(Call<List<CharacterModel>> call, Response<List<CharacterModel>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Code is :" +String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<CharacterModel> characters = response.body();
                ArrayList<CharacterModel> characters1 =  new ArrayList<>();
                ArrayList<CharacterModel> characters2  = new ArrayList<>();

                for (int i=0; i<characters.size(); i++){
                    if(characters.get(i).getTeam().equals("Avengers")){
                        characters1.add(characters.get(i));
                    }
                    else if(characters.get(i).getTeam().equals("X-Men")){
                        characters2.add(characters.get(i));
                    }
                }


                CharactersAdapter charactersAdapter = new CharactersAdapter(MainActivity.this,characters1);
                recyclerView1.setAdapter(charactersAdapter);
                recyclerView1.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL, false));

                CharactersAdapter charactersAdapter2 = new CharactersAdapter(MainActivity.this,characters2);
                recyclerView2.setAdapter(charactersAdapter2);
                recyclerView2.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL, false));

            }

            @Override
            public void onFailure(Call<List<CharacterModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}