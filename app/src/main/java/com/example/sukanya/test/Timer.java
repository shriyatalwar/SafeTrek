package com.example.sukanya.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shriya Talwar on 9/29/2016.
 */
public class Timer extends Activity implements AdapterView.OnItemSelectedListener {
    int hour, minute, second;

    int ms = 0;
    TextView timer;
    int fl=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);

        Intent ij = getIntent();
        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> hr = new ArrayList<String>();
        int i;
        for (i = 0; i < 24; i++) {
            hr.add(String.valueOf(i));
        }


        List<String> min = new ArrayList<String>();
        for (i = 0; i < 61; i++) {
            min.add(String.valueOf(i));
        }

        List<String> sec = new ArrayList<String>();
        for (i = 0; i < 61; i++) {
            sec.add(String.valueOf(i));
        }
        // Creating adapter for spinner
        ArrayAdapter<String> hrAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hr);
        ArrayAdapter<String> minAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, min);
        ArrayAdapter<String> secAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sec);
        // Drop down layout style - list view with radio button
        hrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        minAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        secAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(hrAdapter);
        spinner1.setAdapter(minAdapter);
        spinner2.setAdapter(secAdapter);
        spinner.setOnItemSelectedListener(this);
        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinner:
                String item = parent.getItemAtPosition(position).toString();
                hour = Integer.parseInt(item);
                // Showing selected spinner item
                break;
            case R.id.spinner1:
                String item1 = parent.getItemAtPosition(position).toString();
                minute = Integer.parseInt(item1);
                // Showing selected spinner item
                //  Toast.makeText(parent.getContext(), "Selected: " + item1, Toast.LENGTH_LONG).show();// do stuffs with you spinner 2
                break;
            case R.id.spinner2:
                String item2 = parent.getItemAtPosition(position).toString();
                second = Integer.parseInt(item2);
                // Showing selected spinner item
                //  Toast.makeText(parent.getContext(), "Selected: " + item2, Toast.LENGTH_LONG).show();
                break;
        }
        // On selecting a spinner item
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void time(View view) {
        timer = (TextView) findViewById(R.id.timer);
        ms = hour * 60 * 60 + minute * 60 + second;
        new CountDownTimer(1000 * ms, 1000) {
            public void onTick(long mS) {
                timer.setText(" " + mS / 1000);

            }

            public void onFinish() {
                timer.setText("TIMER");
                Intent i = new Intent(getApplicationContext(), Alarm.class);
                i.putExtra("message",fl);
                startActivity(i);
            }
        }.start();
    }
}
