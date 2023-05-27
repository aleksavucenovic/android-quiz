package ftn.quizapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ftn.quizapp.R;
import ftn.quizapp.model.User;

public class LoginActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        Button submitButton = findViewById(R.id.submitLogin);
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usernamee = username.getText().toString().trim();
                String passwordd = password.getText().toString().trim();

                if (TextUtils.isEmpty(usernamee)) {
                    username.setError("Enter your username");
                    username.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(passwordd)) {
                    password.setError("Enter your password");
                    password.requestFocus();
                    return;
                }
//                loginUser(userName, password);
            }
        });
    }

    private void loginUser(String username, String password){


    }
}
