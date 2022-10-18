package com.example.marvelapp.Repo.Remote;

import com.google.gson.annotations.SerializedName;

public class CharacterModel {


    // @SerializedName annotation allows changing the variable name received from the API
    @SerializedName("realname") // Variable name received from the API
    private String actorName; // My variable name (The one I'll use through the app)
    @SerializedName("name")
    private String characterName;
    private String team;
    @SerializedName("imageurl")
    private String imageUrl;
    private String bio;


    public String getActorName() {
        return actorName;
    }

    public String getCharacterName() {
        return characterName;
    }

    public String getTeam() {
        return team;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getBio() {
        return bio;
    }
}