package com.quizgame.triviamaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUsername;
    EditText etPassword;
    final String LOG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final TextView registerLink = (TextView) findViewById(R.id.tvRegisterHere);
        bLogin.setOnClickListener(this);

        registerLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });


    }

    @Override
    public void onClick(View v) {
        HashMap postData = new HashMap();

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        final Intent intent1 = new Intent (this, UserAreaActivity.class);
        intent1.putExtra("enteredPassword", password);

        //postData.put("mobile", "android");
        postData.put("txtUsername", username);
        postData.put("txtPassword", password);

        PostResponseAsyncTask task = new PostResponseAsyncTask(LoginActivity.this, postData, new AsyncResponse() {
            @Override
            public void processFinish(String result) {
                Log.d(LOG, result);
                if(result.contains("success")){
                    Toast.makeText(LoginActivity.this, "Logged in!", Toast.LENGTH_LONG).show();
                    startActivity(intent1);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Login Failed. Please try again.", Toast.LENGTH_LONG).show();
                }
            }
        });
        task.execute(ServerURL.getURL() + "login.php");
    }
}