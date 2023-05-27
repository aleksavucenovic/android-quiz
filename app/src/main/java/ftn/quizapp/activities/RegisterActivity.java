package ftn.quizapp.activities;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

import ftn.quizapp.R;
import ftn.quizapp.model.GameStats;
import ftn.quizapp.model.User;

public class RegisterActivity extends Activity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
////            reload();
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        mAuth = FirebaseAuth.getInstance();

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

                registerUserAuth(emaill, passwordd, usernamee);

            }
        });

    }



    private void registerUserAuth(String email, String password, String username) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "SUCCESS");
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            String userId = user.getUid();
                            registerUserToDB(email, password, username, userId);
                        } else {
                            Log.w(TAG, "ERROR", task.getException());
                            Toast.makeText(
                                    RegisterActivity.this,
                                    "Authentication failed. Try again.",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
                });
    }

    private void registerUserToDB(String email, String password, String username, String id) {
        mDatabase = FirebaseDatabase
                .getInstance("https://quiz-app-781b7-default-rtdb.europe-west1.firebasedatabase.app/")
                .getReference();

        HashMap<String, GameStats> gameStats = new HashMap<>();
        GameStats gameStats2 = new GameStats();
        gameStats2.setScoreRange(0);
        gameStats2.setTotalGamesPlayed(0);
        gameStats2.setGamesWon(0);
        gameStats2.setGamesLost(0);
        gameStats.put("game", gameStats2);

        User user = new User(username, email, password, id, gameStats);
        mDatabase.child("users").child(id).setValue(user);

        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
        startActivity(intent);
    }


}
