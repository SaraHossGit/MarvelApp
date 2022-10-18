package com.example.marvelapp.UI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marvelapp.Adapter.CharactersAdapter;
import com.example.marvelapp.R;
import com.example.marvelapp.Repo.Remote.CharacterModel;
import com.example.marvelapp.ViewModel.CharactersViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView1, recyclerView2;
    CharactersViewModel charactersVM;

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

        /// [Previous steps in ViewModel Class]
        /// STEP(4): Load Data into recycler view (in Main Activity)
        charactersVM = new ViewModelProvider(this).get(CharactersViewModel.class);
        getData();
    }

    private void getData() {

        charactersVM.getCharactersList().observe(this, new Observer<List<CharacterModel>>() {
            @Override
            public void onChanged(List<CharacterModel> characters) {

                // Append into 2 different RecyclerViews
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
        });

    }


}