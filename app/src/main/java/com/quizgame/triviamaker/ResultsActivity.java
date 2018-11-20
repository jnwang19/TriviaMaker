package com.quizgame.triviamaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    TextView tvResult, tvScoreResult, tvGrade;
    Button bRetry, bQuit;
    QuizInfo quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        tvResult = (TextView)findViewById(R.id.tvResult);
        tvScoreResult = (TextView)findViewById(R.id.tvScoreResult);
        tvGrade = (TextView)findViewById(R.id.tvGrade);
        bRetry = (Button)findViewById(R.id.bRetry);
        bQuit = (Button)findViewById(R.id.bQuit);

        Bundle bundle = getIntent().getExtras();
        double score = bundle.getInt("finalScore");
        double total = bundle.getInt("numQuestions");
        quiz = (QuizInfo) getIntent().getSerializableExtra("quiz");

        tvScoreResult.setText("You scored " + score + " out of " + total);

        if (score == total){
            tvGrade.setText("Amazing!");
        }
        else if (score >= total * 0.8){
            tvGrade.setText("Good work!");
        }
        else if (score >= total * 0.5){
            tvGrade.setText("Good effort. Keep studying!");
        }
        else if (score >= total * 0.2){
            tvGrade.setText("Go over your notes!");
        }
        else {
            tvGrade.setText("... You're bad");
        }

        bRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent retryIntent = new Intent(ResultsActivity.this, MultipleChoice.class);
                retryIntent.putExtra("quiz", quiz);
                ResultsActivity.this.startActivity(retryIntent);
                ResultsActivity.this.finish();
            }
        });

        bQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultsActivity.this, QuizListActivity.class));
                ResultsActivity.this.finish();
            }
        });
    }


}
