package com.quizgame.triviamaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    final String LOG = "RegisterActivity";
    EditText etAge;
    EditText etName;
    EditText etUsername;
    EditText etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etAge = (EditText) findViewById(R.id.etAge);
        etName = (EditText) findViewById(R.id.etName);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(etAge.getText().toString().equals("") || etName.getText().toString().equals("") || etUsername.getText().toString().equals("") || etPassword.getText().toString().equals("")) {
            Toast.makeText(RegisterActivity.this, "Please fill in all areas of information.", Toast.LENGTH_SHORT).show();
        }
        else {
            HashMap postData = new HashMap();
            postData.put("txtName", etName.getText().toString());
            postData.put("txtAge", etAge.getText().toString());
            postData.put("txtUsername", etUsername.getText().toString());
            postData.put("txtPassword", etPassword.getText().toString());
            postData.put("mobile", "android");

            PostResponseAsyncTask task = new PostResponseAsyncTask(RegisterActivity.this, postData, new AsyncResponse() {
                @Override
                public void processFinish(String s) {
                    Log.d(LOG, s);
                    if (s.contains("success")) {
                        Toast.makeText(RegisterActivity.this, "Account successfully registered.", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(in);
                        RegisterActivity.this.finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "An error occurred. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            task.execute(ServerURL.getURL() + "register.php");
        }
    }
}
