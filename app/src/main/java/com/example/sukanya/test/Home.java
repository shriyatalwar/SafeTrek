package com.example.sukanya.test;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

  //  private static final int RESULT_OK =1 ;
    View myView;

    //  ------ SIREN -----
    ImageButton b_siren;
    MediaPlayer siren;
    // ---- VOICE TO TEXT -----
    protected static final int RESULT_SPEECH = 1;
    private ImageButton voice;
    private TextView voiceText;

    int fl=0;
    String num1=null,num2=null,num3=null,num4=null,num5=null;
    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView=inflater.inflate(R.layout.fragment_home, container, false);
        b_siren=(ImageButton)myView.findViewById(R.id.alert);
        siren= MediaPlayer.create(MainActivity.getContextOfApplication(),R.raw.siren);
        // ----- VOICE RECOGNITION -----
        voiceText = (TextView) myView.findViewById(R.id.voiceText);
        voice = (ImageButton) myView.findViewById(R.id.voice);
        num1=EmergencyContacts.getN1();
        num2=EmergencyContacts.getN2();
        num3=EmergencyContacts.getN3();
        num4=EmergencyContacts.getN4();
        num5=EmergencyContacts.getN5();




        b_siren.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendSMS();
                Intent i = new Intent(MainActivity.getContextOfApplication(), Alarm.class);
                i.putExtra("message",fl);
                startActivity(i);
            }
        });

        voiceRecog();
        return myView;
    }

    private void sendSMS() {
        int j;
        String numb = "0";
        for(j=0;j<=5;j++)
        {   if(j==1 && EmergencyContacts.getF1()==1)
            numb = EmergencyContacts.getN1();
        else if(j==2 && EmergencyContacts.getF2()==1)
            numb = EmergencyContacts.getN2();
        else if(j==3 && EmergencyContacts.getF3()==1)
            numb = EmergencyContacts.getN3();
        else if(j==4 && EmergencyContacts.getF4()==1)
            numb = EmergencyContacts.getN4();
        else if(j==5 && EmergencyContacts.getF5()==1)
            numb = EmergencyContacts.getN5();
            if(numb!="0")
            {
                try {
                    SmsManager.getDefault().sendTextMessage(numb, null, "HELP ME! I AM IN DANGER", null, null);
                } catch (Exception e) {


                }
            }}
    }


    private void voiceRecog() {
        voice.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");

                try {
                    startActivityForResult(intent, RESULT_SPEECH);
                    voiceText.setText("");
                } catch (ActivityNotFoundException a) {
                    Toast t = Toast.makeText(MainActivity.getContextOfApplication(),
                            "Oops! Your device doesn't support Speech to Text",
                            Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_SPEECH: {

                    ArrayList<String> text = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String msg=text.get(0);
                    voiceText.setText(msg);
                if(msg.equalsIgnoreCase("help")){
                    sendSMS();
                    Intent i = new Intent(MainActivity.getContextOfApplication(), Alarm.class);
                    i.putExtra("message",fl);
                    startActivity(i);
                }

            }

        }
    }
}
