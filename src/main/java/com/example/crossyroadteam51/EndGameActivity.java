package com.example.crossyroadteam51;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class EndGameActivity extends AppCompatActivity {
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game2);

        score = 0;
        boolean won = false;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                score = 0;
                won = false;
            } else {
                score = extras.getInt("score");
                won = extras.getBoolean("won");
            }
        }
        TextView scoreText = findViewById(R.id.score_text);
        TextView gameOverText = findViewById(R.id.game_over);
        if (won) {
            gameOverText.setText("Game over, you won!");
        } else {
            gameOverText.setText("Game over, you lost!");
        }
        scoreText.setText("Score: " + score);

        Button restart = (Button) findViewById(R.id.button3);
        Button exit = (Button) findViewById(R.id.button4);

        restart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(EndGameActivity.this, MainActivity.class));
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                closeApp();
            }
        });
    }
    public void closeApp() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
        System.exit(0);
    }

    public int getScore() {
        return score;
    }
}