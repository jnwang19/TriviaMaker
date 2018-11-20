package com.quizgame.triviamaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.ArrayList;
import java.util.HashMap;

public class AddQuestionActivity extends AppCompatActivity {

    ArrayList<QuizInfo> quizList;
    String quizId;
    TextView tvEnterQuestion;
    TextView tvEnterChoice;
    TextView tvCorrect;
    EditText etEnterQuestion;
    EditText etChoice1;
    EditText etChoice2;
    EditText etChoice3;
    EditText etChoice4;
    Button bAddQuestion;
    Button bFinish;
    RadioButton rbChoice1;
    RadioButton rbChoice2;
    RadioButton rbChoice3;
    RadioButton rbChoice4;
    RadioGroup rgChoices;
    String correctChoice;
    int clickCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        //Bundle bundle = getIntent().getExtras();
        //quizId = bundle.getString("quizId");

        QuizInfo quiz = (QuizInfo) getIntent().getSerializableExtra("quiz");
        quizId = Integer.toString(quiz.id);

        tvEnterQuestion = (TextView)findViewById(R.id.tvEnterQuestion);
        tvEnterChoice = (TextView)findViewById(R.id.tvEnterChoice);
        tvCorrect = (TextView)findViewById(R.id.tvCorrect);
        etEnterQuestion = (EditText)findViewById(R.id.etEnterQuestion);
        etChoice1 = (EditText)findViewById(R.id.etChoice1);
        etChoice2 = (EditText)findViewById(R.id.etChoice2);
        etChoice3 = (EditText)findViewById(R.id.etChoice3);
        etChoice4 = (EditText)findViewById(R.id.etChoice4);
        bAddQuestion = (Button)findViewById(R.id.bDelete);
        bFinish = (Button)findViewById(R.id.bFinish);
        rbChoice1 = (RadioButton)findViewById(R.id.rbChoice1);
        rbChoice2 = (RadioButton)findViewById(R.id.rbChoice2);
        rbChoice3 = (RadioButton)findViewById(R.id.rbChoice3);
        rbChoice4 = (RadioButton)findViewById(R.id.rbChoice4);
        rgChoices = (RadioGroup) findViewById(R.id.rgChoices);

        bAddQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etEnterQuestion.getText().toString().equals("") || etChoice1.getText().toString().equals("") || etChoice2.getText().toString().equals("") || etChoice3.getText().toString().equals("") || etChoice4.getText().toString().equals("") || rgChoices.getCheckedRadioButtonId() < 0) {
                    Toast.makeText(AddQuestionActivity.this, "Please fill in all areas before adding question.", Toast.LENGTH_SHORT).show();
                }
                else {
                    clickCount++;
                    if (rbChoice1.isChecked()) {
                        correctChoice = etChoice1.getText().toString();
                    }
                    if (rbChoice2.isChecked()) {
                        correctChoice = etChoice2.getText().toString();
                    }
                    if (rbChoice3.isChecked()) {
                        correctChoice = etChoice3.getText().toString();
                    }
                    if (rbChoice4.isChecked()) {
                        correctChoice = etChoice4.getText().toString();
                    }
                    HashMap postData = new HashMap();
                    postData.put("txtQuestion", etEnterQuestion.getText().toString());
                    postData.put("txtChoice1", etChoice1.getText().toString());
                    postData.put("txtChoice2", etChoice2.getText().toString());
                    postData.put("txtChoice3", etChoice3.getText().toString());
                    postData.put("txtChoice4", etChoice4.getText().toString());
                    postData.put("txtChoiceCorrect", correctChoice);
                    postData.put("txtId", quizId);
                    postData.put("mobile", "android");
                    PostResponseAsyncTask taskAdd = new PostResponseAsyncTask(AddQuestionActivity.this, postData, new AsyncResponse() {
                        @Override
                        public void processFinish(String s) {
                            if (s.contains("success")) {
                                etEnterQuestion.setText("");
                                etChoice1.setText("");
                                etChoice2.setText("");
                                etChoice3.setText("");
                                etChoice4.setText("");
                                rbChoice1.setChecked(false);
                                rbChoice2.setChecked(false);
                                rbChoice3.setChecked(false);
                                rbChoice4.setChecked(false);
                            } else {
                                Toast.makeText(AddQuestionActivity.this, "An error occurred. Question could not be added.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    taskAdd.execute(ServerURL.getURL() + "addquestion.php");
                }
            }
        });

        bFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickCount == 0) {
                    Toast.makeText(AddQuestionActivity.this, "Quiz must contain at least one question.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent in = new Intent(AddQuestionActivity.this, UserAreaActivity.class);
                    AddQuestionActivity.this.startActivity(in);
                    AddQuestionActivity.this.finish();
                }
            }
        });

    }

}
