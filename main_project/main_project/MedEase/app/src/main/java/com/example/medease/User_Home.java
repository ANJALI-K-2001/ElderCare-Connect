package com.example.medease;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class User_Home extends AppCompatActivity {
    Button b1,b2,b4,b5,b6,b7;

    ImageView b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        b1=findViewById(R.id.button4);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Viewmedicine.class));
            }
        });

        b2=findViewById(R.id.sendcompl);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Sendenquiry.class));
            }
        });

        b3=findViewById(R.id.userlogout);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

        b4=findViewById(R.id.button7);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Viewdoctors.class));
            }
        });

        b5=findViewById(R.id.button5);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Viewappointment.class));
            }
        });

        b6=findViewById(R.id.button15);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),User_send_emergency_alert.class));
            }
        });

        b7=findViewById(R.id.button16);
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Viewtransportation.class));
            }
        });





    }



    public boolean dispatchKeyEvent(KeyEvent event) {

        int action = event.getAction();
        int keyCode = event.getKeyCode();

        switch (keyCode) {


            case KeyEvent.KEYCODE_VOLUME_DOWN:

                if (action == KeyEvent.ACTION_UP) {

                    if (event.getEventTime() - event.getDownTime() > ViewConfiguration.getLongPressTimeout()) {
                        try {


                            Toast.makeText(this, "Emergency Call ", Toast.LENGTH_SHORT).show();

                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(Uri.parse("tel:" + "10000000"));
                            startActivity(callIntent);

                            return true;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }//TODO long click action

//                        Toast.makeText(this, "Volume Down Pressed", Toast.LENGTH_SHORT).show();

                    } else {
                        //TODO click action

                    }
                }
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }
}