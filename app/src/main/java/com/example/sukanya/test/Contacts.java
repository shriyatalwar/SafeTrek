package com.example.sukanya.test;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Contacts extends Fragment {


    public Contacts() {
        // Required empty public constructor
    }

    View myView;
    // ---- CONTACTS -----
    String NAME;
    String number="g";
    static int i=0;
    TextView tv,con2,con3,con4,con5;
    // -----MESSAGE -----
    int fl=0;

    String num1="0",num2="0",num3="0",num4="0",num5="0";
    String arr[]=new String[5];
    //TextView tv,con2,con3,con4,con5;
    //SetContactsListener setContactsListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView= inflater.inflate(R.layout.fragment_contacts, container, false);
        final TextView tv= (TextView)myView.findViewById(R.id.tv);
        final TextView con2= (TextView)myView.findViewById(R.id.con2);
        final TextView con3= (TextView)myView.findViewById(R.id.con3);
        final TextView con4= (TextView)myView.findViewById(R.id.con4);
        final TextView con5= (TextView)myView.findViewById(R.id.con5);
        if(EmergencyContacts.getF1()==1){
            tv.setText(EmergencyContacts.getNa1()+": "+EmergencyContacts.getN1());
        }
        if(EmergencyContacts.getF2()==1){
            con2.setText(EmergencyContacts.getNa2()+": "+EmergencyContacts.getN2());
        }
        if(EmergencyContacts.getF3()==1){
            con3.setText(EmergencyContacts.getNa3()+": "+EmergencyContacts.getN3());
        }
        if(EmergencyContacts.getF4()==1){
            con4.setText(EmergencyContacts.getNa4()+": "+EmergencyContacts.getN4());
        }
        if(EmergencyContacts.getF5()==1){
            con5.setText(EmergencyContacts.getNa5()+": "+EmergencyContacts.getN5());
        }
        Button add=(Button)myView.findViewById(R.id.addCont);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText e = (EditText)myView.findViewById(R.id.con1);
                NAME = e.getText().toString();
                Context applicationContext = MainActivity.getContextOfApplication();

                ContentResolver cr = applicationContext.getContentResolver();
                Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
                        "DISPLAY_NAME = '" + NAME + "'", null, null);

                if (cursor.moveToFirst()) {
                    String contactId =
                            cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    //
                    //  Get all phone numbers.
                    //
                    Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
                    while (phones.moveToNext()) {
                        number = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));


                    }
                    phones.close();
                }
                cursor.close();
                fl=1;
                i++;
                if(i==1) {
                    tv.setText(NAME+": "+number);
                    num1 = (number);
EmergencyContacts.setN1(num1);
                    EmergencyContacts.setNa1(NAME);
                    EmergencyContacts.setF1(1);
                    e.setText("");
                }
                else if(i==2)
                { con2.setText(NAME+": "+number);
                    num2 = (number);
                    EmergencyContacts.setN2(num2);
                  EmergencyContacts.setNa2(NAME);
                    EmergencyContacts.setF2(1);
                    e.setText("");
                }
                else if(i==3)
                {con3.setText(NAME+": "+number);
                    num3 = (number);
                    EmergencyContacts.setN3(num3);
                    EmergencyContacts.setNa3(NAME);
                    EmergencyContacts.setF3(1);
                    e.setText("");
                }
                else if(i==4)
                { con4.setText(NAME+": "+number);
                    num4 = (number);
                    EmergencyContacts.setN4(num4);
                    EmergencyContacts.setNa4(NAME);
                    EmergencyContacts.setF4(1);
                    e.setText("");
                }
                else if(i==5) {
                    con5.setText(NAME+": "+number);
                    EmergencyContacts.setN5(num5);
                    EmergencyContacts.setNa5(NAME);
                    EmergencyContacts.setF5(1);
                    num5 = (number);
                    e.setText("");
                }
                else {
                     Toast.makeText(MainActivity.getContextOfApplication(),"No further contacts can be added", Toast.LENGTH_LONG).show();
                }
            }
        });
        return myView;
    }
}
