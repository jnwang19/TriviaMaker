package com.quizgame.triviamaker;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class QuizInfo implements Serializable{
    @SerializedName("id")
    public int id;

    @SerializedName("quizname")
    public String quizname;

    @SerializedName("datecreated")
    public String datecreated;

    @SerializedName("username")
    public String username;
}
