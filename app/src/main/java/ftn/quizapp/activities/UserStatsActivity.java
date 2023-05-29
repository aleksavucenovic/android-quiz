package ftn.quizapp.activities;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Map;

import ftn.quizapp.R;
import ftn.quizapp.fragments.FragmentBasicDrawer;
import ftn.quizapp.model.GameStats;
import ftn.quizapp.model.User;

public class UserStatsActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_stats);

        FragmentBasicDrawer fragment = new FragmentBasicDrawer();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.navigationView, fragment);
        fragmentTransaction.commit();

        LinearLayout linearLayout = findViewById(R.id.layoutStats);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();

        mDatabase = FirebaseDatabase
                .getInstance("https://quiz-app-781b7-default-rtdb.europe-west1.firebasedatabase.app/")
                .getReference();
        mDatabase.child("users").child(userId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task){
                DataSnapshot dataSnapshot = task.getResult();
                if (dataSnapshot.exists()) {
                    User user = dataSnapshot.getValue(User.class);
                    for (Map.Entry<String, GameStats> entry : user.getGameStats().entrySet()) {
                        String key = entry.getKey();
                        GameStats playerStats = entry.getValue();
                        int gameLost = playerStats.getGamesLost();
                        int gameWon = playerStats.getGamesWon();
                        int scoreRange = playerStats.getScoreRange();
                        int totalGamesPlayed = playerStats.getTotalGamesPlayed();

                        TextView textView = new TextView(UserStatsActivity.this);
                        textView.setText(
                                "\n--> " + key +
                                "\nGame lost: " + gameLost +
                                "\nGame won: " + gameWon +
                                "\nScore range: " + scoreRange +
                                "\nTotal games played: " + totalGamesPlayed
                        );
                        textView.setTextSize(20);
                        textView.setTypeface(null, Typeface.BOLD);
                        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        linearLayout.addView(textView);
                    }
                } else {
                }
            }
        });
    }
}
