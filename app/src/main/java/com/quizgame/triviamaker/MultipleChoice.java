package com.quizgame.triviamaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class MultipleChoice extends AppCompatActivity {

    final String LOG = "MultipleChoice";
    QuestionLibrary mLibrary;
    private TextView tvScoreText;
    private TextView tvScore;
    private TextView tvQuestion;
    private Button bChoice1;
    private Button bChoice2;
    private Button bChoice3;
    private Button bChoice4;
    private Button bQuit;
    QuizInfo quiz;

    String answer;
    int score = 0;
    int mQuestionNumber = 0;
    int id = 0;

    private ArrayList<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice);

        quiz = (QuizInfo) getIntent().getSerializableExtra("quiz");
        id = quiz.id;

        HashMap postData = new HashMap();
        postData.put("txtId", Integer.toString(id));

        PostResponseAsyncTask task = new PostResponseAsyncTask(MultipleChoice.this, postData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                Log.d(LOG, s);
                questionList = new JsonConverter<Question>().toArrayList(s, Question.class);
                mLibrary = new QuestionLibrary(questionList);
                updateQuestion();
            }
        });
        task.execute(ServerURL.getURL() + "questions.php");

        tvScoreText = (TextView)findViewById(R.id.tvScoreText);
        tvScore = (TextView)findViewById(R.id.tvScore);
        tvQuestion = (TextView)findViewById(R.id.tvQuestion);
        bChoice1 = (Button)findViewById(R.id.bChoice1);
        bChoice2 = (Button)findViewById(R.id.bChoice2);
        bChoice3 = (Button)findViewById(R.id.bChoice3);
        bChoice4 = (Button)findViewById(R.id.bChoice4);
        bQuit = (Button)findViewById(R.id.bQuit);

        bChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bChoice1.getText().equals(answer)) {
                    score++;
                    updateScore(score);
                    Toast.makeText(MultipleChoice.this, "Correct!", Toast.LENGTH_SHORT).show();
                    if(mQuestionNumber == mLibrary.mQuestions.size()) {
                        Intent i = new Intent(MultipleChoice.this, ResultsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("finalScore", score);
                        bundle.putInt("numQuestions", mLibrary.mQuestions.size());
                        i.putExtras(bundle);
                        i.putExtra("quiz", (Serializable) quiz);
                        MultipleChoice.this.finish();
                        startActivity(i);
                    }
                    else {
                        updateQuestion();
                    }
                } else {
                        Toast.makeText(MultipleChoice.this, "Wrong", Toast.LENGTH_SHORT).show();
                    if(mQuestionNumber == mLibrary.mQuestions.size()) {
                        Intent i = new Intent(MultipleChoice.this, ResultsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("finalScore", score);
                        bundle.putInt("numQuestions", mLibrary.mQuestions.size());
                        i.putExtra("quiz", (Serializable) quiz);
                        i.putExtras(bundle);
                        MultipleChoice.this.finish();
                        startActivity(i);
                    }
                    else {
                        updateQuestion();
                    }
                }
            }
        });

        bChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bChoice2.getText().equals(answer)) {
                    score++;
                    updateScore(score);
                    Toast.makeText(MultipleChoice.this, "Correct!", Toast.LENGTH_SHORT).show();
                    if(mQuestionNumber == mLibrary.mQuestions.size()) {
                        Intent i = new Intent(MultipleChoice.this, ResultsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("finalScore", score);
                        bundle.putInt("numQuestions", mLibrary.mQuestions.size());
                        i.putExtra("quiz", (Serializable) quiz);
                        i.putExtras(bundle);
                        MultipleChoice.this.finish();
                        startActivity(i);
                    }
                    else {
                        updateQuestion();
                    }
                } else {
                    Toast.makeText(MultipleChoice.this, "Wrong", Toast.LENGTH_SHORT).show();
                    if(mQuestionNumber == mLibrary.mQuestions.size()) {
                        Intent i = new Intent(MultipleChoice.this, ResultsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("finalScore", score);
                        bundle.putInt("numQuestions", mLibrary.mQuestions.size());
                        i.putExtra("quiz", (Serializable) quiz);
                        i.putExtras(bundle);
                        MultipleChoice.this.finish();
                        startActivity(i);
                    }
                    else {
                        updateQuestion();
                    }
                }
            }
        });

        bChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bChoice3.getText().equals(answer)) {
                    score++;
                    updateScore(score);
                    Toast.makeText(MultipleChoice.this, "Correct!", Toast.LENGTH_SHORT).show();
                    if(mQuestionNumber == mLibrary.mQuestions.size()) {
                        Intent i = new Intent(MultipleChoice.this, ResultsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("finalScore", score);
                        bundle.putInt("numQuestions", mLibrary.mQuestions.size());
                        i.putExtra("quiz", (Serializable) quiz);
                        i.putExtras(bundle);
                        MultipleChoice.this.finish();
                        startActivity(i);
                    }
                    else {
                        updateQuestion();
                    }
                } else {
                    Toast.makeText(MultipleChoice.this, "Wrong", Toast.LENGTH_SHORT).show();
                    if(mQuestionNumber == mLibrary.mQuestions.size()) {
                        Intent i = new Intent(MultipleChoice.this, ResultsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("finalScore", score);
                        bundle.putInt("numQuestions", mLibrary.mQuestions.size());
                        i.putExtra("quiz", (Serializable) quiz);
                        i.putExtras(bundle);
                        MultipleChoice.this.finish();
                        startActivity(i);
                    }
                    else {
                        updateQuestion();
                    }
                }
            }
        });

        bChoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bChoice4.getText().equals(answer)) {
                    score++;
                    updateScore(score);
                    Toast.makeText(MultipleChoice.this, "Correct!", Toast.LENGTH_SHORT).show();
                    if(mQuestionNumber == mLibrary.mQuestions.size()) {
                        Intent i = new Intent(MultipleChoice.this, ResultsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("finalScore", score);
                        bundle.putInt("numQuestions", mLibrary.mQuestions.size());
                        i.putExtra("quiz", (Serializable) quiz);
                        i.putExtras(bundle);
                        MultipleChoice.this.finish();
                        startActivity(i);
                    }
                    else {
                        updateQuestion();
                    }
                } else {
                    Toast.makeText(MultipleChoice.this, "Wrong", Toast.LENGTH_SHORT).show();
                    if(mQuestionNumber == mLibrary.mQuestions.size()) {
                        Intent i = new Intent(MultipleChoice.this, ResultsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("finalScore", score);
                        bundle.putInt("numQuestions", mLibrary.mQuestions.size());
                        i.putExtra("quiz", (Serializable) quiz);
                        i.putExtras(bundle);
                        MultipleChoice.this.finish();
                        startActivity(i);
                    }
                    else {
                        updateQuestion();
                    }
                }
            }
        });

        bQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MultipleChoice.this, QuizListActivity.class));
                MultipleChoice.this.finish();
            }
        });
    }

    private void updateQuestion(){
        tvQuestion.setText(mLibrary.getQuestion(mQuestionNumber));
        bChoice1.setText(mLibrary.getChoice1(mQuestionNumber));
        bChoice2.setText(mLibrary.getChoice2(mQuestionNumber));
        bChoice3.setText(mLibrary.getChoice3(mQuestionNumber));
        bChoice4.setText(mLibrary.getChoice4(mQuestionNumber));

        answer = mLibrary.getCorrectAnswer(mQuestionNumber);
        mQuestionNumber++;
        System.out.println("mQuestionNumber = " + mQuestionNumber);
        System.out.println("Number of questions in mLibrary = " + mLibrary.mQuestions.size());
    }

    private void updateScore(int point) {
        tvScore.setText("" + score);
    }

}
