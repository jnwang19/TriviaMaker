package com.quizgame.triviamaker;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.ArrayList;
import java.util.HashMap;

public class DeleteQuestionActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    ArrayList<Question> questionList;
    ListView lvQuestionList;
    Button bDone;
    QuizInfo quiz;
    String id;
    FunDapter<Question> adapter;
    final String LOG = "DeleteQuestionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_question);

        quiz = (QuizInfo) getIntent().getSerializableExtra("quiz");
        id = Integer.toString(quiz.id);

        lvQuestionList = (ListView) findViewById(R.id.lvQuestionList);
        bDone = (Button) findViewById(R.id.bDone);

        HashMap postData = new HashMap();
        postData.put("txtId", id);

        PostResponseAsyncTask taskRead = new PostResponseAsyncTask(DeleteQuestionActivity.this, postData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                questionList = new JsonConverter<Question>().toArrayList(s, Question.class);

                BindDictionary<Question> dict = new BindDictionary<Question>();
                dict.addStringField(R.id.tvQuestion, new StringExtractor<Question>() {
                    @Override
                    public String getStringValue(Question item, int position) {
                        return item.question;
                    }
                });

                adapter = new FunDapter<>(DeleteQuestionActivity.this, questionList, R.layout.layout_list2, dict);
                lvQuestionList.setAdapter(adapter);
                lvQuestionList.setOnItemClickListener(DeleteQuestionActivity.this);
            }
        });
        taskRead.execute(ServerURL.getURL() + "questions.php");

        bDone.setOnClickListener(DeleteQuestionActivity.this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Question selectedQuestion = questionList.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));

        builder.setTitle("Delete Question Alert");
        builder.setMessage("Are you sure you want to delete this question?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                questionList.remove(selectedQuestion);
                adapter.notifyDataSetChanged();

                HashMap postData = new HashMap();
                postData.put("txtQuestion", selectedQuestion.question);
                postData.put("mobile", "android");

                PostResponseAsyncTask taskUpdate = new PostResponseAsyncTask(DeleteQuestionActivity.this, postData, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {
                        if(s.contains("success")){
                            Log.d(LOG, s);
                            Toast.makeText(DeleteQuestionActivity.this, "Question successfully deleted", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                taskUpdate.execute(ServerURL.getURL() + "deletequestion.php");
            }
        });
        builder.setNegativeButton("No", null);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.DKGRAY);
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.DKGRAY);
            }
        });
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        Intent in = new Intent(DeleteQuestionActivity.this, UserAreaActivity.class);
        startActivity(in);
    }
}
