package ftn.quizapp.activities;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.checkerframework.checker.nullness.qual.NonNull;

import ftn.quizapp.R;
import ftn.quizapp.model.User;

public class LoginActivity extends Activity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        mAuth = FirebaseAuth.getInstance();

        Button submitButton = findViewById(R.id.submitLogin);
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emaill = email.getText().toString().trim();
                String passwordd = password.getText().toString().trim();

                if (TextUtils.isEmpty(emaill)) {
                    email.setError("Enter your email");
                    email.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(passwordd)) {
                    password.setError("Enter your password");
                    password.requestFocus();
                    return;
                }
                loginUser(emaill, passwordd);
            }
        });
    }

    private void loginUser(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "SUCCESS");
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                        } else {
                            Log.w(TAG, "ERROR", task.getException());
                            Toast.makeText(LoginActivity.this,
                                    "Authentication failed. Try again.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
