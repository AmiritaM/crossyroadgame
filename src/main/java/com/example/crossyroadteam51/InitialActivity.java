package com.example.crossyroadteam51;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InitialActivity extends AppCompatActivity {

    private RadioButton difficultyButton;
    private RadioButton characterButton;
    private static ImageView characterimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initialactivity);

        EditText username = findViewById(R.id.textView);
        RadioGroup difficultyGroup = findViewById(R.id.radioGroup);
        RadioGroup characterGroup = findViewById(R.id.radioGroup2);
        Button continueButton = findViewById(R.id.button);
        characterimage = findViewById(R.id.imageView);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = username.getText().toString();
                if (name.isEmpty() || name.trim().length() == 0) {
                    Toast.makeText(InitialActivity.this, "Please enter a valid name!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    int difficultyId = difficultyGroup.getCheckedRadioButtonId();
                    difficultyButton = findViewById(difficultyId);
                    String difficulty = difficultyButton.getText().toString();

                    int characterId = characterGroup.getCheckedRadioButtonId();
                    characterButton = findViewById(characterId);
                    String character = characterButton.getText().toString();

                    Intent intent = new Intent(InitialActivity.this, GameActivity.class);
                    intent.putExtra("playerName", name);
                    intent.putExtra("difficulty", difficulty);
                    intent.putExtra("character", character);
                    startActivity(intent);
                }
            }
        });
    }

    public void onRadioClickLadybug(View view) {
        characterimage.setImageResource(R.drawable.ladybug);
    }

    public void onRadioClickButterfly(View view) {
        characterimage.setImageResource(R.drawable.butterfly);
    }

    public void onRadioClickFrog(View view) {
        characterimage.setImageResource(R.drawable.frog);
    }
}
