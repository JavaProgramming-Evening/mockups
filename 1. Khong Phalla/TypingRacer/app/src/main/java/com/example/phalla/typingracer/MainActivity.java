package com.example.phalla.typingracer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText name;
    private static final String PREFS_NAME = "Player";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.txtName);
    }

    /** Called when the user touches the button */
    public void sendMessage(View view) {
        if( (name.getText().toString().trim()).equals("") ){
            name.setText("");
            name.requestFocus();
        } else {
            // Restore preferences
            SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", name.getText().toString());
            editor.commit();

            Intent intent = new Intent(getApplicationContext(), ModelActivity.class);
            startActivity(intent);
        }
    }


}
