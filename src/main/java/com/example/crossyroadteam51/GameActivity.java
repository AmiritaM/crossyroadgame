package com.example.crossyroadteam51;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    private TextView nameText;
    private TextView diffText;
    private TextView charText;

    public TextView getNameText() {
        return nameText;
    }

    public TextView getDiffText() {
        return diffText;
    }

    public TextView getCharText() {
        return charText;
    }

    public TextView getLivesText() {
        return livesText;
    }

    private TextView livesText;

    private int lives;

    private ImageView charactersprite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameactivity);
        Button startButton = findViewById(R.id.button2);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameActivity.this, MainGameActivity.class));
            }
        });

        nameText = (TextView) findViewById(R.id.textname);
        diffText = (TextView) findViewById(R.id.textdifficulty);
        charText = (TextView) findViewById(R.id.textchar);
        livesText = (TextView) findViewById(R.id.textlives);
        charactersprite = (ImageView) findViewById(R.id.imageView2);

        String name;
        String difficulty;
        String character;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                name = null;
                difficulty = null;
                character = null;
            } else {
                name = extras.getString("playerName");
                difficulty = extras.getString("difficulty");
                character = extras.getString("character");
            }
        } else {
            name = (String) savedInstanceState.getSerializable("playerName");
            difficulty = (String) savedInstanceState.getSerializable("difficulty");
            character = (String) savedInstanceState.getSerializable("character");
        }

        nameText.setText(nameText.getText() + name);
        diffText.setText(diffText.getText() + difficulty);
        charText.setText(charText.getText() + character);

        if (difficulty.equals("Easy")) {
            livesText.setText(livesText.getText() + "3");
            lives = 3;
        } else if (difficulty.equals("Medium")) {
            livesText.setText(livesText.getText() + "2");
            lives = 2;
        } else {
            livesText.setText(livesText.getText() + "1");
            lives = 1;
        }

        if (character.equals("Ladybug")) {
            charactersprite.setImageResource(R.drawable.ladybug);
        } else if (character.equals("Butterfly")) {
            charactersprite.setImageResource(R.drawable.butterfly);
        } else if (character.equals("Frog")) {
            charactersprite.setImageResource(R.drawable.frog);
        }
        Intent intent = new Intent(GameActivity.this, MainGameActivity.class);
        intent.putExtra("character", character);
        intent.putExtra("lives", lives);
        startActivity(intent);
    }
    
    public String nullName(TextView nameText) {
        if (nameText == null) {
            return null;
        } else {
            return (String) nameText.getText();
        }
    }

    public String nullChar(TextView charText) {
        if (charText == null) {
            return null;
        } else {
            return (String) charText.getText();
        }
    }

    public String nullDiff(TextView diffText) {
        if (diffText == null) {
            return null;
        } else {
            return (String) diffText.getText();
        }
    }

    public String nullLives(TextView livesText) {
        if (livesText == null) {
            return null;
        } else {
            return (String) livesText.getText();
        }
    }
}
