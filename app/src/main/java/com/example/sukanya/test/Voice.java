package com.example.sukanya.test;


import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class Voice extends Fragment {


    public Voice() {
        // Required empty public constructor
    }
    View myView;
    int num=0;
    // ----- SOUND RECORDING -----
    ImageButton stop,record;
    private MediaRecorder myAudioRecorder;
    private String outputFile = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // --- SOUND RECORDING ----
    myView=inflater.inflate(R.layout.fragment_voice, container, false);
        stop=(ImageButton)myView.findViewById(R.id.stop);
        record=(ImageButton)myView.findViewById(R.id.record);
        soundRecorder();
        // Inflate the layout for this fragment
        return myView;
    }

    private void soundRecorder() {
        Random ran=new Random();
        num=ran.nextInt(100);
        stop.setEnabled(false);
        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/alert"+num+".3gp";
        myAudioRecorder=new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(outputFile);

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    myAudioRecorder.prepare();
                    myAudioRecorder.start();
                }


                catch (IllegalStateException e) {
                    e.printStackTrace();
                }

                catch (IOException e) {
                    e.printStackTrace();
                }

                record.setEnabled(false);
                stop.setEnabled(true);

                Toast.makeText(MainActivity.getContextOfApplication(), "Recording started", Toast.LENGTH_LONG).show();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioRecorder.stop();
                myAudioRecorder.release();
                myAudioRecorder  = null;
                stop.setEnabled(false);
                record.setEnabled(true);
                Toast.makeText(MainActivity.getContextOfApplication(), "Audio recorded successfully",Toast.LENGTH_LONG).show();
            }
        });


    }
}
