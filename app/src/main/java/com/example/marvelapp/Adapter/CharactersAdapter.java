package com.example.marvelapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.marvelapp.Repo.Remote.CharacterModel;
import com.example.marvelapp.UI.DetailsPage;
import com.example.marvelapp.R;

import java.util.List;

public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.ViewHolder> {
    Context context;
    List<CharacterModel> data;
//    String ss="https://www.simplifiedcoding.net/demos/marvel/ironman.jpg";

    public CharactersAdapter(Context context, List<CharacterModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public CharactersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharactersAdapter.ViewHolder holder, int position) {
        holder.actorName.setText(data.get(position).getActorName());
        holder.charName.setText(data.get(position).getCharacterName());
        String imageUrl = data.get(holder.getAdapterPosition()).getImageUrl();

        Log.d("TAG", "onBindViewHolder: "+data.get(position).getImageUrl());

        Glide.with(context)
                .load(data.get(position).getImageUrl())
                .centerCrop()
                .into(holder.charImage);

//        Log.d("TAG", "here:"+data.get(holder.getAdapterPosition()).getImageUrl());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsPage.class);
                int currentIndex = holder.getAdapterPosition();
                intent.putExtra("name", data.get(currentIndex).getActorName());
                intent.putExtra("char", data.get(currentIndex).getCharacterName());
                intent.putExtra("bio", data.get(currentIndex).getBio());
                intent.putExtra("image", data.get(currentIndex).getImageUrl());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{

        ImageView charImage;
        TextView actorName, charName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            charImage = itemView.findViewById(R.id.actor_character_image);
            charName = itemView.findViewById(R.id.actor_character);
            actorName = itemView.findViewById(R.id.actor_name);
        }
    }
}
