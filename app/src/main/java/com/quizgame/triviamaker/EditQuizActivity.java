package com.quizgame.triviamaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

public class EditQuizActivity extends AppCompatActivity {

    QuizInfo quiz;
    Button bAdd;
    Button bDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_quiz);

        quiz = (QuizInfo) getIntent().getSerializableExtra("quiz");

        bAdd = (Button) findViewById(R.id.bAdd);
        bDelete = (Button) findViewById(R.id.bDelete);

        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditQuizActivity.this, AddQuestionActivity.class);
                intent.putExtra("quiz", (Serializable) quiz);
                startActivity(intent);
            }
        });

        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditQuizActivity.this, DeleteQuestionActivity.class);
                intent.putExtra("quiz", (Serializable) quiz);
                startActivity(intent);
            }
        });

    }
}
