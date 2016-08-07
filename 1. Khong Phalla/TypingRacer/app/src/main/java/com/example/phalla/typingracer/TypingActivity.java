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
import android.text.method.ScrollingMovementMethod;
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

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TypingActivity extends AppCompatActivity {

    int carID = 0;
    EditText pContent;
    int maxInput;
    int currentPosition = 0;
    String paragraphs = "";
    TextView clock;
    int second = 0, minut = 0, hour = 0;
    Timer timerTicker;
    ImageView carImage;

    String[] carModels = {"model_1", "model_2", "model_3", "model_4", "model_5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typing);

        Intent intent = getIntent();
        carID = intent.getExtras().getInt("car_position");

        int idImage = getResources().getIdentifier("@drawable/" + carModels[carID], null, getPackageName());
        carImage = (ImageView)findViewById(R.id.carDrive);
        carImage.setImageResource(idImage);

        clock = (TextView) findViewById(R.id.timer);
        // set text
        paragraphs = getParagraphs();
        maxInput = paragraphs.length();

        pContent = (EditText) findViewById(R.id.pViewer);
        pContent.setText(paragraphs);
        pContent.setMovementMethod(new ScrollingMovementMethod());
        pContent.requestFocus();
        pContent.setSelection(currentPosition+1);

        pContent.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                String character = ((char) keyEvent.getUnicodeChar())+"";
                String currentCharacter = paragraphs.substring(maxInput);
                if ( currentPosition < maxInput  ){
                    currentCharacter = paragraphs.substring(currentPosition, currentPosition + 1);
                }

                if ( keyEvent.getAction() == KeyEvent.ACTION_DOWN ){
                    if ( character.equals(currentCharacter) ){
                        if ( currentPosition < maxInput  ){
                            pContent.getText().delete(currentPosition, currentPosition + 1);
                        }else{
                            pContent.getText().delete(maxInput-1, maxInput);
                        }

                    }else{
                        if ( currentPosition <= maxInput  ){
                            return true;
                        }
                    }
                }

                if ( keyEvent.getAction() == KeyEvent.ACTION_UP ){
                    if ( character.equals(currentCharacter) ){
                        currentPosition++;
                        move();

                        if ( currentPosition < maxInput  ){
                            pContent.setSelection(currentPosition+1);
                        }else{
                            pContent.setSelection(maxInput);
                        }
                    }
                }

                return false;
            }
        });

        /* show time */
        timerTicker = new Timer();
        timerTicker.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        second++;
                        setTime();
                    }
                });
            }
        }, 0, 1000);
    }

    public void move(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x - 250; // distance of object to move

        int percentType = (100*currentPosition)/maxInput; // get percentage of character

        float numMove = (width*percentType)/100; // get number of step for move object

        carImage.animate().translationX(numMove).setDuration(500).start();

        if (currentPosition == maxInput){
            pContent.setEnabled(false);
            showDialog();
        }
    }

    public void showDialog(){
        timerTicker.cancel();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setMessage(carID + ": Please check your score");
        builder.setMessage("Check your result");
        builder.setPositiveButton("Result", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("time", clock.getText().toString());
                startActivity(intent);
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

    public String getParagraphs(){
        String[] poems = {
            "To love at a distance can be torturous, as you always desire closeness to your beloved.",
            "I see you in my thoughts and dreams, When I awake, how real it seems. You aren't here to comfort me,",
            "How do you know when your love is true and that you are not just fooling yourself?",
            "For so long, I wished for the day. The day that our love would find its way. From my heart",
            "Two roads diverged in a yellow wood, And sorry I could not travel both And be one traveler,",
            "Don't go far off, not even for a day, because -- because -- I don't know how to say it:",
            "Drunk as drunk on turpentine From your open kisses, Your wet body wedged Between my wet body and the strake Of our boat"
        };

        /*String[] poems = {
            "To love at a distance can be torturous, as you always desire closeness to your beloved. But closeness and distance are not only physical definitions of space but also how we understand our heart. So while the physical distance between us may be great, that space may always be small within the heart. Holding close to the ones we love and trusting in that steadfastness can calm our hearts and soothe our minds no matter how far the physical distance between us. While loving another from far away may be difficult, faith in our love may keep us strong.",
            "I see you in my thoughts and dreams,When I awake, how real it seems. You aren't here to comfort me, But, soon I hope you will be. No one truly knows or understands, You have my heart in your hands. My love is what you truly own, Come soon and make our house a home. Inside those walls you are doing your time, Not being here with me is your only true crime. Others in your life will come and go, But my love is true, and I'm sure you know.",
            "How do you know when your love is true and that you are not just fooling yourself? How do you know that you are not merely infatuated with each other? How do you know that this is a relationship that has lasting power? The truth is that these are only questions that can be answered with experience. It may take a few bad relationships before you know how to be in a good one. Another tip is to try to use your head before you get your heart involved. If it makes sense on paper,",
            "For so long, I wished for the day. The day that our love would find its way. From my heart and into your soul, The feeling so strong, I had no control. When then that day came, when I found you again, I vowed never to make the same mistake. I knew I would never let you go, For my life is now complete in a way I cannot show. For eternity I will spend making you believe, You are the sole reason that I breathe. My life is yours, my hopes and desires too. Until my dying day,",
            "Two roads diverged in a yellow wood, And sorry I could not travel both And be one traveler, long I stood And looked down one as far as I could To where it bent in the undergrowth; Then took the other, as just as fair, And having perhaps the better claim Because it was grassy and wanted wear, Though as for that the passing there Had worn them really about the same, And both that morning equally lay In leaves no step had trodden black. Oh, I kept the first for another day! Yet knowing how way leads on to way",
            "Don't go far off, not even for a day, because -- because -- I don't know how to say it: a day is long and I will be waiting for you, as in an empty station when the trains are parked off somewhere else, asleep. Don't leave me, even for an hour, because then the little drops of anguish will all run together, the smoke that roams looking for a home will drift into me, choking my lost heart. Oh, may your silhouette never dissolve on the beach; may your eyelids never flutter into the empty distance.",
            "Drunk as drunk on turpentine From your open kisses, Your wet body wedged Between my wet body and the strake Of our boat that is made of flowers, Feasted, we guide it - our fingers Like tallows adorned with yellow metal - Over the sky's hot rim, The day's last breath in our sails. Pinned by the sun between solstice And equinox, drowsy and tangled together We drifted for months and woke With the bitter taste of land on our lips, Eyelids all sticky, and we longed for lime And the sound of a rope Lowering a bucket down its well."
        };*/

        Random rnd = new Random();
        int index = rnd.nextInt(poems.length);

        return poems[index];
    }

    private void setTime(){
        int pSecond = second;
        int pMinut = 0;
        int pHour = 0;

        if( second >= 60 ) {
            pSecond = second % 60;
            pMinut = second / 60;

            if( pMinut >= 60){
                pHour = pMinut / 60;
                pMinut = pMinut % 60;
            }
        }

        clock.setText(pHour +" h : "+ pMinut +" m : "+ pSecond +" s");
    }
}
