package com.quizgame.triviamaker;

import com.google.gson.annotations.SerializedName;

public class Question {

    @SerializedName("id")
    public int id;

    @SerializedName("question")
    public String question;

    @SerializedName("choice1")
    public String choice1;

    @SerializedName("choice2")
    public String choice2;

    @SerializedName("choice3")
    public String choice3;

    @SerializedName("choice4")
    public String choice4;

    @SerializedName("choicecorrect")
    public String choicecorrect;
}
