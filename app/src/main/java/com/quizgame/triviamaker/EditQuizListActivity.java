package com.quizgame.triviamaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class EditQuizListActivity extends AppCompatActivity implements AsyncResponse, AdapterView.OnItemClickListener {

    private ArrayList<QuizInfo> quizList;
    private ListView lvQuiz;
    TextView tvEmpty;
    String username;
    final String LOG = "EditQuizListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_list);
        tvEmpty = (TextView) findViewById(R.id.tvEmpty);

        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");

        HashMap postData = new HashMap();
        postData.put("txtUsername", username);

        PostResponseAsyncTask taskRead = new PostResponseAsyncTask(EditQuizListActivity.this, postData, this);
        taskRead.execute(ServerURL.getURL() + "quizinfotest.php");
    }

    @Override
    public void processFinish(String s) {
        Log.d(LOG, s);
        if(s.contains("<br />")){
            tvEmpty.setVisibility(View.VISIBLE);
        }
        else {
            quizList = new JsonConverter<QuizInfo>().toArrayList(s, QuizInfo.class);

            BindDictionary<QuizInfo> dict = new BindDictionary<QuizInfo>();
            dict.addStringField(R.id.tvQuizName, new StringExtractor<QuizInfo>() {
                @Override
                public String getStringValue(QuizInfo item, int position) {
                    return item.quizname;
                }
            });

            dict.addStringField(R.id.tvDateCreated, new StringExtractor<QuizInfo>() {
                @Override
                public String getStringValue(QuizInfo item, int position) {
                    return item.datecreated;
                }
            });

            FunDapter<QuizInfo> adapter = new FunDapter<>(EditQuizListActivity.this, quizList, R.layout.layout_list, dict);

            lvQuiz = (ListView) findViewById(R.id.lvQuiz);
            lvQuiz.setAdapter(adapter);
            lvQuiz.setOnItemClickListener(this);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        QuizInfo selectedQuiz = quizList.get(position);
        Intent intent = new Intent(EditQuizListActivity.this, EditQuizActivity.class);
        intent.putExtra("quiz", (Serializable) selectedQuiz);
        startActivity(intent);
    }
}
