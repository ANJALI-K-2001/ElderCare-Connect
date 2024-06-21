package com.example.medease;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Viewdoctors extends AppCompatActivity implements JsonResponse{

    ListView l1;

    String[] doctor_name,qualification,specialised_in,phone,email,doctid,value;

    public  static  String doctors_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdoctors);

        l1=(ListView) findViewById(R.id.viewdoctors);

        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                doctors_id=doctid[position];
                final  CharSequence[] items ={"Make Appointment","Cancel"};
                android.app.AlertDialog.Builder builder = new AlertDialog.Builder(Viewdoctors.this);
                builder.setTitle("Select Option!");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (items[item].equals("Make Appointment")){
                            Intent il=new Intent(getApplicationContext(), Makeappointment.class);
                            startActivity(il);

                        } else if (items[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();

            }
        });
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Viewdoctors.this;
        String q = "/viewdoctors?";
        q = q.replace(" ", "%20");
        JR.execute(q);

    }


    @Override
    public void response(JSONObject jo) {
        try {

            String status =jo.getString("status");
            String method = jo.getString("method");

            Log.d("method",method);

            if (method.equalsIgnoreCase("vewdoctors")){
                if (status.equalsIgnoreCase("success")){
                    JSONArray ja=(JSONArray) jo.getJSONArray("data");

                    doctid=new String[ja.length()];
                    doctor_name=new String[ja.length()];
                    qualification=new String[ja.length()];
                    specialised_in=new String[ja.length()];

                    phone=new String[ja.length()];
                    email=new String[ja.length()];
                    value=new String[ja.length()];

                    for (int i=0;i<ja.length();i++){

                        doctid[i]=ja.getJSONObject(i).getString("doctor_id");
                        doctor_name[i]=ja.getJSONObject(i).getString("doctor_name");
                        qualification[i]=ja.getJSONObject(i).getString("qualification");
                        specialised_in[i]=ja.getJSONObject(i).getString("specialised_in");
                        phone[i]=ja.getJSONObject(i).getString("phone");
                        email[i]=ja.getJSONObject(i).getString("email");

                        value[i]="Name: " + doctor_name[i] +  "\nQualification: " + qualification[i]  + "\nSpecialised_in: " + specialised_in[i]  + "\nPhone: " + phone[i] + "\nEmail: " + email[i];

                    }
                    ArrayAdapter<String> ar=new ArrayAdapter<>(getApplication(),R.layout.custlistdoctors,value);
                    l1.setAdapter(ar);

                }
            }
            else {
                Toast.makeText(this , "No Messages", Toast.LENGTH_SHORT).show();
            }


        }

        catch (Exception e) {
            Toast.makeText(getApplication(), e.toString(), Toast.LENGTH_SHORT).show();
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



