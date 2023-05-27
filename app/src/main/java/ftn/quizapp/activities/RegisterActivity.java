package ftn.quizapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ftn.quizapp.R;

public class RegisterActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        Button submitButton = findViewById(R.id.submitLogin);
        EditText username = findViewById(R.id.username);
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        EditText repeatedPassword = findViewById(R.id.repeatedPassword);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usernamee = username.getText().toString().trim();
                String emaill = email.getText().toString().trim();
                String passwordd = password.getText().toString().trim();
                String repeatedPasswordd = repeatedPassword.getText().toString().trim();


                if (TextUtils.isEmpty(usernamee)) {
                    username.setError("Enter your username");
                    username.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(emaill)) {
                    email.setError("Enter your email");
                    email.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(passwordd)) {
                    password.setError("Enter your password");
                    password.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(repeatedPasswordd)) {
                    repeatedPassword.setError("Enter your repeated password");
                    repeatedPassword.requestFocus();
                    return;
                }

                if (!passwordd.equals(repeatedPasswordd)) {
                    repeatedPassword.requestFocus();
                    Toast.makeText(RegisterActivity.this, "Password are not the same !!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                registerUser(usernamee, emaill, passwordd);
            }
        });

    }


    private void registerUser(String username, String email, String password){

    }



}
