package com.quizgame.triviamaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.ArrayList;

public class UserAreaActivity extends AppCompatActivity implements AsyncResponse{
    final String LOG = "UserAreaActivity";
    EditText etUsername;
    EditText etAge;
    Button bEdit;
    private ArrayList<UserInfo> userList;
    String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etAge = (EditText) findViewById(R.id.etAge);
        final TextView welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMsg);
        final Button bPlay = (Button) findViewById(R.id.bPlay);
        final Button bCreate = (Button) findViewById(R.id.bCreate);
        bEdit = (Button) findViewById(R.id.bEdit);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null) {
        }
        else {
            password = bundle.getString("enteredPassword");
            PostResponseAsyncTask taskRead = new PostResponseAsyncTask(UserAreaActivity.this, this);
            taskRead.execute(ServerURL.getURL() + "userinfo.php");
        }

        bPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(UserAreaActivity.this, QuizListActivity.class);
                registerIntent.putExtra("username", etUsername.getText().toString());
                UserAreaActivity.this.startActivity(registerIntent);
            }
        });
        bCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createIntent = new Intent(UserAreaActivity.this, CreateQuizActivity.class);
                createIntent.putExtra("username", etUsername.getText().toString());
                UserAreaActivity.this.startActivity(createIntent);
            }
        });
        bEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(UserAreaActivity.this, EditQuizListActivity.class);
                editIntent.putExtra("username", etUsername.getText().toString());
                UserAreaActivity.this.startActivity(editIntent);
            }
        });
    }

    @Override
    public void processFinish(String s) {
            Log.d(LOG, s);
            userList = new JsonConverter<UserInfo>().toArrayList(s, UserInfo.class);

        for(int i = 0; i < userList.size(); i++) {
            if(userList.get(i).PASSWORD.equals(password)) {
                etUsername.setText(userList.get(i).username, TextView.BufferType.EDITABLE);
                etAge.setText(Integer.toString(userList.get(i).age), TextView.BufferType.EDITABLE);
            }
        }

    }

}
