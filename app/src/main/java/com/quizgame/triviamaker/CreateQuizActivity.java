package com.quizgame.triviamaker;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class CreateQuizActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etQuizName;
    Button bCreateQuiz;
    String username = "";
    String formattedDate = "";
    final String LOG = "CreateQuizActivity";
    ArrayList<QuizInfo> quizList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);

        etQuizName = (EditText) findViewById(R.id.etQuizName);
        bCreateQuiz = (Button) findViewById(R.id.bCreateQuiz);

        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        formattedDate = df.format(c.getTime());

        bCreateQuiz.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(etQuizName.getText().toString().equals("")) {
            Toast.makeText(CreateQuizActivity.this, "Please enter a quiz name.", Toast.LENGTH_SHORT).show();
        }
        else {
            HashMap postData = new HashMap();
            postData.put("txtQuizName", etQuizName.getText().toString());
            postData.put("txtUsername", username);
            postData.put("txtDateCreated", formattedDate);
            postData.put("mobile", "android");

            PostResponseAsyncTask taskRead = new PostResponseAsyncTask(CreateQuizActivity.this, postData, new AsyncResponse() {
                @Override
                public void processFinish(String s) {
                    Log.d(LOG, s);
                    if (s.contains("success")) {
                        PostResponseAsyncTask taskGetId = new PostResponseAsyncTask(CreateQuizActivity.this, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                quizList = new JsonConverter<QuizInfo>().toArrayList(s, QuizInfo.class);
                                Intent in = new Intent(CreateQuizActivity.this, AddQuestionActivity.class);
                                in.putExtra("quiz", (Serializable) quizList.get(quizList.size() - 1));
                                CreateQuizActivity.this.startActivity(in);
                                CreateQuizActivity.this.finish();
                            }
                        });
                        taskGetId.execute(ServerURL.getURL() + "quizinfo.php");

                    } else {
                        Toast.makeText(CreateQuizActivity.this, "An error occurred. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            taskRead.execute(ServerURL.getURL() + "createquiz.php");
        }
    }
}
