package com.example.sukanya.test;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;

/**
 * Created by Shriya Talwar on 9/29/2016.
 */
public class CallCon extends Activity {
    String num1 = null, num2 = null, num3 = null, num4 = null, num5 = null;
    String n1 = null, n2 = null, n3 = null, n4 = null, n5 = null;
    Button b1, b2, b3, b4, b5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.callcon);

        num1 = EmergencyContacts.getN1();
        num2 = EmergencyContacts.getN2();
        num3 = EmergencyContacts.getN3();
        num4 = EmergencyContacts.getN4();
        num5 = EmergencyContacts.getN5();
        n1 = EmergencyContacts.getNa1();
        n2 = EmergencyContacts.getNa2();
        n3 = EmergencyContacts.getNa3();
        n4 = EmergencyContacts.getNa4();
        n5 = EmergencyContacts.getNa5();
        b1 = (Button) findViewById(R.id.but1);
        b2 = (Button) findViewById(R.id.but2);
        b3 = (Button) findViewById(R.id.but3);
        b4 = (Button) findViewById(R.id.but4);
        b5 = (Button) findViewById(R.id.but5);
        if (num1!=null) {
            b1.setText("Call "+n1);
            b1.setVisibility(View.VISIBLE);
            b1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    try {

                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + num1)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        }  if (num2 != null) {
            b2.setText("Call " + n2);
            b2.setVisibility(View.VISIBLE);
            b2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    try {

                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + num2)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }  if (num3 != null) {
            b3.setText("Call " + n3);
            b3.setVisibility(View.VISIBLE);
            b3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    try {

                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + num3)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } if (num4 != null) {
            b4.setText("Call " + n4);
            b4.setVisibility(View.VISIBLE);
            b4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    try {

                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + num4)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } if (num5 != null) {
            b5.setText("Call " + n5);
            b5.setVisibility(View.VISIBLE);
            b5.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    try {

                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + num5)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


}
