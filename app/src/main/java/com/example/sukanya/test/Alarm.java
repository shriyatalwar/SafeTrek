package com.example.sukanya.test;

/**
 * Created by Shriya Talwar on 9/15/2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sukanya.test.MainActivity;
import com.example.sukanya.test.R;

/**
 * Created by Shriya Talwar on 9/15/2016.
 */

public class Alarm extends Activity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    //Alarm
    MediaPlayer siren;
    int flag = 0;
    ImageButton but;
    //gestures
    private static final String DEBUG_TAG = "Gestures";
    private GestureDetectorCompat mDetector;

    TextView tv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm);
        Intent i = getIntent();
        //  int c = i.getIntExtra("message",0);
        // tv = (TextView)findViewById(R.id.intent);
        // tv.setText(c);
        //siren = MediaPlayer.create(this,R.raw.siren);
        play_siren();// ---- for alarm
        but=(ImageButton)findViewById(R.id.siren);
        but.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        stop();
                    }
                }
        );
        // for detecting gestures
        mDetector = new GestureDetectorCompat(this,this);
        // Set the gesture detector as the double tap listener.
        mDetector.setOnDoubleTapListener(this);

    }

    private void play_siren() {

        // stop_playing();
        siren = MediaPlayer.create(this,R.raw.siren);
        siren.start();
    }

    private void stop_playing() {
        if(siren!=null)
            siren.stop();
        siren.reset();
        siren.release();
        siren=null;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }
    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        stop();
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }
    void stop(){
        siren.pause();
        flag=1;
        Intent in = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(in);
    }
}

