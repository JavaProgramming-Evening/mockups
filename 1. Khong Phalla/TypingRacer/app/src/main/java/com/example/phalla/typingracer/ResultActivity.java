package com.example.phalla.typingracer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "Player";
    TextView playerName;
    TextView timeSpent;
    TextView typingStatus;
    Button buttonResetModel;
    Button buttonResetTyping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        playerName = (TextView) findViewById(R.id.name);
        timeSpent = (TextView) findViewById(R.id.time);
        typingStatus = (TextView) findViewById(R.id.typingStat);

        SharedPreferences shared = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String player = shared.getString("name", "");

        Intent intent = getIntent();

        playerName.setText(player);
        timeSpent.setText("Time spent : "+ (intent.getExtras().getString("time")));
        typingStatus.setText("Number Charector : "+ (intent.getExtras().getInt("numChar")));

        buttonResetModel = (Button) findViewById(R.id.btnResetModel);
        buttonResetModel.setOnClickListener(onClick);

        buttonResetTyping = (Button) findViewById(R.id.btnResetTyping);
        buttonResetTyping.setOnClickListener(onClick);
    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()){
                case R.id.btnResetModel:
                    intent = new Intent(getApplicationContext(), ModelActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btnResetTyping:
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}
