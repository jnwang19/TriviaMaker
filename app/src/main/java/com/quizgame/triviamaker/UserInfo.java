package com.quizgame.triviamaker;

import com.google.gson.annotations.SerializedName;

public class UserInfo {

    @SerializedName("age")
    public int age;

    @SerializedName("NAME")
    public String NAME;

    @SerializedName("username")
    public String username;

    @SerializedName("PASSWORD")
    public String PASSWORD;

}
