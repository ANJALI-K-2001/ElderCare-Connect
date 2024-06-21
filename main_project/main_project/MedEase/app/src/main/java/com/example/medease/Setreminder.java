package com.example.medease;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class Setreminder extends AppCompatActivity implements  JsonResponse{

    EditText e1,e2,e3,e4,e5,e6;

    Button b1;

    String medname,start_date,end_date,time_mrg,time_aftn,time_nyt;

    SharedPreferences sh;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setreminder);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        e1=(EditText)  findViewById(R.id.editTextDate);
        e2=(EditText) findViewById(R.id.editTextDate2);
        e3=(EditText) findViewById(R.id.editTextTime2);
        e4=(EditText) findViewById(R.id.editTextTime3);
        e5=(EditText) findViewById(R.id.editTextTime4);
        e6=(EditText)  findViewById(R.id.editTextText11);

        e6.setText(Addmedicine.medicine_name);

        b1=(Button) findViewById(R.id.button13);



        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(Setreminder.this,
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


        e2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(Setreminder.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                e2.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();


            }
        });

        e3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(Setreminder.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // Set the selected time to the EditText in the desired format
                                String selectedTime = String.format("%02d:%02d", hourOfDay, minute);
                                e3.setText(selectedTime);
                            }
                        }, hour, minute, false);
                timePickerDialog.show();
            }
        });
        e4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(Setreminder.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // Set the selected time to the EditText in the desired format
                                String selectedTime = String.format("%02d:%02d", hourOfDay, minute);
                                e4.setText(selectedTime);
                            }
                        }, hour, minute, false);
                timePickerDialog.show();
            }
        });

        e5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(Setreminder.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // Set the selected time to the EditText in the desired format
                                String selectedTime = String.format("%02d:%02d", hourOfDay, minute);
                                e5.setText(selectedTime);
                            }
                        }, hour, minute, false);
                timePickerDialog.show();

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_date=e1.getText().toString();
                end_date=e2.getText().toString();
                time_mrg=e3.getText().toString();
                time_aftn=e4.getText().toString();
                time_nyt=e5.getText().toString();
                medname=e6.getText().toString();


                if (start_date.equalsIgnoreCase("")){
                    e1.setError("enter the date");
                    e1.setFocusable(true);
                } else if (end_date.equalsIgnoreCase("")) {
                    e2.setError("enter the date");
                    e2.setFocusable(true);

                } else if (time_mrg.equalsIgnoreCase("")) {
                    e3.setError("enter the time");
                    e3.setFocusable(true);

                } else if (time_aftn.equalsIgnoreCase("")) {
                    e4.setError("enter the time");
                    e4.setFocusable(true);

                } else if (time_nyt.equalsIgnoreCase("")) {
                    e5.setFocusable(true);
                    e5.setError("enter the time");

                } else if (medname.equalsIgnoreCase("")) {
                    e6.setError("enter the name");
                    e6.setFocusable(true);

                } else {

                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) Setreminder.this;
                    String q = "/set_reminder?med_id=" + Addmedicine.medicinedetid + "&start_date=" + start_date + "&end_date=" + end_date + "&time_mrg=" + time_mrg + "&time_afn=" + time_aftn + "&time_nyt=" + time_nyt;
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
            Log.d("method",status);

            if(status.equalsIgnoreCase("success")){

                Toast.makeText(this, "Set Reminder success", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Home.class));

            }
        }

        catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }

    }


    public void onBackPressed ()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(), Home.class);
        startActivity(b);
    }

}