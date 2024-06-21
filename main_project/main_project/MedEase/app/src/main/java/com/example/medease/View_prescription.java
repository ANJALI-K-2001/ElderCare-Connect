package com.example.medease;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class View_prescription extends AppCompatActivity implements JsonResponse{

    ListView l1;

    String [] prescription_details,appointment_id,date,value;

    SharedPreferences sh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_prescription);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        l1=(ListView) findViewById(R.id.viewprescription);


        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) View_prescription.this;
        String q = "/viewprescription?appid=" + Viewappointment.app_id;
        q = q.replace(" ", "%20");
        JR.execute(q);


    }


    @Override
    public void response(JSONObject jo) {
        try {

            String status = jo.getString("status");
            String method= jo.getString("method");

            Log.d("method",method);

            if (method.equalsIgnoreCase("viewprescription")){

                if (status.equalsIgnoreCase("success")){

                    JSONArray ja=(JSONArray) jo.getJSONArray("data");


                    prescription_details=new String[ja.length()];
                    appointment_id=new String[ja.length()];
                    date =new  String[ja.length()];
                    value=new String[ja.length()];


                    for (int i=0;i< ja.length();i++){


                        appointment_id[i]=ja.getJSONObject(i).getString("appointment_id");
                        prescription_details[i]=ja.getJSONObject(i).getString("prescription_details");
                        date[i]=ja.getJSONObject(i).getString("pre_date");


                        value[i]=" Prescription: " + prescription_details[i] + "\n Date: " + date[i];

                    }
                    ArrayAdapter<String> ar =new ArrayAdapter<>(getApplication(), R.layout.custlistdoctors,value);
                    l1.setAdapter(ar);
//
                }else {
                    Toast.makeText(this, "no messages", Toast.LENGTH_SHORT).show();
                }
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_SHORT).show();
        }
    }


}

