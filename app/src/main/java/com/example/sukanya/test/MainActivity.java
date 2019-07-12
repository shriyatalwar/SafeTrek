package com.example.sukanya.test;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SensorEventListener{
    FragmentManager fm=getSupportFragmentManager();
    // ---ACCELEROMETER SENSORS ---
    private float lastX, lastY, lastZ;
    double netAcc;
    private SensorManager sensorManager;
    private Sensor accelerometer,proximity;
    int flag=0;
    private float changeX = 0,changeY = 0,changeZ = 0;
    //private TextView mTextField;  // TIMER
    int fl=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contextOfApplication = getApplicationContext();
        accelerometer();
        setContentView(R.layout.activity_main);
        Home frag=new Home();
        fm.beginTransaction().replace(R.id.content_frame,frag,frag.getTag()).commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // Button sirenB=(Button) findViewById(R.id.alert);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    public static Context contextOfApplication;
    public static Context getContextOfApplication()
    {
        return contextOfApplication;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Home frag=new Home();
            fm.beginTransaction().replace(R.id.content_frame,frag,frag.getTag()).commit();
        }
    }
    private void accelerometer() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            //  proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            sensorManager.registerListener((SensorEventListener) this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            Home frag=new Home();
            fm.beginTransaction().replace(R.id.content_frame,frag,frag.getTag()).commit();
            // Handle the camera action
        }
       else if (id == R.id.nav_camera) {
            Contacts frag=new Contacts();
            fm.beginTransaction().replace(R.id.content_frame,frag,frag.getTag()).commit();
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Voice frag=new Voice();
            fm.beginTransaction().replace(R.id.content_frame,frag,frag.getTag()).commit();
        }else if (id == R.id.nav_slideshow1) {
            Utilities frag=new Utilities();
            fm.beginTransaction().replace(R.id.content_frame,frag,frag.getTag()).commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER) {
            changeX = Math.abs(lastX - event.values[0]);
            changeY = Math.abs(lastY - event.values[1]);
            changeZ = Math.abs(lastZ - event.values[2]);
            netAcc = Math.sqrt((changeX * changeX) + (changeY * changeY) + (changeZ * changeZ));
            if (netAcc < 3) {
                new CountDownTimer(10000, 1000) {
                    public void onTick(long mS) {
                       // mTextField.setText(" " + mS / 1000);
                        if (mS / 1000 == 3)
                            flag = 0;
                    }

                    public void onFinish() {
                        if (flag == 1) {
                    //        mTextField.setText(" no alert");
                        } else {
                     //       mTextField.setText("alert");
                            Intent i = new Intent(getApplicationContext(), Alarm.class);
                            i.putExtra("message",fl);
                            startActivity(i);
                        }
                    }
                }.start();
            } else if (netAcc >= 9) {

                if (netAcc > 9.7 && netAcc < 10.05) {
                    // do nothing
                } else
                    flag = 1;
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
