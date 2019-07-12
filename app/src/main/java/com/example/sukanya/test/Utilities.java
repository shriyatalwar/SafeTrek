package com.example.sukanya.test;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class Utilities extends Fragment {

Camera cam;
    ImageButton flash1,timer1,tips1,call1;
    int flag=0,fl=0;
    public Utilities() {
        // Required empty public constructor
    }
    View myView;

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView= inflater.inflate(R.layout.fragment_utilities, container, false);
        flash1=(ImageButton) myView.findViewById(R.id.torch);
        timer1=(ImageButton) myView.findViewById(R.id.timer);
        tips1=(ImageButton) myView.findViewById(R.id.tips);
        call1=(ImageButton) myView.findViewById(R.id.call);
        flash();
        time();
        tips();
        call();

        return myView;
    }

    private void time() {
        timer1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.contextOfApplication, Timer.class);
                i.putExtra("message", fl);
                startActivity(i);
            }
        });

    }

    private void call() {
        call1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.contextOfApplication, CallCon.class);
                i.putExtra("message", fl);
                startActivity(i);
            }
        });

    }

    private void tips() {
        tips1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.contextOfApplication, Tips.class);
                in.putExtra("message", fl);
                startActivity(in);
            }
        });
    }

    private void flash() {
        flash1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(flag==0) {
                    cam= Camera.open();
                    Camera.Parameters p = cam.getParameters();
                    p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    cam.setParameters(p);
                    cam.startPreview();
                    flag=1;
                }
                else
                { flag=0;
                    cam.stopPreview();
                    cam.release();
                }
            }
        });
    }




}
