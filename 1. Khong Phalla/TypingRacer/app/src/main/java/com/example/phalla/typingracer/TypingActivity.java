package com.example.phalla.typingracer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TypingActivity extends AppCompatActivity {

    EditText editText;
    int carID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typing);

        Intent intent = getIntent();
        carID = intent.getExtras().getInt("car_position");

        editText = (EditText) findViewById(R.id.inputText);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                boolean handled = false;
                if(actionId == EditorInfo.IME_ACTION_SEND){
                    //Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT).show();
                    handled = true;
                }
                return handled;
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Toast.makeText(getBaseContext(), "befor text changed", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Toast.makeText(getBaseContext(), "on text changed", Toast.LENGTH_SHORT).show();
                if (editText.getText().length() < 10){
                    move();
                } else {
                    move();
                    editText.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //Toast.makeText(getBaseContext(), "after text changed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void showSoftKeyboard(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public void move(){
        ImageView image = (ImageView)findViewById(R.id.carDrive);
        /*Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        image.startAnimation(animation);*/

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x - 250; // distance of object to move

        int numChar = editText.getText().length(); // count number of character input

        int percentType = (100*numChar)/10; // get percentage of character

        float numMove = (width*percentType)/100; // get number of step for move object

        //Log.w("Size Width", "move: "+ width);
        //Toast.makeText(getApplicationContext(), "move: "+ numMove, Toast.LENGTH_SHORT).show();

        image.animate().translationX(numMove).setDuration(500).start();

        if (numChar == 10){
            showDialog();
        }
    }

    public void showDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(carID + ": Please check your score");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(getApplicationContext(), "FIRE Button Click", Toast.LENGTH_SHORT).show();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }
}
