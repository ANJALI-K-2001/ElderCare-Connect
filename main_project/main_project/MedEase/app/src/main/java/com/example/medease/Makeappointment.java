package com.example.medease;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Makeappointment extends AppCompatActivity implements JsonResponse{


    EditText e1,e2;
    Button b1;

    public  static  String date,reason;

    SharedPreferences sh;


    byte[] byteArray = null;


    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makeappointment);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        e1=(EditText)  findViewById(R.id.editTextText2);


        e2=(EditText) findViewById(R.id.editTreas);

        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(Makeappointment.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                e1.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });



        b1=(Button) findViewById(R.id.button10);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date=e1.getText().toString();
                reason=e2.getText().toString();


                SharedPreferences.Editor e = sh.edit();
                e.putString("date", date);
                e.commit();

                if (date.equalsIgnoreCase("")){
                    e1.setFocusable(true);
                    e1.setError("enter the date");
                }else {

                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) Makeappointment.this;
                    String q = "/user_make_appointment?user_id=" + sh.getString("login_id","")  + "&date=" + date + "&reason=" + reason +  "&doctors_id=" + Viewdoctors.doctors_id;
                    q = q.replace(" ", "%20");
                    JR.execute(q);

                }

            }


        });

    }


    @Override
    public void response(JSONObject jo) {
        try {
            String status = jo.getString("status");

            if (status.equalsIgnoreCase("success")){
                Toast.makeText(this, "Make the appointment successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),User_Home.class));
            }

        } catch (Exception e) {

            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }

    }


    public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),Viewdoctors.class);
        startActivity(b);
    }




}

